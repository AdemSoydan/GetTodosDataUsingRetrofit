package com.example.gettodosdatausingretrofit

import retrofit2.Call
import retrofit2.http.GET

interface GetTodos {
    @GET("todos")
    fun gedtodo(): Call<List<ToDo>>
}