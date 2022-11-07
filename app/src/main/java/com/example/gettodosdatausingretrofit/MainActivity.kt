package com.example.gettodosdatausingretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    private val BASEURL =
        "https://jsonplaceholder.typicode.com"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASEURL)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = retrofit.create(GetTodos::class.java)
        val call = apiService.gedtodo()

        call.enqueue(object : Callback<List<ToDo>>{
            override fun onResponse(call: Call<List<ToDo>>, response: Response<List<ToDo>>) {

                if(response.code() == 200){
                    val todos = response.body()
                    print(todos)
                }
            }

            override fun onFailure(call: Call<List<ToDo>>, t: Throwable) {
                Log.e("RESPONSE", "There is no Data")
            }
        })
    }
}