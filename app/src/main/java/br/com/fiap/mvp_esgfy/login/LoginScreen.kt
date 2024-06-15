package br.com.fiap.mvp_esgfy.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mvp_esgfy.R
import br.com.fiap.mvp_esgfy.components.SaveButton
import br.com.fiap.mvp_esgfy.model.Usuario

@Composable
fun LoginScreen(navController: NavController) {

    val usuario    by remember { mutableStateOf(Usuario()) }

    var loginId    by remember { mutableStateOf("1@gmail.com") }
    var loginPass  by remember { mutableStateOf("1") }

    var errorId    by remember { mutableStateOf(false) }
    var errorPass  by remember { mutableStateOf(false) }

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "wallet",
            modifier = Modifier
                .size(300.dp, 100.dp)
        )
        Text(text = "Jobfy!", fontSize = 35.sp, modifier = Modifier.padding(top = 30.dp))
        Box(modifier = Modifier.padding(top = 20.dp)) {
            Column (modifier = Modifier.width(330.dp)) {
                Text(text = "Email:", fontSize = 30.sp, modifier = Modifier.padding(bottom = 10.dp))
                TextField(value = loginId, onValueChange = {loginId = it}, isError = errorId
                         ,modifier = Modifier.width(330.dp))
                Text(text = "Senha:", fontSize = 30.sp, modifier = Modifier.padding(bottom = 10.dp, top = 20.dp))
                TextField(value = loginPass, onValueChange = {loginPass = it}, isError = errorPass
                    , modifier = Modifier.width(330.dp)
                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))

                if ( usuario.errorLogin ) {
                    Text(text="Usuário não localizado!", fontSize = 20.sp,
                         modifier = Modifier.padding(bottom = 10.dp))
                }

                SaveButton(title="Entrar!", onClick = {errorId = loginId.isEmpty(); errorPass = loginPass.isEmpty()
                    if ( !errorId && !errorPass ) {

                        usuario.email = loginId
                        usuario.senha = loginPass

                        usuario.login(navController)
                    }
                }, modifier = Modifier.padding(top = 30.dp))
            }
        }
    }
}