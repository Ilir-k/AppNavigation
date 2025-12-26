package com.example.appnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appnavigation.ui.theme.AppNAvigationTheme
import com.example.scrollablelist.PhotoData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNAvigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            PhotoApp(navController = navController)
        }
        composable("contact") {
            ContactScreen(navController = navController)
        }
    }
}

@Composable
fun PhotoApp(navController: NavController) {
    val photoList = listOf(
        PhotoData(R.drawable.i1, "Photo 1"),
        PhotoData(R.drawable.i2, "Photo 2"),
        PhotoData(R.drawable.i3, "Photo 3"),
        PhotoData(R.drawable.i4, "Photo 4"),
        PhotoData(R.drawable.i5, "Photo 5"),
        PhotoData(R.drawable.i6, "Photo 6"),
        PhotoData(R.drawable.i7, "Photo 7"),
        PhotoData(R.drawable.i8, "Photo 8"),
        PhotoData(R.drawable.i9, "Photo 9"),
        PhotoData(R.drawable.i10, "Photo 10")
    )
    Column {
        Button(onClick = { navController.navigate("contact") }) {
            Text(text = "Contact Page")
        }
        PhotoList(photoList = photoList)
    }
}

@Composable
fun PhotoList(photoList: List<PhotoData>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(photoList) { photo ->
            PhotoListItem(photoData = photo)
        }
    }
}

@Composable
fun PhotoListItem(photoData: PhotoData, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = photoData.imageResourceId),
            contentDescription = photoData.description,
            modifier = Modifier.size(100.dp)
        )
        Text(text = photoData.description, modifier = Modifier.padding(start = 16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contact") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Greeting("Ilir", modifier = Modifier.padding(paddingValues))

    }
}

@Preview(showBackground = true)
@Composable
fun PhotoListPreview() {
    AppNAvigationTheme {
        val navController = rememberNavController()
        PhotoApp(navController)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.reu)
    Box(modifier) {
        Column(modifier)
        {
            Text(
                text = "Hello, from $name!",
                modifier = modifier.padding(all=16.dp),
                fontSize = 24.sp
            )
            Text(
                text = "Email: ilirk2@aol.com",
                modifier = modifier.padding(all=16.dp),
                fontSize = 24.sp
            )
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
