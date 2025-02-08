package de.treestack.throwing;

/**
 * Represents a predicate (boolean-valued function) of two arguments that might throw an exception.
 * This is the two-arity specialization of {@link Predicate}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #test(Object, Object)}.
 *
 * @param <T1> the type of the first input to the predicate
 * @param <T2> the type of the second input to the predicate
 * @param <E> the type of the exception that may be thrown
 * @since 1.0
 */
@FunctionalInterface
public interface BiPredicate<T1, T2, E extends Exception> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the first input argument
     * @param t2 the second input argument
     * @return {@code true} if the input arguments match the predicate,
     * @throws E if an exception occurs
     * otherwise {@code false}
     */
    boolean test(T1 t, T2 t2) throws E;

    /**
     * Wraps a function that may throw an exception into a function that will throw a RuntimeException if the original
     * function throws an exception.
     *
     * @param function the function to wrap
     * @return a function that will throw a RuntimeException if the original function throws an exception
     * @param <T1> the type of the input to the function
     * @param <T2> the type of the input to the function
     * @since 1.0
     */
    static <T1, T2> java.util.function.BiPredicate<T1, T2> unchecked(final BiPredicate<? super T1, ? super T2, ?> function) {
        return (t, t2) -> {
            try {
                return function.test(t, t2);
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
     * @see #unchecked(BiPredicate)
     * @since 1.0
     */
    default java.util.function.BiPredicate<T1, T2> unchecked() {
        return unchecked(this);
    }
}
