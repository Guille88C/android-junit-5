package com.guille88c.junit5

import org.junit.jupiter.api.Assertions.assertEquals
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

    @Tag("enable_test")
    @DisplayName("Given numbers, when isOdd is called, then res is expected")
    @TestFactory
    fun testIsOdd() = listOf(
        1 to true,
        2 to false,
        3 to true,
        4 to false
    ).map { (num, res) ->
        dynamicTest("num = $num, res = $res") {
            assertEquals(res, num.isOdd())
        }
    }

    @Tag("enable_test")
    @DisplayName("Given myArgument, when times is called, then res is expected")
    @TestFactory
    fun testTimes() = listOf(
        MyArgument(1, 1, 1),
        MyArgument(1, 2, 2),
        MyArgument(2, 2, 4),
        MyArgument(2, 3, 6)
    ).map {
        dynamicTest("argument = $it") {
            assertEquals(it.res, it.first.times(it.second))
        }
    }
}
