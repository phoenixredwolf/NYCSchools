package com.phoenixredwolf.barretvogtmannycschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.phoenixredwolf.barretvogtmannycschools.ui.SchoolApp
import com.phoenixredwolf.barretvogtmannycschools.ui.theme.BarretVogtmanNYCSchoolsTheme
import com.phoenixredwolf.barretvogtmannycschools.ui.viewmodel.MainViewModel


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllSchools()
        setContent {
            BarretVogtmanNYCSchoolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SchoolApp(viewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BarretVogtmanNYCSchoolsTheme {
//        SchoolApp()
    }
}