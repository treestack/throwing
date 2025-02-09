package de.treestack.throwing;

/**
 * Represents an operation that accepts two input arguments, returns no
 * result and (optionally) throws an Exception.
 * This is the two-arity specialization of {@link de.treestack.throwing.Consumer}.
 * Unlike most other functional interfaces, {@code BiConsumer} is expected
 * to operate via side effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the operation
 * @param <T2> the type of the second argument to the operation
 * @param <E> the type of the exception that may be thrown
 * @since 1.0
 */
@FunctionalInterface
public interface BiConsumer<T1, T2, E extends Exception> {

    /**
     * Applies this function to the given argument.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @throws E if an exception occurs
     * @since 1.0
     */
    void accept(T1 t1, T2 t2) throws E;

    /**
     * Wraps a function that may throw an exception into a function that will throw a RuntimeException if the original
     * function throws an exception.
     *
     * @param function the function to wrap
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @param <T1> the type of the first input to the function
     * @param <T2> the type of the second input to the function
     * @param <E> the type of the exception thrown by the function
     * @since 1.0
     */
    static <T1, T2, E extends Exception> java.util.function.BiConsumer<T1, T2> unchecked(final BiConsumer<? super T1, ? super T2, E> function) {
        return (t1, t2) -> {
            try {
                function.accept(t1, t2);
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
     * @see #unchecked(BiConsumer)
     * @since 1.0
     */
    default java.util.function.BiConsumer<T1, T2> unchecked() {
        return unchecked(this);
    }
}