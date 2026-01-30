package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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


