package com.example.mobilevan.ui.screens.criar_trajeto

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobilevan.config.RetrofitConfig
import com.example.mobilevan.enums.Periodo
import com.example.mobilevan.service.TrajetoService
import com.example.mobilevan.service.dto.TrajetoDTO
import com.example.mobilevan.service.dto.TrajetoRequestDto
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.flow.firstOrNull

class MainViewModel : ViewModel() {
    var nomeTrajeto by mutableStateOf("")
    val periodoTrajeto = mutableStateOf<Periodo?>(null)

    var trajetoCriado by mutableStateOf(false)
    var erroCriarTrajeto by mutableStateOf<String?>(null)
    var trajetoRetorno by mutableStateOf<TrajetoDTO?>(null)

    suspend fun onAdicionarNovoTrajetoClick(
        context: Context
    ): Boolean {
        if (nomeTrajeto.isEmpty()) {
            erroCriarTrajeto = "Nome do trajeto não pode ser vazio"
            return false
        }

        if (periodoTrajeto.value == null) {
            erroCriarTrajeto = "Período do trajeto não pode ser vazio"
            return false
        }

        val userId = TokenStore.getUserId(context).firstOrNull()
        val token = TokenStore.getToken(context).firstOrNull()

        if (userId == null || token.isNullOrEmpty()) {
            erroCriarTrajeto = "Usuário não autenticado"
            return false
        }

        val success = novoTrajeto(context, userId, token)

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
    private suspend fun novoTrajeto(context: Context, userId: Int?, token: String?): Boolean{
        val authApi = RetrofitConfig.instance.create(TrajetoService::class.java)

        val request = TrajetoRequestDto(
            nome = nomeTrajeto,
            periodo = periodoTrajeto.value!!,
            trajetoDependentes = emptyList(),
            proprietarioServicoId = userId!!
        )
        return try {
            val response = authApi.criarTrajeto(
                token = "Bearer $token",
                trajetoRequestDto = request
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    trajetoRetorno = it
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
