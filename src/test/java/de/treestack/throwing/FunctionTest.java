package de.treestack.throwing;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class FunctionTest {

    @Test
    void lift_shouldHaveResult() {
        Function<Integer, Integer, Exception> f = i -> i + 1;
        Optional<Integer> result = f.lift().apply(42);
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void lift_shouldBeEmpty() {
        Function<Integer, Integer, Exception> f = i -> {
            throw new Exception("custom exception message");
        };
        Optional<Integer> result = f.lift().apply(42);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void unchecked_shouldHaveResult() {
        Function<Integer, Integer, Exception> f = i -> i + 1;
        Integer result = f.unchecked().apply(42);
        assertThat(result).isEqualTo(43);
    }

    @Test
    void unchecked_shouldThrowRuntimeException() {
        Function<Integer, Integer, Exception> f = i -> {
            throw new Exception("custom exception message");
        };
        assertThatThrownBy(() -> f.unchecked().apply(42))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("java.lang.Exception: custom exception message");

    }
}