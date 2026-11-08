package com.example.netsalarycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var screen by remember { mutableStateOf(1) }

            var userName by remember { mutableStateOf("") }
            var edad by remember { mutableStateOf("") }
            var grupoProfesional by remember { mutableStateOf("") }
            var gradoDiscapacidad by remember { mutableStateOf("") }
            var numHijos by remember { mutableStateOf("") }
            var salarioBrutoAnual by remember { mutableStateOf("") }
            var numPagas by remember { mutableStateOf("") }
            var civilStatus by remember { mutableStateOf("") }

            var salarioNeto by remember { mutableStateOf("") }
            var retencionIRPF by remember { mutableStateOf("") }
            var posiblesDeducciones by remember { mutableStateOf("") }

            LaunchedEffect(Unit) {
                delay(5000)
                screen = 2
            }
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()), //to allow scrolling
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
            ){
                when(screen){ //choose what to show depending on the screen number
                    1 -> {
                        Image(
                            painter = painterResource(R.drawable.tax),
                            contentDescription = "TaxImg",
                            modifier = Modifier.size(200.dp)
                        )
                        Text("Bienvenido a la calculadora de salario neto\n",
                            color = Color.DarkGray,
                            fontSize = 32.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    2 -> {
                        Text("Cómo te llamas?", fontFamily = FontFamily.Default, fontSize = 28.sp)
                        OutlinedTextField(
                            value = userName,
                            onValueChange = {userName = it},
                            label = {Text("Tu nombre")}
                        )
                        Button(onClick = { screen = 3 }){
                            Text("Siguiente")
                        }
                    }
                    3 -> {
                        Text("Encantada, $userName. Calculamos tu salario neto.", color = Color.DarkGray, fontFamily = FontFamily.Default, fontSize = 32.sp)
                        Text("Para empezar, necesitamos tus datos", fontFamily = FontFamily.Default, fontSize = 16.sp)
                        OutlinedTextField(
                            value = edad,
                            onValueChange = {edad = it},
                            label = {Text("Edad")}
                        )
                        OutlinedTextField(
                            value = grupoProfesional,
                            onValueChange = {grupoProfesional = it},
                            label = {Text("Grupo Profesional")}
                        )
                        OutlinedTextField(
                            value = gradoDiscapacidad,
                            onValueChange = {gradoDiscapacidad = it},
                            label = {Text("Grado Discapacidad")}
                        )
                        OutlinedTextField(
                            value = numHijos,
                            onValueChange = {numHijos = it},
                            label = {Text("Número de hijos")}
                        )
                        OutlinedTextField(
                            value = salarioBrutoAnual,
                            onValueChange = {salarioBrutoAnual = it},
                            label = {Text("Salario bruto anual")}
                        )
                        OutlinedTextField(
                            value = numPagas,
                            onValueChange = {numPagas = it},
                            label = {Text("Número de pagas")}
                        )
                        OutlinedTextField(
                            value = civilStatus,
                            onValueChange = {civilStatus = it},
                            label = {Text("Estado civil")}
                        )
                        Button(onClick = {
                            val salarioNetoResult = (salarioBrutoAnual.toDoubleOrNull() ?: 0.0) / (numPagas.toIntOrNull() ?: 1)
                            salarioNeto = "✅ Salario neto: " + salarioNetoResult
                            val retencionIRPFResult = ((salarioBrutoAnual.toDoubleOrNull() ?: 0.0) / salarioNetoResult) * 100
                            retencionIRPF = "✅ Retencion IRPF: " + retencionIRPFResult
                            val posiblesDeduccionesResult = 300 + 100 * (numHijos.toIntOrNull() ?: 0)
                            posiblesDeducciones = "✅ Possibles Deducciones: " + posiblesDeduccionesResult
                            screen = 4
                        }){
                            Text("Listo! Calculamos tu salario neto")
                        }
                    }
                    4 -> {
                        Text("Aquí tienes tu salario neto", fontSize = 32.sp)
                        Text(
                            "✅ Salario Bruto Anual: " + salarioBrutoAnual,
                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                        Text(
                            salarioNeto,
                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                        Text(
                            retencionIRPF,
                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                        Text(
                            posiblesDeducciones,
                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}