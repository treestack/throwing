package de.treestack.throwing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BiPredicateTest {

    @Test
    void unchecked_shouldHaveResult() {
        BiPredicate<Integer, String, Exception> f = (i, s) -> true;
        boolean result = f.unchecked().test(42, "foo");
        assertThat(result).isTrue();
    }

    @Test
    void unchecked_shouldThrowRuntimeException() {
        BiPredicate<Integer, String, Exception> f = (i, s) -> {
            throw new Exception("custom exception message");
        };
        assertThatThrownBy(() -> f.unchecked().test(42, "foo"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("java.lang.Exception: custom exception message");
    }
}