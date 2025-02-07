package de.treestack.throwing;

import java.util.Optional;

/**
 * Represents a function that accepts no arguments, produces a result
 * and (optionally) throws an Exception.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #get()} )}.
 *
 * @param <R> the type of the result of the function
 * @param <E> the type of the exception that may be thrown
 *
 * @since 1.0
 */
@FunctionalInterface
public interface Supplier<R, E extends Exception> {

    /**
     * Applies this function to the given argument.
     *
     * @return the function result
     * @throws E if an exception occurs
     * @since 1.0
     */
    R get() throws E;

    /**
     * Lifts a function that may throw an exception into a function that returns an Optional.
     * If the original function throws an exception, the returned function will return an empty Optional.
     * Otherwise, the returned function will return an Optional containing the result of the original function.
     *
     * @param function the function to lift
     * @return a function that returns an Optional
     * @param <R> the type of the result of the function
     * @since 1.0
     */
    static <R> java.util.function.Supplier<Optional<R>> lifted(final Supplier<? extends R, ?> function) {
        return () -> {
            try {
                return Optional.ofNullable(function.get());
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
     * @param <R> the type of the result of the function
     * @since 1.0
     */
    static <R> java.util.function.Supplier<R> unchecked(final Supplier<? extends R, ?> function) {
        return () -> {
            try {
                return function.get();
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Lifts this function that may throw an exception into a function that returns an Optional.
     * If the original function throws an exception, the returned function will return an empty Optional.
     * Otherwise, the returned function will return an Optional containing the result of the original function.
     *
     * @return a function that returns an Optional
     * @see #lifted(Supplier)
     * @since 1.0
     */
    default java.util.function.Supplier<Optional<R>> lift() {
        return lifted(this);
    }

    /**
     * Wraps this function that may throw an exception into a function that will throw a RuntimeException if
     * the original function throws an exception.
     *
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @see #unchecked(Supplier)
     * @since 1.0
     */
    default java.util.function.Supplier<R> unchecked() {
        return unchecked(this);
    }
}

