package com.example.tiki_test

import retrofit2.Call
import retrofit2.http.GET

interface TikiWS {
    @GET("tikivn/android-home-test/v2/keywords.json")
    fun getKeyword(): Call<ArrayList<String>>
}