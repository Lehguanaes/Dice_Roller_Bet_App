package com.example.dice_roller

import android.os.Bundle
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dice_roller.ui.theme.Dice_RollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dice_RollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf( 1) }
    var aposta_usuario by remember { mutableStateOf("") }
    var resultado_aposta by remember { mutableStateOf("") }
    val context = LocalContext.current


    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(imageResource), contentDescription = result.toString())

        Button(
            onClick = { result = (1..6).random()
                resultado_aposta = verificacao_num_dados(aposta_usuario.toInt(), result)
                Toast.makeText(context, resultado_aposta, Toast.LENGTH_SHORT).show()
            },
        ) {
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = aposta_usuario,
            onValueChange = {aposta_usuario = it},
            label = { Text("Digite sua tentativa:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

    }
}

fun verificacao_num_dados(valor_aposta: Int, numero_dado: Int): String {
    if(valor_aposta in 1..6) {
        if(valor_aposta == numero_dado) {
            return  "Parabéns, você acertou o resultado!"
        } else {
            return "Que pena, você não acertou o resultado!"
        }
    } else {
        return "Os valores são entre 1 a 6!"
    }
}