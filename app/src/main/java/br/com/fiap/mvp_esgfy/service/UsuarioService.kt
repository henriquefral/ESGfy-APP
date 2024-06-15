package br.com.fiap.mvp_esgfy.service

import br.com.fiap.mvp_esgfy.model.Usuario
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UsuarioService {
    @POST("usuario/login")
    fun loginUsuario(@Body usuario: Usuario): Call<Usuario>

}