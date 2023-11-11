package com.example.dogandcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dogandcat.ui.model.CatFactViewModel
import com.example.dogandcat.ui.model.DogImageViewModel
import com.example.dogandcat.ui.theme.DogAndCatTheme
import com.skydoves.landscapist.glide.GlideImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogAndCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: CatFactViewModel = viewModel()
                    val facts = viewModel.facts.collectAsState().value

                    val dogViewModel : DogImageViewModel = viewModel()
                    val dogUrl = dogViewModel.dogUrl.collectAsState().value

                    var fetchNew by remember { mutableStateOf(false) }

                    LaunchedEffect(fetchNew) {
                        //viewModel.fetchFacts()
                        dogViewModel.fetchDogImage()
                    }

//                    Column(
//                        modifier = Modifier.fillMaxSize(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.spacedBy(10.dp)
//                    ) {
//                        facts.forEach {
//                            Text(text = it.fact)
//                        }
//                        Button(onClick = { fetchNew = !fetchNew }) {
//                            Text(text = "Fetch New")
//                        }
//                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        GlideImage(
                            {dogUrl},
                            modifier = Modifier.heightIn(max = 500.dp),
                            loading = {
                                Text(text = "Just Wait Bro")
                            }
                        )
                        Button(onClick = { fetchNew = !fetchNew }) {
                            Text(text = "Fetch New")
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DogAndCatTheme {
        TODO(/*NOTHING*/)
    }
}