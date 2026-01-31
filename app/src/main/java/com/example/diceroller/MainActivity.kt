package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

// --- Layout (Column, Row, padding, tamaño, etc.) ---
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

// --- Material3 (componentes de UI: Scaffold, Text, Button, Card) ---
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

// --- Compose runtime (State: remember + mutableIntStateOf) ---
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// --- Cosas necesarias de UI (alineación, tipografía, medidas) ---
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//  animacion..
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope




import com.example.diceroller.ui.theme.DiceRollerTheme

/*
INSTRUCCIONES PARA EL PRIMER PROYECTO...
3 filas: Vitality, Dexterity, Wisdom
Cada fila tiene: nombre + valor + botón Roll
Abajo: Total = suma
Feedback:
Total < 30 → “Re-roll recommended!” rojo
Total ≥ 50 → “Godlike!”



creemos las constantes para los lados del dado*/
private const val MIN = 1
private const val MAX = 20


// MAIN ACTIVITY (ENTRADA)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                MainScreen()
            }
        }
    }
}

// PANTALLA PRINCIPAL
@Composable
fun MainScreen() {
    /*Las 3 stats requeridas*/
    // importante saber esto:
    // "remember" = conserva el valor aunque la pantalla se redibuje
    // "mutableIntStateOf" = si cambia el valor, Compose redibuja la UI automáticamente


    var vit by remember { mutableIntStateOf(10) }
    var dex by remember { mutableIntStateOf(10) }
    var wis by remember { mutableIntStateOf(10) }

    // total
    val total = vit + dex + wis



    // scope va a ser n uestro control remoto para las animaciones del roll
    val scope = rememberCoroutineScope()
    // courutines asi simulamos el roll sin bloquear con delay asi la forma correcta en compose


    // UI BASE: para topB y contenido
    Scaffold(
        topBar = {
            Text(
                text = "RPG Dice Roller",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(50.dp)
        ) {

            Text(
                text = "Estadisticas del dado (1 - 20)",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,

            )

            Spacer(Modifier.height(50.dp))


            // 3 FILAS: Vitality / Dexterity / Wisdom
            // name , value, roll
            StatRow(
                name = "STR",
                value = vit,
                roll = {
                    scope.launch {
                        repeat(10) {
                            vit = (MIN..MAX).random()
                            delay(100)
                        }

                    }



                } //  número aleatorio entre 1 y 20
            )

            Spacer(Modifier.height(20.dp))

            StatRow(
                name = "DEX",
                value = dex,
                roll = {
                    scope.launch {
                        repeat(10) {
                            dex = (MIN..MAX).random() //  número aleatorio entre 1 y 20
                            delay(100)
                        }

                    }

                }
            )


            Spacer(Modifier.height(20.dp))

            StatRow(
                name = "INT",
                value = wis,
                roll = {
                    scope.launch {
                        repeat(10) {
                            wis = (MIN..MAX).random() //  número aleatorio entre 1 y 20
                            delay(100)
                        }

                    }
                }
            )

            Spacer(Modifier.height(20.dp))
            Text(
                text = "Total: $total",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            when {
                total < 30 -> {
                    Text(
                        text = "Re-roll recommended!",
                        color = Color.Red,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                total >= 50 -> {
                    Text(
                        text = "Godlike!",
                        color = Color(0xFFFFC107),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }


            }

        }
    }
}

// STATROW = UNA FILA REUTILIZABLE
// es la funcion que dibuja la tarjeta con nombre roll value
@Composable
fun StatRow(
    name: String,
    value: Int,
    roll: () -> Unit // función que se ejecuta al hacer click en el botón
) {
    // la cajita visual
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x00FFF0FF)
        )
    ) {
        // acomodarlo horizontal
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
        // acomodar nombre y value
            Column {
                Text(
                    text = name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Value: $value",
                    fontSize = 16.sp
                )
            }
            // el botton, ejecuta la función onRoll que viene desde MainScreen
            Button(onClick = roll) {
                Text("Roll")
            }
        }
    }
}
