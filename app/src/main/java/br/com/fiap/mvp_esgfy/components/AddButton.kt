package br.com.fiap.mvp_esgfy.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mvp_esgfy.ui.theme.CustomGrayBt
import br.com.fiap.mvp_esgfy.ui.theme.JetBrainsMono

@Composable
fun AddButton (title: String, onClick: () -> Unit) {
    Button(onClick = onClick, shape = RoundedCornerShape(10.dp), contentPadding = PaddingValues(10.dp, 0.dp, 10.dp, 0.dp),
           colors = ButtonDefaults.buttonColors(containerColor = CustomGrayBt),
           modifier = Modifier.shadow(8.dp), ) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Text(text = "+", fontSize = 35.sp,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            if ( title != "" ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = title, fontFamily = JetBrainsMono)
            }
        }
    }
}