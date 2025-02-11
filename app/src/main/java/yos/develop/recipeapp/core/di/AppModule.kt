package yos.develop.recipeapp.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import yos.develop.recipeapp.core.room.config.AppDatabase
import yos.develop.recipeapp.core.room.tables.user.UserDao
import yos.develop.recipeapp.core.utils.Constants
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
}