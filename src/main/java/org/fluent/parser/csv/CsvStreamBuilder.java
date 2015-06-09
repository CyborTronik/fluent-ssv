package org.fluent.parser.csv;

import org.fluent.parser.csv.convert.TypeConverterMap;
import org.fluent.parser.csv.convert.ValueConverter;
import org.fluent.parser.csv.exception.CsvStreamBuilderException;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by strifan on 04.06.2015.
 */
public class CsvStreamBuilder<T> {
	private IndexedProperties indexedProperties;
	private LineParser lineParser = new StringSeparatedValuesLineParser();
	private ItemFactory<T> itemFactory;
	private ItemMapper<T> itemMapper;
	private TypeConverterMap typeConverterMap = new TypeConverterMap();

	public CsvStreamBuilder<T> forEntity(Class<T> entityType) {
		ItemFactory<T> itemFactory = new DefaultItemFactory<T>(entityType);
		return withItemFactory(itemFactory);
	}

	public CsvStreamBuilder<T> withItemFactory(ItemFactory<T> itemFactory) {
		this.itemFactory = itemFactory;
		return this;
	}

	public CsvStreamBuilder<T> withHeader(String... header) {
		IndexedProperties indexedProperties = new DefaultIndexedProperties(header);
		return withIndexedProperties(indexedProperties);
	}

	public CsvStreamBuilder<T> withIndexedProperties(IndexedProperties indexedProperties) {
		this.indexedProperties = indexedProperties;
		return this;
	}

	public CsvStreamBuilder<T> withSeparator(String separator) {
		LineParser lineParser = new StringSeparatedValuesLineParser(separator);
		return withLineParser(lineParser);
	}

	public CsvStreamBuilder<T> withLineParser(LineParser lineParser) {
		this.lineParser = lineParser;
		return this;
	}

	public CsvStreamBuilder<T> withItemMapper(ItemMapper<T> itemMapper) {
		this.itemMapper = itemMapper;
		return this;
	}

	public CsvStreamBuilder<T> withValueConverter(ValueConverter<?> valueConverter) {
		typeConverterMap.add(valueConverter);
		return this;
	}

	public Stream<ParsedRecord<T>> stream(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		return stream(path);
	}

	public Stream<ParsedRecord<T>> stream(URI uri) throws IOException {
		Path path = Paths.get(uri);
		return stream(path);
	}

	public Stream<ParsedRecord<T>> stream(Path path) throws IOException {
		Stream<String> lines = Files.lines(path);
		return stream(lines);
	}

	public Stream<ParsedRecord<T>> stream(Stream<String> streamLines) {
		if (itemMapper == null && itemFactory == null)
			throw new CsvStreamBuilderException("Impossible to init a stream without knowing how to map an item. Please provide an ItemMapper or an ItemFactory or use 'forEntity' method.");
		return streamLines.map(this::map).filter(item -> item != null);
	}

	/**
	 * Ugly method because Streams in Java are not enough mature(compared with other related technologies).
	 * Should be replaced and optimized in the future with Head::Tail format.
	 */
	private ParsedRecord<T> map(String line) {
		if (indexedProperties == null && itemMapper == null) {
			getItemMapper(line);
			return null;
		}
		try {
			T item = itemMapper.map(line);
			return new ParsedRecord<>(item);
		} catch (Exception ex) {
			return new ParsedRecord<>(ex);
		}
	}

	private ItemMapper<T> getItemMapper(String line) {
		if (itemMapper == null) {
			IndexedProperties indexedProperties = getIndexedProperties(line);
			IndexedValueSetter valueSetter = new IndexedValueSetter(indexedProperties, typeConverterMap);
			itemMapper = new DefaultItemMapper<T>(valueSetter, itemFactory, lineParser);
		}
		return itemMapper;
	}

	private IndexedProperties getIndexedProperties(String line) {
		if (indexedProperties == null)
			initIndexedPropertiesWithFirstLine(line);
		return this.indexedProperties;
	}

	private void initIndexedPropertiesWithFirstLine(String line) {
		String[] header = lineParser.parse(line);
		withHeader(header);
	}

}
