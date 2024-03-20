package br.com.fiap.mvp_esgfy.createFormulario

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mvp_esgfy.components.AddButton
import br.com.fiap.mvp_esgfy.components.Header
import br.com.fiap.mvp_esgfy.components.InputBox
import br.com.fiap.mvp_esgfy.components.SaveButton
import br.com.fiap.mvp_esgfy.model.Formulario
import br.com.fiap.mvp_esgfy.model.Pergunta
import br.com.fiap.mvp_esgfy.service.RetrofitFactory
import br.com.fiap.mvp_esgfy.ui.theme.CustomGray
import br.com.fiap.mvp_esgfy.ui.theme.CustomGrayBt
import br.com.fiap.mvp_esgfy.ui.theme.CustomGraySub
import br.com.fiap.mvp_esgfy.ui.theme.CustomGreen
import br.com.fiap.mvp_esgfy.ui.theme.CustomOrange
import br.com.fiap.mvp_esgfy.ui.theme.JetBrainsMono
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateFormularioScreen (navController: NavController) {

    val focusRequester = remember { FocusRequester() }
    val context        = LocalContext.current

    var perguntas      by remember { mutableStateOf(listOf<Pergunta>()) }

    var nomeForm       by remember { mutableStateOf("") }
    var escolha        by remember { mutableStateOf("") }
    var tituloPergunta by remember { mutableStateOf("") }
    var pontos         by remember { mutableStateOf("") }
    var expanded       by remember { mutableStateOf(false) }
    var tipo           by remember { mutableStateOf("")  }
    var descTipo       by remember { mutableStateOf("")  }
    var escolhas       by remember { mutableStateOf(listOf<String>())}

    var nomeFormErro       by remember { mutableStateOf(false) }
    var escolhaErro        by remember { mutableStateOf(false) }
    var tituloPerguntaErro by remember { mutableStateOf(false) }
    var pontosErro         by remember { mutableStateOf(false) }
    var tipoErro           by remember { mutableStateOf(false) }
    var escolhasErro       by remember { mutableStateOf(false) }

    Column {
        Header(true, navController)
        Text("Cadastro de formulários", fontSize = 20.sp, color = CustomGreen)
        Spacer(modifier = Modifier.height(5.dp))
        Row (verticalAlignment = Alignment.CenterVertically) {
            Text("Formulário: ", fontSize = 18.sp, color = CustomOrange)
            InputBox(value = nomeForm, onValueChange = { nomeForm = it }, modifier = Modifier.fillMaxWidth(), error = nomeFormErro            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(CustomGray)
            .height(2.dp))
        Column (modifier = Modifier
            .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            ClickableText(
                            text = AnnotatedString("Adicionar pergunta (".plus(perguntas.size.toString()).plus(")")),
                            onClick = {

                                tituloPerguntaErro = tituloPergunta.isEmpty()

                                pontosErro = pontos.isEmpty()
                                if ( !pontosErro ) {
                                    pontosErro = pontos.toInt() <= 0
                                }

                                tipoErro = tipo.isEmpty()

                                escolhaErro = if ( tipo == "1" ) {
                                    escolhas.isEmpty()
                                } else {
                                    false
                                }
                                if ( !tituloPerguntaErro && !pontosErro && !tipoErro && !escolhaErro )
                                {
                                    perguntas = perguntas.plusElement(Pergunta( titulo = tituloPergunta,
                                                                                tipo = tipo,
                                                                                pontos = pontos.toInt(),
                                                                                escolhas = escolhas)
                                                )
                                }
                            },
                            style = TextStyle(color = CustomGraySub, fontFamily = JetBrainsMono, textDecoration = TextDecoration.Underline)
            )
            Spacer(modifier = Modifier.height(15.dp))
            InputBox(
                label = "Título da pergunta:",
                value = tituloPergunta,
                onValueChange = { tituloPergunta = it },
                modifier = Modifier.width(300.dp),
                error = tituloPerguntaErro
            )
            Spacer(modifier = Modifier.height(15.dp))
            InputBox(
                label = "Pontos:",
                value = pontos,
                onValueChange = { pontos = it },
                keyboardType = KeyboardType.Number,
                modifier = Modifier.width(100.dp),
                error = pontosErro
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Tipo:", fontFamily = JetBrainsMono)
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                modifier = Modifier.size(190.dp, 30.dp),
                onExpandedChange = { expanded = !expanded }
            ) {
                InputBox(value = descTipo,
                    modifier = Modifier
                        .width(250.dp)
                        .menuAnchor(),
                    enabled = false,
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    error = tipoErro)
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(CustomGrayBt)
                ) {
                    DropdownMenuItem(
                        text = { Text("Múltipla escolha") },
                        onClick = {
                            tipo = "1"
                            descTipo = "Múltipla escolha"
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Livre") },
                        onClick = {
                            tipo = "2"
                            descTipo = "Livre"
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Escala") },
                        onClick = {
                            tipo = "3"
                            descTipo = "Escala"
                            expanded = false
                        }
                    )
                }
            }
            if ( tipo == "1" ) {
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                }

                Row ( verticalAlignment = Alignment.CenterVertically ) {
                    InputBox(label = "Nova escolha:",
                        value = escolha,
                        onValueChange = { escolha = it },
                        modifier = Modifier.width(250.dp),
                        error = escolhasErro
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    AddButton(  onClick = {
                                                if (escolha.isEmpty()) {
                                                    escolhasErro = true
                                                } else {
                                                    escolhas = escolhas.plusElement(escolha)
                                                    escolha = ""
                                                    focusRequester.freeFocus()
                                                    escolhasErro = false
                                                }
                                          },
                                title = "")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .background(CustomGray)
                    .height(2.dp)
                    .padding(5.dp, 0.dp)
                )
                LazyColumn (
                    modifier = Modifier.height(50.dp).fillMaxWidth()
                ) {
                    items(escolhas) {
                        Spacer(modifier = Modifier.height(10.dp)                        )
                        InputBox(value = it)
                   }
                }
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(CustomGray)
            .height(2.dp))
        Spacer(modifier = Modifier
            .height(4.dp))
        Row (modifier = Modifier.fillMaxSize(),
             verticalAlignment = Alignment.CenterVertically,
             horizontalArrangement = Arrangement.Center) {
            SaveButton("Salvar!") {
                if ( perguntas.isEmpty() )
                {
                    tituloPerguntaErro = true
                    pontosErro = true
                    tipoErro = true
                    escolhaErro = true
                } else {
                    nomeFormErro = nomeForm.isEmpty()

                    if ( !nomeFormErro )
                    {
                        val formulario = Formulario(nome = nomeForm,
                                                    perguntas = perguntas)

                        val call = RetrofitFactory().getFormularioService()
                                                    .postFormulario(formulario)

                        call.enqueue(object : Callback<ResponseBody> {
                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>
                            ) {
                                Toast.makeText(context, "Formulário salvo!", Toast.LENGTH_LONG)
                                    .show()
                                navController.navigate("formulario")
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                var message = "Não foi implementado"

                                if ( t.message!!.isNotEmpty() ){
                                    message = t.message!!
                                }

                                Toast.makeText(context, "Não foi possível salvar o formulário. ".plus(message),
                                    Toast.LENGTH_LONG)
                                    .show()
                            }
                        })
                    }
                }
            }
        }
    }
}