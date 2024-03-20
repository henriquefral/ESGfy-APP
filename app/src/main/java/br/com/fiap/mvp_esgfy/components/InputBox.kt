package br.com.fiap.mvp_esgfy.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mvp_esgfy.ui.theme.CustomBlackIc
import br.com.fiap.mvp_esgfy.ui.theme.CustomGrayBt
import br.com.fiap.mvp_esgfy.ui.theme.JetBrainsMono

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBox (
    modifier: Modifier = Modifier,
    label: String = "",
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit = {},
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
) {
    Column {
        if ( label != "" )
        {
            Text(text = label, fontFamily = JetBrainsMono)
            Spacer(modifier = Modifier.height(8.dp))
        }
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = TextStyle(
                color = Color.White,
                fontFamily = JetBrainsMono,
                fontSize = 15.sp,
            ),
            enabled = enabled,
            readOnly = readOnly,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = modifier.height(25.dp),
            decorationBox = { TextFieldDefaults.DecorationBox(
                value = value,
                isError = error,
                innerTextField = it,
                enabled = true,
                singleLine = true,
                trailingIcon = trailingIcon,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                contentPadding = PaddingValues(start = 3.dp, end = 0.dp),
                colors = TextFieldDefaults.colors(
                    disabledTrailingIconColor = CustomBlackIc,
                    unfocusedTrailingIconColor = CustomBlackIc,
                    focusedContainerColor = CustomGrayBt,
                    errorContainerColor = CustomGrayBt,
                    unfocusedContainerColor = CustomGrayBt,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledContainerColor = CustomGrayBt,
                ),
            )
            }
        )
    }
}