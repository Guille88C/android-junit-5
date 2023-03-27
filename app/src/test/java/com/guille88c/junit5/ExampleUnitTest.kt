package com.guille88c.junit5

import java.util.Arrays
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
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
    @CsvSource(
        "2, 1, 2",
        "2, 2, 1",
        "4, 1, 4",
        "4, 2, 2",
        "4, 4, 1"
    )
    @ParameterizedTest
    fun testDiv(num: Int, den: Int, res: Int) {
        assertEquals(res, num.div(den))
    }

    @Tag("enable_test")
    @MethodSource("arguments")
    @ParameterizedTest
    fun testTimesWithArguments(first: Int, second: Int, res: Int) {
        assertEquals(res, first.times(second))
    }

    @Tag("enable_test")
    @MethodSource("myArguments")
    @ParameterizedTest
    fun testTimesWithMyArguments(myArguments: MyArguments) {
        assertEquals(myArguments.res, myArguments.first.times(myArguments.second))
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

    companion object {

        @JvmStatic
        fun arguments(): Stream<Arguments> = Stream.of(
            Arguments.of(1, 1, 1),
            Arguments.of(1, 2, 2),
            Arguments.of(2, 2, 4),
            Arguments.of(2, 3, 6)
        )

        @JvmStatic
        fun myArguments(): Stream<MyArguments> = Arrays.stream(
            arrayOf(
                MyArguments(1, 1, 1),
                MyArguments(1, 2, 2),
                MyArguments(2, 2, 4),
                MyArguments(2, 3, 6)
            )
        )
    }

    data class MyArguments(
        val first: Int,
        val second: Int,
        val res: Int
    )
}
