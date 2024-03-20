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
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.fiap.mvp_esgfy.answerFormulario.AnswerFormularioScreen
import br.com.fiap.mvp_esgfy.createFormulario.CreateFormularioScreen
import br.com.fiap.mvp_esgfy.formulario.FormularioScreen
import br.com.fiap.mvp_esgfy.model.Formulario
import br.com.fiap.mvp_esgfy.ui.theme.MVP_ESGFYTheme
import com.google.gson.Gson
import com.google.gson.GsonBuilder

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
                        startDestination = "formulario",
                        modifier = Modifier.padding(15.dp, 15.dp, 15.dp, 15.dp)
                    ){
                        composable(route = "formulario") { FormularioScreen(navController) }
                        composable(route = "createFormulario") { CreateFormularioScreen(navController) }
                        composable(
                            route = "answerFormulario/{formulario}",
                            arguments = listOf(
                                navArgument("formulario") { // Notice over here
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val gson: Gson    = GsonBuilder().create()
                            val formularioJson= it.arguments?.getString("formulario")
                            val formulario    = gson.fromJson(formularioJson, Formulario::class.java)

                            AnswerFormularioScreen(navController, formulario)
                        }
                    }
                }
            }
        }
    }
}