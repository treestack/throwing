package de.treestack.throwing;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SupplierTest {

    @Test
    void lift_shouldHaveResult() {
        Supplier<Integer, Exception> f = () -> 1;
        Optional<Integer> result = f.lift().get();
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void lift_shouldBeEmpty() {
        Supplier<Integer, Exception> f = () -> {
            throw new Exception("custom exception message");
        };
        Optional<Integer> result = f.lift().get();
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void unchecked_shouldHaveResult() {
        Supplier<Integer, Exception> f = () -> 1;
        Integer result = f.unchecked().get();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void unchecked_shouldThrowRuntimeException() {
        Supplier<Integer, Exception> f = () -> {
            throw new Exception("custom exception message");
        };
        assertThatThrownBy(() -> f.unchecked().get())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("java.lang.Exception: custom exception message");
    }
}