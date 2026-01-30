package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue


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

@Composable
fun MainScreen() {

    var vitality by remember { mutableIntStateOf(10) }
    var dexterity by remember { mutableIntStateOf(10) }
    var wisdom by remember { mutableIntStateOf(10) }

    val total = vitality + dexterity + wisdom



    Scaffold(
        topBar = {
            Text(
                text = "RPG Dice Roller",
                modifier = Modifier.padding(16.dp)

            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Estadisticas del dado (1 - 20)")

            Text("Vitality: $vitality")
            Text("Dexterity: $dexterity")
            Text("Wisdom: $wisdom")
            Text("Total: $total")
        }
    }
}

