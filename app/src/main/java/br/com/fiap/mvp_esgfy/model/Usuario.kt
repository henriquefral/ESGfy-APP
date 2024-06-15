package br.com.fiap.mvp_esgfy.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import br.com.fiap.mvp_esgfy.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Usuario (
    var nome         : String = "",
    var email        : String = "",
    var senha        : String = "",
    var razaoSocial  : String = "",
    var cgc          : String = "",
    var tipo         : String = "",
    var codigo       : Int    = 1
) {

    var errorLogin by mutableStateOf(false)

    fun login(navController : NavController) {

        val call = RetrofitFactory().getUsuarioService().loginUsuario(this);

        call.enqueue(object : Callback<Usuario> {
            override fun onResponse(
                call: Call<Usuario>,
                response: Response<Usuario>
            ) {

                errorLogin = false;

                if ( response.body() != null ) {

                    val usuario = response.body()!!

                    codigo = usuario.codigo
                    nome = usuario.nome
                    razaoSocial = usuario.razaoSocial
                    cgc = usuario.cgc
                    tipo = usuario.tipo

                    navController.currentBackStackEntry?.savedStateHandle?.set("codigo",
                        usuario.codigo)
                    navController.currentBackStackEntry?.savedStateHandle?.set("nome",
                        usuario.nome)

                    navController.navigate("menu")
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {

                errorLogin = true;
            }
        })
    }
}