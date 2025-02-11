package yos.develop.recipeapp.splash.data

import yos.develop.recipeapp.core.room.tables.user.UserDao
import yos.develop.recipeapp.core.utils.Resource
import yos.develop.recipeapp.splash.domain.SplashRepository
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): SplashRepository {

    override suspend fun getSession(): Resource<Boolean> {
        return try {
            val userEntity = userDao.getSession()

            if (userEntity != null){
                Resource.Success(true)
            }else{
                Resource.Success(false)
            }
        }catch (e: Exception){
            Resource.Failure(exception = e)
        }
    }
}