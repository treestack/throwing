package de.treestack.throwing;

/**
 * Represents a function that accepts no arguments, produces a result
 * and (optionally) throws an Exception.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #getAsBoolean()} )}.
 *
 * @param <E> the type of the exception that may be thrown
 * @since 1.0
 */
@FunctionalInterface
public interface BooleanSupplier<E extends Exception> {

    /**
     * Applies this function to the given argument.
     *
     * @return the function result
     * @throws E if an exception occurs
     * @since 1.0
     */
    boolean getAsBoolean() throws E;

    /**
     * Wraps a function that may throw an exception into a function that will throw a RuntimeException if the original
     * function throws an exception.
     *
     * @param function the function to wrap
     * @param <E>      the type of the exception thrown by the function
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @since 1.0
     */
    static <E extends Exception> java.util.function.BooleanSupplier unchecked(final BooleanSupplier<E> function) {
        return () -> {
            try {
                return function.getAsBoolean();
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
     * @see #unchecked(BooleanSupplier)
     * @since 1.0
     */
    default java.util.function.BooleanSupplier unchecked() {
        return unchecked(this);
    }
}
