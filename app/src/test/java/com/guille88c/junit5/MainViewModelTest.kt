package com.guille88c.junit5

import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@Tag("enable_test")
@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class, CoroutineTestExtension::class)
internal class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private val mockStateObserver: Observer<String> = mock()

    @BeforeEach
    fun setUp() {
        viewModel = MainViewModel().apply {
            state.observeForever(mockStateObserver)
        }
    }

    @AfterEach
    fun tearDown() {
        viewModel.state.removeObserver(mockStateObserver)
    }

    @DisplayName("When view model is created, then init state is expected")
    @Test
    fun testInit() {
        verify(mockStateObserver).onChanged("state initialized")
    }
}
