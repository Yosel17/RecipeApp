package yos.develop.recipeapp.screen.home.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yos.develop.recipeapp.core.utils.Resource
import yos.develop.recipeapp.screen.home.domain.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    var state by mutableStateOf(HomeState())

    private var getRecipesJob: Job? = null

    fun onHomeEvent(event: HomeEvent){
        when(event){
            HomeEvent.ToggleShowDialogError ->{
                state = state.copy(isError = !state.isError)
            }
        }
    }

    init {
        getRecipes()
    }

    private fun getRecipes(){

        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getRecipes()

            withContext(Dispatchers.Main){
                when(response){
                    is Resource.Failure -> {
                        val errorMessage = response.exception.localizedMessage ?: "---"
                        state = state.copy(errorMessage = errorMessage)
                        state = state.copy(isError = true)
                        state = state.copy(isLoadingDataInitial = false)
                    }
                    is Resource.Success -> {
                        getRecipesJob = response.result.onEach {
                            state = state.copy(recipes = it)
                            state = state.copy(isLoadingDataInitial = false)
                        }.launchIn(viewModelScope)
                    }
                }
            }
        }
    }
}