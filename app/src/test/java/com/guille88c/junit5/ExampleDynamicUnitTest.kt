package com.guille88c.junit5

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestFactory

/**
 * https://junit.org/junit5/docs/current/user-guide/#writing-tests-dynamic-tests
 */
internal class ExampleDynamicUnitTest {

    @Tag("enable_test")
    @DisplayName("Given odd numbers, when isOdd is called, then true is expected")
    @TestFactory
    fun testOddNumbers() = listOf(1, 3, 5, 7).map {
        dynamicTest("number = $it") {
            assertTrue { it.isOdd() }
        }
    }
}
