package br.com.fiap.mvp_esgfy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.mvp_esgfy.login.LoginScreen
import br.com.fiap.mvp_esgfy.menu.MenuScreen
import br.com.fiap.mvp_esgfy.model.Usuario
import br.com.fiap.mvp_esgfy.rankESG.RankESGSCreen
import br.com.fiap.mvp_esgfy.ui.theme.MVP_ESGFYTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVP_ESGFYTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier
                                    .fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController=navController,
                        startDestination = "login",
                        modifier = Modifier.padding(15.dp, 15.dp, 15.dp, 15.dp)
                    ){
                        composable(route = "login") { LoginScreen(navController) }
                        composable(route = "menu")  {

                            val nome   : String? = navController.previousBackStackEntry?.savedStateHandle?.get("nome")
                            val codigo : Int? = navController.previousBackStackEntry?.savedStateHandle?.get("codigo")

                            val usuario = Usuario(nome = nome!!, codigo = codigo!!)

                            MenuScreen(navController, usuario)
                        }
                        composable(route = "rankPilarESG")  {

                            val codigo : Int? = navController.previousBackStackEntry?.savedStateHandle?.get("codigo")

                            RankESGSCreen(codigo!!)
                        }
                    }
                }
            }
        }
    }
}