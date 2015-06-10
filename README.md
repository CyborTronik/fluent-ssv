# fluent-ssv (String separated values)
Fluent API to stream CSV, TSV and custom separated values files that are streamed with java beans(to POJOs)
Special designed to use streams from Java 8.

# To use it add in your pom.xml
```xml
<dependency>
  <groupId>com.github.cybortronik</groupId>
  <artifactId>fluent-ssv</artifactId>
  <version>0.1</version>
</dependency>
```

# Example of usage
So you should remember the start point in usage is class named SsvStreamBuilder
So simpliest example can be:
```java
class Person {
    private String name;
    private Integer age;

    public get/set....
}

//Some where in usage (default parser is for csv, you can change it see at SsvStreamBuilder methods)
Stream<ParsedRecord<Person>> streamedPersons = new SsvStreamBuilder<Person>()
                                            .forEntity(Person.class)
                                            .stream("~/some/file/path");
...
```
In this case CSV file will look something like (header by default is matched to Person class set methods),
methods order doesn't matter, but you are able to provide custom header and/or line parser, see SsvStreamBuilder:

```
Name, Age
Victor, 20
Leo, 18
...

```

# ParsedRecord structure

You are able to check if item was parsed with success by calling isParsed() (from class ParsedRecord).
Next to get parsed bean call getItem().
To check what happened call getException().

# Support
To check more cases ask via github or stackoverflow (put tag fluent-ssv).