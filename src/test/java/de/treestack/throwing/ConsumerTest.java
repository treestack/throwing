package de.treestack.throwing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ConsumerTest {

    @Test
    void unchecked_shouldAcceptValue() {
        Consumer<Integer, Exception> f = i -> {};
        f.unchecked().accept(1);
    }

    @Test
    void unchecked_shouldThrowRuntimeException() {
        Consumer<Integer, Exception> f = i -> {
            throw new Exception("custom exception message");
        };
        assertThatThrownBy(() -> f.unchecked().accept(1))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception message");
    }
}