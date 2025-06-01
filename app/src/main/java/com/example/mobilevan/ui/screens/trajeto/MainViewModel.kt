package com.example.mobilevan.ui.screens.trajeto

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobilevan.config.RetrofitConfig
import com.example.mobilevan.service.TrajetoService
import com.example.mobilevan.service.dto.DependenteDTO
import com.example.mobilevan.service.dto.TrajetoDTO
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.flow.firstOrNull

class MainViewModel: ViewModel(){

    var trajeto by mutableStateOf<TrajetoDTO?>(null)
    var trajetoDependentes by mutableStateOf<List<DependenteDTO>?>(null)

    var dependenteAtualNumber by mutableStateOf<Int?>(0)
    var dependenteAtual by mutableStateOf<DependenteDTO?>(null)

    var trajetoFinalizado by mutableStateOf(false)

    var modalInformacoesAdicionaisVisible by mutableStateOf(false)

    suspend fun onScreenLoad(context: Context, trajetoId: String) {
        val api = RetrofitConfig.instance.create(TrajetoService::class.java)
        val token = TokenStore.getToken(context).firstOrNull()
        val userId = TokenStore.getUserId(context).firstOrNull()

        if (token.isNullOrEmpty()  || userId == null) {
            println("Token or User ID is null")
            return
        }

        try {
            val response =  api.getTrajeto(trajetoId, "Bearer $token")

            if (response.raw().code == 200) {
                response.body()?.let { trajeto ->
                    println("Trajeto: $trajeto")
                    this.trajeto = trajeto
                }
            }  else {
                println("Error: ${response.raw().code}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (trajetoDependentes?.isNotEmpty() == true) {
            dependenteAtual = trajetoDependentes?.get(0)
        }
    }

    private fun updateDependenteAtual()  {
        if (trajetoDependentes.isNullOrEmpty()) return

        dependenteAtualNumber = (dependenteAtualNumber ?: 0) + 1

        if (dependenteAtualNumber!! >= trajetoDependentes!!.size) {
            trajetoFinalizado = true
            return
        }

        dependenteAtual = trajetoDependentes!![dependenteAtualNumber!!]
    }

    fun onConfirmClick() {
        updateDependenteAtual()
    }

    fun onDismissRequest() {
        modalInformacoesAdicionaisVisible = false
    }

    fun onMaisInformacoesClick() {
        modalInformacoesAdicionaisVisible = true
    }
}