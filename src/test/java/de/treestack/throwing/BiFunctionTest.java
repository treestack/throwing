package de.treestack.throwing;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BiFunctionTest {

    @Test
    void lift_shouldHaveResult() {
        BiFunction<Integer, Integer, Integer, Exception> f = Integer::sum;
        Optional<Integer> result = f.lift().apply(21, 21);
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void lift_shouldBeEmpty() {
        BiFunction<Integer, Integer, Integer, Exception> f = (i, j) -> {
            throw new Exception("custom exception message");
        };
        Optional<Integer> result = f.lift().apply(21, 21);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void unchecked_shouldHaveResult() {
        BiFunction<Integer, Integer, Integer, Exception> f = Integer::sum;
        Integer result = f.unchecked().apply(21, 21);
        assertThat(result).isEqualTo(42);
    }

    @Test
    void unchecked_shouldThrowRuntimeException() {
        BiFunction<Integer, Integer, Integer, Exception> f = (i, j) -> {
            throw new Exception("custom exception message");
        };
        assertThatThrownBy(() -> f.unchecked().apply(21, 21))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception message");
    }
}