package com.guille88c.junit5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
internal class ExampleUnitTest {

    @Tag("disable_test")
    @Test
    fun testDisabled() {
        assertTrue {
            1.isEven()
        }
    }

    @Tag("enable_test")
    @DisplayName("Given an odd number, when isOdd is called, then true is expected")
    @Test
    fun testOddNumber() {
        assertTrue {
            1.isOdd()
        }
    }

    @Tag("enable_test")
    @DisplayName("Given an even number, when isEven is called, then true is expected")
    @Test
    fun testEvenNumber() {
        assertTrue {
            2.isEven()
        }
    }

    @Tag("enable_test")
    @DisplayName("Given odd numbers, when isOdd is called, then true is expected")
    @ValueSource(ints = [1, 3, 5, 7])
    @ParameterizedTest
    fun testOddNumbers(number: Int) {
        assertTrue {
            number.isOdd()
        }
    }

    @Tag("enable_test")
    @DisplayName("Given even numbers, when isEvent is called, then true is expected")
    @ValueSource(ints = [0, 2, 4, 6])
    @ParameterizedTest
    fun testEvenNumbers(number: Int) {
        assertTrue {
            number.isEven()
        }
    }

    @Tag("enable_test")
    @DisplayName("Given zero number")
    @Nested
    inner class GivenZeroNumber {

        private val number = 0

        @DisplayName("When div by number is called, then exception is expected")
        @Test
        fun testDiv() {
            assertThrows<Exception> {
                @Suppress("DIVISION_BY_ZERO")
                2.div(number)
            }
        }

        @DisplayName("When times by number is called, then 0 is expected")
        @Test
        fun testTimes() {
            assertEquals(0, 2.times(number))
        }
    }
}
