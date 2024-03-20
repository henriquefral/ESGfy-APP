package br.com.fiap.mvp_esgfy.answerFormulario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mvp_esgfy.components.Header
import br.com.fiap.mvp_esgfy.components.InputBox
import br.com.fiap.mvp_esgfy.model.Formulario
import br.com.fiap.mvp_esgfy.ui.theme.CustomBlue
import br.com.fiap.mvp_esgfy.ui.theme.CustomGray
import br.com.fiap.mvp_esgfy.ui.theme.CustomGraySub
import br.com.fiap.mvp_esgfy.ui.theme.CustomGreen
import br.com.fiap.mvp_esgfy.ui.theme.CustomOrange

@Composable
fun AnswerFormularioScreen (navController: NavController, formulario: Formulario) {

    var pontos    by remember { mutableIntStateOf(0) }

    if ( pontos == 0 ) formulario.perguntas!!.forEach{ pergunta -> pontos += pergunta.pontos; pergunta.resposta = "" }

    Column {
        Header(true, navController)
        Row {
            Text("Formulário: ", fontSize = 20.sp, color = CustomGreen)
            Text(formulario.nome.replace('+', ' '), fontSize = 20.sp, color = CustomBlue)
        }
        Row {
            Text("Pontos: ", fontSize = 20.sp, color = CustomOrange)
            Text(pontos.toString(), fontSize = 20.sp)
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(CustomGray)
            .height(2.dp))
        LazyColumn (
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(formulario.perguntas!!) { index, it ->
                Column( horizontalAlignment = Alignment.CenterHorizontally, 
                        modifier = Modifier.fillMaxWidth())
                {
                    Text(text = it.titulo.replace('+', ' '), fontSize = 20.sp)
                    Text(it.pontos.toString().plus(" pontos"), color = CustomGraySub)
                    when ( it.tipo ) {
                      "1" -> {

                      }
                      "2" -> {
                          InputBox( value = it.resposta,
                                    onValueChange = { value -> it.resposta = value },
                                    modifier = Modifier.fillMaxWidth()
                          )
                      }
                      "3" -> {
                          
                      }  
                    } 
                }
            }
        }
    }
}