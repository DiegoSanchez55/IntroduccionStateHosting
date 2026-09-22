package sanchez.diego.introduccion.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextField
import androidx.compose.material3.AssistChip


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.text.font.FontWeight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FichaEstudiante() {



    // ESTADO (VARIABLES GLOBALES QUE CAMBIARAN CON EL TIEMPO)
    var nombre by rememberSaveable { mutableStateOf("") }
    var carrera by rememberSaveable { mutableStateOf("") }
    var semestre by rememberSaveable { mutableStateOf(0) }
    var likes by rememberSaveable { mutableStateOf(0) }



    // VENTANA PRINCIPAL (TODOS LOS ELEMENTOS VISIBLES EN PANTALLA)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Mi ficha")
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {



            //PARTE 1: CARTA CON ICONO Y DATOS
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(2f)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    // FOTO
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    ) {
                        val inicial = nombre.firstOrNull()?.toString() ?: "?"

                        Text(
                            text = inicial.uppercase(),
                            fontSize = 50.sp
                        )
                    }

                    // DATOS (NOMBRE, CARRERA Y SEMESTRE)
                    Text(
                        text = if (nombre.isEmpty()) "Sin nombre" else nombre,
                        fontSize = 30.sp
                    )

                    Text(
                        text = "Carrera: $carrera",
                        fontSize = 20.sp
                    )

                    Text(
                        text = "Semestre: $semestre",
                        fontSize = 20.sp
                    )
                }
            }



            //PARTE 2: ESPACIO PARA RELLENAR DATOS
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // NOMBRE
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre Completo") },
                    modifier = Modifier.fillMaxWidth()
                )

                // CARRERA
                TextField(
                    value = carrera,
                    onValueChange = { carrera = it },
                    label = { Text("Carrera") },
                    modifier = Modifier.fillMaxWidth()
                )
            }



        //PARTE 3: CHIPS DE SEMESTRES

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = { semestre = 7 },
                    label = { Text("7°") },
                    modifier = Modifier.weight(1f)
                )

                AssistChip(
                    onClick = { semestre = 8 },
                    label = { Text("8°") },
                    modifier = Modifier.weight(1f)
                )

                AssistChip(
                    onClick = { semestre = 9 },
                    label = { Text("9°") },
                    modifier = Modifier.weight(1f)
                )
            }



        //PARTE 4: CONTADOR DE ME GUSTA

            //CARTA CON CONTADOR
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // FILA: MENOS - NUMERO - MAS
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        // BOTON DE SUMA
                        OutlinedButton(
                            onClick = { if (likes > 0) likes-- },
                        ) {
                            Text("-", fontSize = 20.sp)
                        }

                        Text(
                            text = "$likes",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // BOTON DE RESTA
                        OutlinedButton(
                            onClick = { likes++ },
                        ) {
                            Text("+", fontSize = 20.sp)
                        }
                    }

                    // ETIQUETA
                    Text(
                        text = "Me gusta",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }


            // BOTON DE RESET
            Button(
                onClick = { likes = 0 },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text("Limpiar")
            }


        }

    }
}