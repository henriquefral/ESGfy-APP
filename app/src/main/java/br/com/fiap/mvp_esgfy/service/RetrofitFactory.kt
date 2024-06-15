package br.com.fiap.mvp_esgfy.service

import br.com.fiap.mvp_esgfy.model.RankPilarESG
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "http://192.168.0.10:8080/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getUsuarioService(): UsuarioService {
        return retrofitFactory.create(UsuarioService::class.java)
    }

    fun getRankPilarESGService(): RankPilarESGService {
        return retrofitFactory.create(RankPilarESGService::class.java)
    }

}