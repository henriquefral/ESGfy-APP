package br.com.fiap.mvp_esgfy.rankESG

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue;
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mvp_esgfy.model.RankPilarESG
import br.com.fiap.mvp_esgfy.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RankESGSCreen(codigo : Int) {

    var rankPilarESGState = remember { mutableStateListOf<RankPilarESG>() }
    var errorGet          by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val call = RetrofitFactory().getRankPilarESGService().getRankPilarESG(codigo)

        call.enqueue(object : Callback<List<RankPilarESG>> {
            override fun onResponse(
                call: Call<List<RankPilarESG>>,
                response: Response<List<RankPilarESG>>
            ) {

                errorGet = false;

                if ( response.body() != null ) {

                    response.body()!!.forEach { rankPilarESGState.add(element = it) }
                }
            }

            override fun onFailure(call: Call<List<RankPilarESG>>, t: Throwable) {
                errorGet = true;
            }
        })
    }

    Column {
        Text(text = "Estes são os maiores ranks de pontuação ESG!", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(15.dp))
        if ( errorGet ) {
            Text(text = "Houve um problema ao gerar os dados do rank, favor, aguardar.")
        } else {

            LazyColumn (modifier = Modifier.fillMaxSize()

            ) {
                items(rankPilarESGState) {
                    Column () {
                        Text(text = "Pilar: "+it.pilarESG.nome)
                        if ( it.fornecedor == null ) {
                            Text(text = "Empresa: " + it.usuario.nome)
                        } else {
                            Text(text = "Fornecedor: " + it.fornecedor.nome)
                        }
                        Text(text = "Pontos: "+it.pontos.toString())
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }




    }
}