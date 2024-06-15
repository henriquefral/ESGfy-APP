package br.com.fiap.mvp_esgfy.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import br.com.fiap.mvp_esgfy.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class RankPilarESG (
    var codigo     : Int = 0,
    var pontos     : Int = 0,
    var usuario    : Usuario = Usuario(),
    var pilarESG   : PilarESG = PilarESG(),
    var fornecedor : Fornecedor = Fornecedor()

) {

}