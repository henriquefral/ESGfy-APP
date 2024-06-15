package br.com.fiap.mvp_esgfy.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mvp_esgfy.components.SaveButton
import br.com.fiap.mvp_esgfy.model.Usuario

@Composable
fun MenuScreen(navController: NavController, usuario: Usuario? = null) {
    Column {
        Text(text = "Bem vindo, ".plus(usuario?.nome), fontSize = 30.sp)
        Spacer(modifier = Modifier.height(50.dp))
        SaveButton(title = "Verificar ranking ESG!"
                 , modifier = Modifier.size(410.dp, 70.dp)
                 , onClick = {
                navController.popBackStack();
                navController.currentBackStackEntry?.savedStateHandle?.set("codigo",
                    usuario?.codigo)
                navController.currentBackStackEntry?.savedStateHandle?.set("nome",
                    usuario?.nome)
                navController.navigate("rankPilarESG")
        } )
    }
}