package de.treestack.throwing;

/**
 * Represents a function that accepts one argument, produces no result
 * and (optionally) throws an Exception.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object)} )}.
 *
 * @param <T> the type of the input to the function
 * @param <E> the type of the exception that may be thrown
 *
 * @since 1.0
 */
@FunctionalInterface
public interface Consumer<T, E extends Exception> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @throws E if an exception occurs
     * @since 1.0
     */
    void accept(T t) throws E;

    /**
     * Wraps a function that may throw an exception into a function that will throw a RuntimeException if the original
     * function throws an exception.
     *
     * @param function the function to wrap
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @param <T> the type of the input to the function
     * @param <E> the type of the exception thrown by the function
     * @since 1.0
     */
    static <T, E extends Exception> java.util.function.Consumer<T> unchecked(final Consumer<? super T, E> function) {
        return t -> {
            try {
                function.accept(t);
            } catch (final Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        };
    }

    /**
     * Wraps this function that may throw an exception into a function that will throw a RuntimeException if
     * the original function throws an exception.
     *
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @see #unchecked(Consumer)
     * @since 1.0
     */
    default java.util.function.Consumer<T> unchecked() {
        return unchecked(this);
    }
}

