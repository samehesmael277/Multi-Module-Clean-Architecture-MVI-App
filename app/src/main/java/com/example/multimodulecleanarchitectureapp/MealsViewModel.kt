package com.example.multimodulecleanarchitectureapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetMeals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsUseCase: GetMeals
) : ViewModel() {

    val intentChannel = Channel<MainIntent>(Channel.UNLIMITED)

    private var _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Idle)
    val state: StateFlow<MainState> = _state

    init {
        intentProcess()
    }

    private fun intentProcess() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    MainIntent.GetMeals -> {
                        _state.emit(MainState.Loading)
                        getMeals()
                    }
                }
            }
        }
    }

    private fun getMeals() {
        viewModelScope.launch {
            try {
                _state.emit(MainState.Meals(getMealsUseCase()))
            } catch (e: Exception) {
                _state.emit(MainState.Error(e.message.toString()))
            }
        }
    }
}