package yos.develop.recipeapp.core.room.config

import androidx.room.Database
import androidx.room.RoomDatabase
import yos.develop.recipeapp.core.room.tables.recipe.RecipeDao
import yos.develop.recipeapp.core.room.tables.recipe.RecipeEntity
import yos.develop.recipeapp.core.room.tables.user.UserDao
import yos.develop.recipeapp.core.room.tables.user.UserEntity

@Database(
    entities = [UserEntity::class, RecipeEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun recipeDao(): RecipeDao
}