package yos.develop.recipeapp.core.room.tables.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val idUser: Int,
    val email: String,
    val password: String
)
