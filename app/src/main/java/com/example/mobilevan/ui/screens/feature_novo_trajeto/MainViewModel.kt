package com.example.mobilevan.ui.screens.feature_novo_trajeto

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobilevan.config.RetrofitConfig
import com.example.mobilevan.enums.Periodo
import com.example.mobilevan.service.TrajetoService
import com.example.mobilevan.service.dto.TrajetoRequestDto
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.flow.firstOrNull

class MainViewModel : ViewModel() {
    var nomeTrajeto by mutableStateOf("")
    var periodoTrajeto by mutableStateOf("")
    var trajetoCriado by mutableStateOf(false)
    var erroCriarTrajeto by mutableStateOf<String?>(null)

    suspend fun onAdicionarNovoTrajetoClick(
        context: Context,
        nomeTrajeto: String,
        periodoTrajeto: String
    ): Boolean {
        val success = novoTrajeto(context, nomeTrajeto, periodoTrajeto)
        if (success) {
            println("Trajeto criado com sucesso!")
            trajetoCriado = true
            erroCriarTrajeto = null
        } else {
            println("Falha ao criar o trajeto.")
            erroCriarTrajeto = "Erro ao criar o trajeto"
        }
        return success
    }
    private suspend fun novoTrajeto(context: Context, nomeTrajeto: String, periodoTrajeto: String): Boolean{
        val authApi = RetrofitConfig.instance.create(TrajetoService::class.java)

        val request = TrajetoRequestDto(
            nome = this.nomeTrajeto,
            periodo = Periodo.valueOf(this.periodoTrajeto),
            trajetoDependentes = emptyList(),
            proprietarioServicoId = TokenStore.getUserId(context).firstOrNull() ?: 0
        )
        return try {
            val response = authApi.criarTrajeto(
                token = "Bearer ${TokenStore.getToken(context)}",
                trajetoRequestDto = request
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    println("Trajeto criado com sucesso")
                    return true
                }
            }
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
