package de.treestack.throwing;

/**
 * Represents a predicate (boolean-valued function) of one argument that might throw an exception.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #test(Object)}.
 *
 * @param <T> the type of the input to the predicate
 * @since 1.0
 */
@FunctionalInterface
public interface Predicate<T, E extends Exception> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t) throws E;

    /**
     * Wraps a function that may throw an exception into a function that will throw a RuntimeException if the original
     * function throws an exception.
     *
     * @param function the function to wrap
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @param <T> the type of the input to the function
     * @since 1.0
     */
    static <T> java.util.function.Predicate<T> unchecked(final Predicate<? super T, ?> function) {
        return t -> {
            try {
                return function.test(t);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Wraps this function that may throw an exception into a function that will throw a RuntimeException if
     * the original function throws an exception.
     *
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @see #unchecked(Predicate)
     * @since 1.0
     */
    default java.util.function.Predicate<T> unchecked() {
        return unchecked(this);
    }
}
