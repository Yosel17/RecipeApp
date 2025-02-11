package yos.develop.recipeapp.core.room.tables.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun insertUser(userEntity: UserEntity)

    @Query("select * from userentity")
    suspend fun getSession(): UserEntity?

    @Query("delete from userentity")
    suspend fun deleteUser()
}