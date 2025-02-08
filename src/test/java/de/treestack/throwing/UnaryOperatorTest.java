package de.treestack.throwing;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class UnaryOperatorTest {
    @Test
    void lift_shouldHaveResult() {
        UnaryOperator<Integer, Exception> f = (i) -> i + 1;
        Optional<Integer> result = f.lift().apply(21);
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void lift_shouldBeEmpty() {
        UnaryOperator<Integer, Exception> f = (i) -> {
            throw new Exception("custom exception message");
        };
        Optional<Integer> result = f.lift().apply(21);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void unchecked_shouldHaveResult() {
        UnaryOperator<Integer, Exception> f = (i) -> i + 1;
        Integer result = f.unchecked().apply(41);
        assertThat(result).isEqualTo(42);
    }

    @Test
    void unchecked_shouldThrowRuntimeException() {
        UnaryOperator<Integer, Exception> f = (i) -> {
            throw new Exception("custom exception message");
        };
        assertThatThrownBy(() -> f.unchecked().apply(21))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception message");
    }
}
