package de.treestack.throwing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BiConsumerTest {

    @Test
    void unchecked_shouldAcceptValue() {
        BiConsumer<Integer, Integer, Exception> f = (i, j) -> {};
        f.unchecked().accept(1, 2);
    }

    @Test
    void unchecked_shouldThrowRuntimeException() {
        BiConsumer<Integer, Integer, Exception> f = (i, j) -> {
            throw new Exception("custom exception message");
        };
        assertThatThrownBy(() -> f.unchecked().accept(1, 2))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("java.lang.Exception: custom exception message");
    }


}