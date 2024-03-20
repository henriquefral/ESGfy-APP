package br.com.fiap.mvp_esgfy.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "http://192.168.0.17:8080/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getFormularioService(): FormularioService {
        return retrofitFactory.create(FormularioService::class.java)
    }

}