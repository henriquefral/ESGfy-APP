package br.com.fiap.mvp_esgfy.service

import br.com.fiap.mvp_esgfy.model.Formulario
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FormularioService {

    @GET("formulario")
    fun getFormulario(): Call<List<Formulario>>

    @POST("formulario")
    fun postFormulario(@Body body: Formulario): Call<ResponseBody>

}