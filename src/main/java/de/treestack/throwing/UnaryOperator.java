package de.treestack.throwing;

/**
 * Represents an operation upon one operand, producing a result
 * of the same type as the operand and (optionally) throwing an Exception.
 * This is a specialization of {@link java.util.function.Function} for
 * the case where the operand and the result are of the same type.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the operands and result of the operator
 *
 * @see java.util.function.Function
 * @see java.util.function.BinaryOperator

 * @since 1.0
 */
public interface UnaryOperator<T, E extends Exception> extends Function<T, T, E> {

}
