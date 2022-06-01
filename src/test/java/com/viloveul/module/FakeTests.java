package com.viloveul.module;

import com.viloveul.module.initial.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {
        TestConfiguration.class
    }
)
class FakeTests {

    @Test
    void testDoang() {
    }
}
