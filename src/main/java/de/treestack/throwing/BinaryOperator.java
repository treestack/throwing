package de.treestack.throwing;

/**
 * Represents an operation upon two operands of the same type, producing a result
 * of the same type as the operands and (optionally) throwing an Exception.
 * This is a specialization of {@link java.util.function.BiFunction} for
 * the case where the operands and the result are all the same type.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> the type of the operands and result of the operator
 * @param <E> the type of the exception that may be thrown
 * @see java.util.function.BiFunction
 * @see UnaryOperator
 * @since 1.0
 */
public interface BinaryOperator<T, E extends Exception> extends BiFunction<T, T, T, E> {

}
