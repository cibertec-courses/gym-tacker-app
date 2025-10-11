package edu.pe.cibertec.gymtrakcer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.pe.cibertec.gymtrakcer.data.local.entity.SesionEntity
import edu.pe.cibertec.gymtrakcer.data.repository.SesionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SesionViewModel(
    private val repository: SesionRepository
): ViewModel(){
    private val _sesison = MutableStateFlow<List<SesionEntity>>(emptyList())
    val sesion: StateFlow<List<SesionEntity>> = _sesison.asStateFlow()

    private val _seriesActuales = MutableStateFlow<List<SesionEntity>>(emptyList())
    val seriesActuales: StateFlow<List<SesionEntity>> = _seriesActuales.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Estado Error
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        cargarSesiones()
    }

    private fun cargarSesiones() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getAllSesiones().collect {
                    lista -> _sesison.value = lista
                }
            }catch (e: Exception){
                _error.value = "Error al cargar sesiones: ${e.message}"

            }finally {
                _isLoading.value = false
            }
        }
    }
}