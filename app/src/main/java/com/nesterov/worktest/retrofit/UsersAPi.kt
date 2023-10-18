package com.nesterov.worktest.retrofit

import retrofit2.http.GET

interface UsersAPi {
    @GET("users")
    suspend fun getAllUsers(): List<Users>
}