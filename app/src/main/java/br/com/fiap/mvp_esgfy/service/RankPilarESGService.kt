package br.com.fiap.mvp_esgfy.service

import br.com.fiap.mvp_esgfy.model.RankPilarESG
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RankPilarESGService {
    @GET("rankPilarESG/usuario/{codigoUsuario}")
    fun getRankPilarESG(@Path("codigoUsuario") codigoUsuario:  Int): Call<List<RankPilarESG>>

}