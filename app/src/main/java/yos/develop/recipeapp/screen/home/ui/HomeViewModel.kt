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
import yos.develop.recipeapp.core.utils.Catalog
import yos.develop.recipeapp.core.utils.Constants
import yos.develop.recipeapp.core.utils.Resource
import yos.develop.recipeapp.core.utils.codeAStringSelectFilter
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
            HomeEvent.Logout -> {
                logout()
            }
            HomeEvent.ToggleSuccess -> {
                state = state.copy(successLogout = !state.successLogout)
            }
            HomeEvent.ToggleShowDialogFilter -> {
                state = state.copy(showDialogFilter = !state.showDialogFilter)
            }
            is HomeEvent.ChangeFilter -> {
                changeFilter(selected = event.selected)
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
                            changeFilter(selected = codeAStringSelectFilter(selected = state.selectedFilter))
                            state = state.copy(isLoadingDataInitial = false)
                        }.launchIn(viewModelScope)
                    }
                }
            }
        }
    }

    private fun logout() {

        viewModelScope.launch(Dispatchers.IO) {

            val response = homeRepository.logout()

            withContext(Dispatchers.Main){
                when(response){
                    is Resource.Failure -> {
                        val errorMessage = response.exception.localizedMessage ?: "---"
                        state = state.copy(errorMessage = errorMessage)
                        state = state.copy(isError = true)
                    }
                    is Resource.Success -> {
                        state = state.copy(successLogout = true)
                    }
                }
            }
        }

    }

    private fun changeFilter(selected: String) {
        when(selected){
            Constants.ALL_FILTER ->{
                state = state.copy(selectedFilter = Catalog.ALL_FILTER)
                state = state.copy(recipesFilter = state.recipes)
                state = state.copy(isFilterApplied = false)
                state = state.copy(showDialogFilter = false)
            }
            Constants.PREPARATION_TIME_FILTER ->{
                state = state.copy(selectedFilter = Catalog.PREPARATION_TIME_FILTER)
                state = state.copy(recipesFilter = state.recipes.sortedBy { it.preparationTime })
                state = state.copy(isFilterApplied = true)
                state = state.copy(showDialogFilter = false)
            }
            Constants.FAVORITE_FILTER ->{
                state = state.copy(selectedFilter = Catalog.FAVORITE_FILTER)
                state = state.copy(recipesFilter = state.recipes.filter { it.favorite })
                state = state.copy(isFilterApplied = true)
                state = state.copy(showDialogFilter = false)
            }
        }
    }
}