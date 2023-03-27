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
    @DisplayName("Given numbers by argument, when times is applied, then result is expected")
    @MethodSource("argument")
    @ParameterizedTest
    fun testTimesWithArgument(first: Int, second: Int, res: Int) {
        assertEquals(res, first.times(second))
    }

    @Tag("enable_test")
    @DisplayName("Given numbers by MyArgument with stream, when times is applied, then result is expected")
    @MethodSource("myArgumentWithStream")
    @ParameterizedTest
    fun testTimesWithMyArgumentWithStream(myArgument: MyArgument) {
        assertEquals(myArgument.res, myArgument.first.times(myArgument.second))
    }

    @Tag("enable_test")
    @DisplayName("Given numbers by MyArgument with array, when times is applied, then result is expected")
    @MethodSource("myArgumentWithArray")
    @ParameterizedTest
    fun testTimesWithMyArgumentWithArray(myArgument: MyArgument) {
        assertEquals(myArgument.res, myArgument.first.times(myArgument.second))
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
        fun argument(): Stream<Arguments> = Stream.of(
            Arguments.of(1, 1, 1),
            Arguments.of(1, 2, 2),
            Arguments.of(2, 2, 4),
            Arguments.of(2, 3, 6)
        )

        @JvmStatic
        fun myArgumentWithStream(): Stream<Arguments> = Stream.of(
            Arguments.of(MyArgument(1, 1, 1)),
            Arguments.of(MyArgument(1, 2, 2)),
            Arguments.of(MyArgument(2, 2, 4)),
            Arguments.of(MyArgument(2, 3, 6))
        )

        @JvmStatic
        fun myArgumentWithArray(): Stream<MyArgument> = Arrays.stream(
            arrayOf(
                MyArgument(1, 1, 1),
                MyArgument(1, 2, 2),
                MyArgument(2, 2, 4),
                MyArgument(2, 3, 6)
            )
        )
    }
}
