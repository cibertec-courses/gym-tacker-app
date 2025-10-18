package edu.pe.cibertec.gymtrakcer

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
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.pe.cibertec.gymtrakcer.ui.screens.EjerciciosScreen
import edu.pe.cibertec.gymtrakcer.ui.theme.GymtrackerappTheme
import edu.pe.cibertec.gymtrakcer.ui.viewmodel.EjericioViewModel
import edu.pe.cibertec.gymtrakcer.ui.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val app = application as GymTrackerApplication

        val viewModelFactory = ViewModelFactory(
            ejercicioRepository = app.ejercicioRepository,
            sesionRepository = app.sesionRepository
        )

        setContent {
            GymtrackerappTheme {
                Scaffold (modifier = Modifier.fillMaxSize())
                { innerpadding ->
                    val ejercicioViewModel:  EjericioViewModel = viewModel(
                        factory = viewModelFactory
                    )

                    EjerciciosScreen(viewModel =  ejercicioViewModel,
                        modifier = Modifier.padding(innerpadding)
                        )
                }
            }
        }
    }
}
