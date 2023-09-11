package com.example.mealsappcleanarch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CategoryResponse
import com.example.domain.repo.MealsRepo
import com.example.domain.usecase.MealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val mealsUseCase: MealsUseCase) : ViewModel() {
    private val TAG = "MealsViewModel"
    private val _meals: MutableStateFlow<CategoryResponse?> = MutableStateFlow(null)

     val meals : StateFlow<CategoryResponse?> get() = _meals

    fun getMeals() {
        viewModelScope.launch {
            try {
                _meals.value = mealsUseCase()
            } catch (e: Exception) {
                Log.e(TAG, "getMeals:${e.message.toString()}")
            }
        }
    }
}