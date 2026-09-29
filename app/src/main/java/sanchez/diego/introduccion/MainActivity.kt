package sanchez.diego.introduccion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.contextmenu.modifier.appendTextContextMenuComponents
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sanchez.diego.introduccion.composables.ContadorCafes
import sanchez.diego.introduccion.composables.FichaAlumno
import sanchez.diego.introduccion.composables.FichaEstudiante
import sanchez.diego.introduccion.composables.ListaCompras
import sanchez.diego.introduccion.composables.PantallaPerfil
import sanchez.diego.introduccion.ui.theme.IntroduccionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroduccionTheme {
                FichaAlumno()
            }
        }
    }
}



// FUNCIONES DE PRUEBA
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun miBoton(){
    Botton(onClick = {}){
        Text(text = "Soy un boton")
    }
}

*/




@Composable
fun Botton(onClick: () -> Unit, content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroduccionTheme {
        FichaEstudiante()
    }
}