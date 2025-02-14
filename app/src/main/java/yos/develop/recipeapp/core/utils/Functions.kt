package yos.develop.recipeapp.core.utils

fun codeAStringSelectFilter(selected: Int): String{
    return when(selected){
        Catalog.ALL_FILTER -> Constants.ALL_FILTER
        Catalog.PREPARATION_TIME_FILTER -> Constants.PREPARATION_TIME_FILTER
        Catalog.FAVORITE_FILTER -> Constants.FAVORITE_FILTER
        else -> Constants.ALL_FILTER
    }
}