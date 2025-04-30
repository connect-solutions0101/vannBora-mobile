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

    var dependenteAtualNumber by mutableStateOf<Int?>(0)
    var dependenteAtual by mutableStateOf<DependenteDTO?>(null)

    var trajetoFinalizado by mutableStateOf(false)

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

        if (trajeto?.trajetoDependentes?.isNotEmpty() == true) {
            dependenteAtual = trajeto!!.trajetoDependentes[0].responsavelDependente.dependente
        }
    }

    private fun updateDependenteAtual()  {
        dependenteAtualNumber = dependenteAtualNumber?.plus(1)

        if(dependenteAtualNumber!! >= (trajeto?.trajetoDependentes?.size ?: 0)) {
            dependenteAtualNumber = 0
            dependenteAtual = null
            trajetoFinalizado = true
            return
        }

        dependenteAtual = trajeto?.trajetoDependentes?.get(dependenteAtualNumber ?: 0)?.responsavelDependente?.dependente
    }

    fun onConfirmClick() {
        updateDependenteAtual()
    }
}