package br.com.fiap.mvp_esgfy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mvp_esgfy.ui.theme.CustomBlueBt
import br.com.fiap.mvp_esgfy.ui.theme.CustomOrange
import br.com.fiap.mvp_esgfy.ui.theme.PoppinsRegular

@Composable
fun SaveButton (title: String, onClick: () -> Unit = {}, modifier : Modifier) {
    Box(modifier = modifier.clip(RoundedCornerShape(33.dp)).background(Color.Cyan).size(150.dp, 55.dp)
    ) {
        Column(modifier = Modifier
                                    .background(CustomOrange)
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(10.dp))
                                    .padding(1.dp)
        ) {
            Button( onClick = onClick,
                    contentPadding = PaddingValues(10.dp, 0.dp, 10.dp, 0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CustomBlueBt),
                    modifier = Modifier.fillMaxSize()
            ) {
                Row (verticalAlignment = Alignment.CenterVertically ) {
                    Text(text = title, fontFamily = PoppinsRegular, fontSize = 24.sp)
              }
            }
        }
    }
}