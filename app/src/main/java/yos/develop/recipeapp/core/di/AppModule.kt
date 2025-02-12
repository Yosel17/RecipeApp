package yos.develop.recipeapp.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import yos.develop.recipeapp.core.room.config.AppDatabase
import yos.develop.recipeapp.core.room.tables.recipe.RecipeDao
import yos.develop.recipeapp.core.room.tables.user.UserDao
import yos.develop.recipeapp.core.utils.Constants
import yos.develop.recipeapp.screen.home.data.HomeRepositoryImpl
import yos.develop.recipeapp.screen.home.domain.HomeRepository
import yos.develop.recipeapp.screen.login.data.LoginRepositoryImpl
import yos.develop.recipeapp.screen.login.domain.LoginRepository
import yos.develop.recipeapp.splash.data.SplashRepositoryImpl
import yos.develop.recipeapp.splash.domain.SplashRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //Room
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase{
        return Room.databaseBuilder(appContext, AppDatabase::class.java, Constants.TABLE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao{
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): RecipeDao{
        return appDatabase.recipeDao()
    }

    //Repository
    @Singleton
    @Provides
    fun provideSplashRepository(impl: SplashRepositoryImpl): SplashRepository = impl

    @Singleton
    @Provides
    fun provideLoginRepository(impl: LoginRepositoryImpl): LoginRepository = impl

    @Singleton
    @Provides
    fun provideHomeRepository(impl: HomeRepositoryImpl): HomeRepository = impl

}