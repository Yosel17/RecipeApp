package yos.develop.recipeapp.core.room.tables.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val idUser: Int = 0,
    val email: String,
    val password: String
)
