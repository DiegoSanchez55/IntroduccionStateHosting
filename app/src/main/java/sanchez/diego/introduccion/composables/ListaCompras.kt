package sanchez.diego.introduccion.composables
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text


data class Articulo(
    val id: Int,
    val nombre: String,
    val cantidad: Int
)


@Composable
fun ListaCompras(){
    val lista = remember {mutableStateListOf<Articulo>(
        Articulo(1,"Marcador",3),
        Articulo(2,"Borrador",1),
        Articulo(3,"Lápiz",5),
        Articulo(4,"Libreta",2)
    )}


    LazyColumn() {
        items(
            items = lista,
            key = {it.id}
        ) {articulo ->
            ItemArticulo(articulo = articulo)
        }
        }



}


@Composable
fun ItemArticulo(articulo: Articulo){
    Card(
        Modifier.padding(8.dp).fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = articulo.nombre
        )
    }


}