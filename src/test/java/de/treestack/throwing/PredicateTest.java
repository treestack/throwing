package de.treestack.throwing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PredicateTest {

    @Test
    void unchecked_shouldHaveResult() {
        Predicate<Integer, Exception> f = i -> true;
        boolean result = f.unchecked().test(42);
        assertThat(result).isTrue();
    }

    @Test
    void unchecked_shouldThrowRuntimeException() {
        Predicate<Integer, Exception> f = i -> {
            throw new Exception("custom exception message");
        };
        assertThatThrownBy(() -> f.unchecked().test(42))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom exception message");
    }
}