package de.treestack.throwing;

import java.util.Optional;

/**
 * Represents a function that accepts two arguments, produces a result
 * and (optionally) throws an Exception.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object)} )}.
 *
 * @param <T1> the type of the input to the function
 * @param <T2> the type of the input to the function
 * @param <R> the type of the result of the function
 * @param <E> the type of the exception that may be thrown
 *
 * @since 1.0
 */
@FunctionalInterface
public interface BiFunction<T1, T2, R, E extends Exception> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the first function argument
     * @param t2 the second function argument
     * @return the function result
     * @throws E if an exception occurs
     * @since 1.0
     */
    R apply(T1 t, T2 t2) throws E;

    /**
     * Lifts a function that may throw an exception into a function that returns an Optional.
     * If the original function throws an exception, the returned function will return an empty Optional.
     * Otherwise, the returned function will return an Optional containing the result of the original function.
     *
     * @param function the function to lift
     * @return a function that returns an Optional
     * @param <T1> the type of the first input to the function
     * @param <T2> the type of the second input to the function
     * @param <R> the type of the result of the function
     * @since 1.0
     */
    static <T1, T2, R> java.util.function.BiFunction<T1, T2, Optional<R>> lifted(final BiFunction<? super T1, ? super T2, ? extends R, ?> function) {
        return (t, t2) -> {
            try {
                return Optional.ofNullable(function.apply(t, t2));
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
     * @param <T1> the type of the first input to the function
     * @param <T2> the type of the second input to the function
     * @param <R> the type of the result of the function
     * @since 1.0
     */
    static <T1, T2, R> java.util.function.BiFunction<T1, T2, R> unchecked(final BiFunction<? super T1, ? super T2, ? extends R, ?> function) {
        return (t, t2) -> {
            try {
                return function.apply(t, t2);
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
     * @see #lifted(BiFunction)
     * @since 1.0
     */
    default java.util.function.BiFunction<T1, T2, Optional<R>> lift() {
        return lifted(this);
    }

    /**
     * Wraps this function that may throw an exception into a function that will throw a RuntimeException if
     * the original function throws an exception.
     *
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @see #unchecked(BiFunction)
     * @since 1.0
     */
    default java.util.function.BiFunction<T1, T2, R> unchecked() {
        return unchecked(this);
    }
}

