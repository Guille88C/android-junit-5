package com.guille88c.junit5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<String>()
    val state: LiveData<String>
        get() = _state

    init {
        viewModelScope.launch {
            _state.value = "state initialized"
        }
    }
}
