package com.nisarg.teamsync.data.mapper

import com.nisarg.teamsync.data.model.UserDto
import com.nisarg.teamsync.domain.model.User

fun UserDto.toDomain() : User{
    return User(
        email = email,
        uid = uid,
        name = name,
        teamIds = teamIds
    )
}

fun User.toDTO() : UserDto{
    return UserDto(
        email = email,
        uid = uid,
        name = name,
        teamIds = teamIds
    )
}