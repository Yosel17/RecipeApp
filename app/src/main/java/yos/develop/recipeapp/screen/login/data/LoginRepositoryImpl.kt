package yos.develop.recipeapp.screen.login.data

import yos.develop.recipeapp.core.room.tables.user.UserDao
import yos.develop.recipeapp.core.room.tables.user.UserEntity
import yos.develop.recipeapp.core.utils.Resource
import yos.develop.recipeapp.screen.login.domain.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): LoginRepository {

    override suspend fun login(email: String, password: String): Resource<Boolean> {
        return try {
            userDao.insertUser(
                userEntity = UserEntity(
                    email = email,
                    password = password
                )
            )
            Resource.Success(true)
        }catch (e: Exception){
            Resource.Failure(exception = e)
        }
    }
}