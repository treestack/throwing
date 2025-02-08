package de.treestack.throwing;

import java.util.Optional;

/**
 * Represents a function that accepts one argument, produces a result
 * and (optionally) throws an Exception.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)} )}.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @param <E> the type of the exception that may be thrown
 *
 * @since 1.0
 */
@FunctionalInterface
public interface Function<T, R, E extends Exception> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws E if an exception occurs
     * @since 1.0
     */
    R apply(T t) throws E;

    /**
     * Lifts a function that may throw an exception into a function that returns an Optional.
     * If the original function throws an exception, the returned function will return an empty Optional.
     * Otherwise, the returned function will return an Optional containing the result of the original function.
     *
     * @param function the function to lift
     * @return a function that returns an Optional
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     * @since 1.0
     */
    static <T, R, E extends Exception> java.util.function.Function<T, Optional<R>> lifted(final Function<? super T, R, E> function) {
        return t -> {
            try {
                return Optional.ofNullable(function.apply(t));
            } catch (final Exception e) {
                return Optional.empty();
            }
        };
    }

    /**
     * Wraps a function that may throw an exception into a function that will throw a RuntimeException if the original
     * function throws an exception.
     *
     * @param function the function to wrap
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     * @since 1.0
     */
    static <T, R, E extends Exception> java.util.function.Function<T, R> unchecked(final Function<? super T, R, E> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (final Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        };
    }

    /**
     * Lifts this function that may throw an exception into a function that returns an Optional.
     * If the original function throws an exception, the returned function will return an empty Optional.
     * Otherwise, the returned function will return an Optional containing the result of the original function.
     *
     * @return a function that returns an Optional
     * @see #lifted(Function)
     * @since 1.0
     */
    default java.util.function.Function<T, Optional<R>> lift() {
        return lifted(this);
    }

    /**
     * Wraps this function that may throw an exception into a function that will throw a RuntimeException if
     * the original function throws an exception.
     *
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @see #unchecked(Function)
     * @since 1.0
     */
    default java.util.function.Function<T, R> unchecked() {
        return unchecked(this);
    }
}

