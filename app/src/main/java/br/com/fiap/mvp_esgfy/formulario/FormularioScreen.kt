package br.com.fiap.mvp_esgfy.formulario

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mvp_esgfy.components.AddButton
import br.com.fiap.mvp_esgfy.components.Header
import br.com.fiap.mvp_esgfy.model.Formulario
import br.com.fiap.mvp_esgfy.service.RetrofitFactory
import br.com.fiap.mvp_esgfy.ui.theme.CustomBlue
import br.com.fiap.mvp_esgfy.ui.theme.CustomBlueSub
import br.com.fiap.mvp_esgfy.ui.theme.CustomGray
import br.com.fiap.mvp_esgfy.ui.theme.CustomGraySub
import br.com.fiap.mvp_esgfy.ui.theme.CustomGreen
import br.com.fiap.mvp_esgfy.ui.theme.CustomOrange
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FormularioScreen (navController: NavController) {

    var formulariosState by remember { mutableStateOf(listOf<Formulario>()) }
    val context = LocalContext.current
    val call = RetrofitFactory().getFormularioService().getFormulario()

    if (formulariosState.isEmpty()) {
        call.enqueue(object : Callback<List<Formulario>> {
            override fun onResponse(
                call: Call<List<Formulario>>,
                response: Response<List<Formulario>>
            ) {
                formulariosState = response.body()!!
                Toast.makeText(context, "Foi possível trazer os formulários.", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onFailure(call: Call<List<Formulario>>, t: Throwable) {
                var message = "Não foi implementado"

                if (t.message!!.isNotEmpty()) {
                    message = t.message!!
                }

                Toast.makeText(
                    context, "Não foi possível trazer os formulários. ".plus(message),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        })
    }

    Column {
        Header()
        Text("Formulários", fontSize = 24.sp, color = CustomGreen)
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Text("Cliente: ", fontSize = 24.sp, color = CustomOrange)
            Text("LogX"     , fontSize = 24.sp, color = CustomBlue)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(CustomGray)
            .height(2.dp))
        Spacer(modifier = Modifier.height(15.dp))
        AddButton("Novo formulário!") { navController.navigate("createFormulario") }
        Spacer(modifier = Modifier.height(5.dp))
        Spacer(modifier = Modifier
                .fillMaxWidth()
                .background(CustomGray)
                .height(2.dp))
        LazyColumn (
            modifier = Modifier.fillMaxWidth()
        ) {
            items(formulariosState) {

                var pontos = 0
                it.perguntas?.forEach { pergunta -> pontos += pergunta.pontos }

                Spacer(modifier = Modifier.height(10.dp))
                Box( modifier = Modifier.clickable {

                    val gson: Gson = GsonBuilder().create()

                    var formularioJson = gson.toJson(it)

                    formularioJson = URLEncoder.encode(formularioJson, StandardCharsets.UTF_8.toString())

                    navController.navigate(
                        "answerFormulario/$formularioJson"
                    )
                }) {
                    Column {
                        Text(it.nome, fontSize = 18.sp)
                        Text(pontos.toString().plus(" pontos disponíveis"),
                            fontSize = 14.sp,
                            color = CustomGraySub)
                        if ( it.finalizacao.isNullOrEmpty() ) {
                            Text("Disponível para análise, clique aqui para analisar",
                                fontSize = 11.sp,
                                color = CustomBlueSub)
                        } else {
                            Text("Disponível para análise, clique aqui para analisar",
                                fontSize = 11.sp,
                                color = CustomBlueSub)
                        }
                    }
                }
            }
        }
    }
}