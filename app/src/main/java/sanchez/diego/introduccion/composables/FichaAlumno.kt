package sanchez.diego.introduccion.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// PANTALLA PRINCIPAL: GUARDA LOS DATOS Y CONECTA LA TARJETA CON EL FORMULARIO
@Composable
fun FichaAlumno() {
    // VARIABLES DE ESTADO: AL CAMBIAR, LA PANTALLA SE ACTUALIZA SOLA
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableIntStateOf(0) }
    var ciudad by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var isFavorite by remember { mutableStateOf(false) }

    // NECESARIOS PARA MOSTRAR EL SNACKBAR (MENSAJE EMERGENTE)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                // AL PRESIONAR: MUESTRA EL MENSAJE Y CAMBIA EL COLOR DE LA TARJETA
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Ficha Actualizada",
                            duration = SnackbarDuration.Short
                        )
                    }
                    isFavorite = !isFavorite
                }
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TarjetaDatos(nombre, edad, ciudad, correo, isFavorite)
            // CADA CAMPO ACTUALIZA SU VARIABLE CUANDO EL USUARIO ESCRIBE
            CardFormulario(
                nombre = nombre,
                edad = edad,
                ciudad = ciudad,
                correo = correo,
                onNombreChange = { nombre = it },
                onEdadChange = { edad = it },
                onCiudadChange = { ciudad = it },
                onCorreoChange = { correo = it }
            )
        }
    }
}

// TARJETA QUE MUESTRA LOS DATOS ESCRITOS Y CAMBIA A CYAN SI ES FAVORITO
@Composable
fun TarjetaDatos(
    nombre: String,
    edad: Int,
    ciudad: String,
    correo: String,
    isFavorite: Boolean
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(200.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(if (isFavorite) Color.Cyan else Color.Transparent)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // SI EL CAMPO ESTÁ VACÍO, MUESTRA UN TEXTO POR DEFECTO
                Row {
                    Text("Nombre: ", Modifier.padding(bottom = 8.dp))
                    Text(nombre.ifEmpty { "Sin Nombre" }, Modifier.padding(bottom = 8.dp))
                }
                Row {
                    Text("Edad: ", Modifier.padding(bottom = 8.dp))
                    Text("$edad años", Modifier.padding(bottom = 8.dp))
                }
                Row {
                    Text("Ciudad: ", Modifier.padding(bottom = 8.dp))
                    Text(ciudad.ifEmpty { "Sin Ciudad" }, Modifier.padding(bottom = 8.dp))
                }
                Row {
                    Text("Correo: @", Modifier.padding(bottom = 8.dp))
                    Text(correo.ifEmpty { "Sin Correo" }, Modifier.padding(bottom = 8.dp))
                }
            }
        }
    }
}

// FORMULARIO CON LOS CAMPOS DE TEXTO PARA CAPTURAR LOS DATOS
@Composable
fun CardFormulario(
    nombre: String,
    edad: Int,
    ciudad: String,
    correo: String,
    onNombreChange: (String) -> Unit,
    onEdadChange: (Int) -> Unit,
    onCiudadChange: (String) -> Unit,
    onCorreoChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            singleLine = true,
            placeholder = { Text("Diego Sánchez") },
            shape = CircleShape,
            label = { Text("Nombre Del Alumno") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        )
        OutlinedTextField(
            // SI LA EDAD ES 0 MUESTRA EL CAMPO VACÍO, Y SOLO ACEPTA NÚMEROS VÁLIDOS
            value = if (edad == 0) "" else edad.toString(),
            onValueChange = { nuevoValor ->
                if (nuevoValor.isEmpty()) {
                    onEdadChange(0)
                } else {
                    nuevoValor.toIntOrNull()?.let { onEdadChange(it) }
                }
            },
            singleLine = true,
            placeholder = { Text("20") },
            shape = CircleShape,
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        )
        OutlinedTextField(
            value = ciudad,
            onValueChange = onCiudadChange,
            singleLine = true,
            placeholder = { Text("Guadalajara") },
            shape = CircleShape,
            label = { Text("Ciudad") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        )
        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange,
            singleLine = true,
            placeholder = { Text("william.henry.harrison@example-pet-store.com") },
            shape = CircleShape,
            label = { Text("Correo") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// VISTA PREVIA PARA VER LA PANTALLA
@Composable
@Preview
fun FichaAlumnoPreview() {
    FichaAlumno()
}