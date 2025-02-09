# Throwing functional interfaces

[![Maven CI/CD](https://github.com/treestack/throwing/actions/workflows/build.yml/badge.svg)](https://github.com/treestack/throwing/actions/workflows/build.yml)
![Coverage](https://treestack-static.s3.eu-central-1.amazonaws.com/badges/jacoco.svg)
![License](https://img.shields.io/github/license/treestack/throwing)

## Introduction

Java 8 introduced the concept of functional interfaces—interfaces that have only one abstract method. It also introduced lambda expressions, which allow for concise implementations of functional interfaces.

However, a significant limitation of Java's standard `java.util.function` interfaces is that they do not handle checked exceptions well. If you need to use a method that throws a checked exception within a lambda expression, you must wrap the method call in a try-catch block.

This leads to unnecessary boilerplate. What could have been a simple expression like this:

```java
Stream.of("foo", "bar")
    .map(s -> s.getBytes(StandardCharsets.UTF_8));
```

quickly turns into:

```java
Stream.of("foo", "bar")
        .map(s -> {
            try {
                return s.getBytes(StandardCharsets.UTF_8);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        });
```

This project provides a set of functional interfaces that support checked exceptions, allowing for cleaner and more readable code.

## Status

The following functional interfaces are currently provided by this project:

- `BiConsumer`
- `BiFunction`
- `BinaryOperator`
- `BiPredicate`
- `Consumer`
- `Function`
- `Predicate`
- `Supplier`
- `UnaryOperator`

## Installation

To use this library, add the following dependency to your project:

```xml
<dependency>
    <groupId>de.treestack</groupId>
    <artifactId>throwing</artifactId>
    <version>1.1.1</version>
</dependency>
```

## Unchecked exceptions

The `unchecked()` method is a utility that wraps methods throwing checked exceptions in a lambda expression that instead throws an unchecked exception.

⚠ Note: The `unchecked()` method wraps any thrown checked exception in a `RuntimeException`, setting the original exception as its cause.

### Example

```java 
import static de.treestack.throwing.Function.unchecked;

Stream.of("foo", "bar")
    .map(unchecked(s -> s.getBytes(StandardCharsets.UTF_8)));
```


## Lifted functional interfaces

Instead of using `unchecked()`, you can use the `lifted` functional interfaces provided by this project. These are similar to the standard functional interfaces in `java.util.function`, but instead of throwing an exception, they return an `Optional`.

⚠ Note: If an exception is thrown, the lifted functional interfaces return an empty `Optional`, meaning the original exception is lost.

### Example

Because the `lifted` functional interfaces return an `Optional`, you can use the `flatMap` method to chain operations that may fail.

```java
import static de.treestack.throwing.Function.lifted;

// Using a static method reference

Optional<MyObject> bytes = Optional.of("some-id")
        .flatMap(lifted(myService::findById));

// Using a lambda function

Optional<byte[]> bytes = Optional.of("foo")
    .flatMap(lifted(s -> s.getBytes(StandardCharsets.UTF_8)));
```

If an operation is not supposed to fail, or you just don't care about the exception, you can also wrap a method reference in a `lifted` function and call it directly: 

```java
import static de.treestack.throwing.Function.lifted;

Optional<String> name = lifted(myService::findById).apply(id)
    .map(MyEntity::getName);
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
