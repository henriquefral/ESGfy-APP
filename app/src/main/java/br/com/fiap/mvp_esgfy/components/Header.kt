package br.com.fiap.mvp_esgfy.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mvp_esgfy.R

@Composable
fun Header (showBackButton: Boolean? = false, navController: NavController? = null) {
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),

        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(140.dp, 60.dp)
            )
            Card(
                shape = RoundedCornerShape(20),
                border = BorderStroke(width = 1.dp, color = Color(0xFF008F95)),
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile",
                    modifier = Modifier.size(65.dp),
                )
            }
        }
        if ( showBackButton!! ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { navController?.popBackStack() }) {
                Image(
                    painter = painterResource(id = R.drawable.goback),
                    contentDescription = "goBack",
                    modifier = Modifier.size(50.dp),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Voltar", fontSize = 24.sp)
            }
        }
    }
}