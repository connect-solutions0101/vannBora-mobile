package com.example.mobilevan.ui.screens.trajeto

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilevan.config.RetrofitConfig
import com.example.mobilevan.service.EmbarqueService
import com.example.mobilevan.service.NorificacaoService
import com.example.mobilevan.service.TrajetoService
import com.example.mobilevan.service.dto.DependenteDTO
import com.example.mobilevan.service.dto.TrajetoDTO
import com.example.mobilevan.service.request.EmbarqueRequest
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

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

    suspend fun registrarEmbarque(context: Context, aluno: DependenteDTO) {
        val api = RetrofitConfig.instance.create(EmbarqueService::class.java)
        val apiNotificacao = RetrofitConfig.instance.create(NorificacaoService::class.java)
        val token = TokenStore.getToken(context).firstOrNull()
        val userId = TokenStore.getUserId(context).firstOrNull()

        if (token.isNullOrEmpty() || userId == null) {
            println("Token or User ID is null")
            return
        }

        var dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))

        val registro = EmbarqueRequest(
            dataHora = dataHora,
            tipo = "EMBARQUE",
            responsavelId = aluno.responsaveis[0].responsavelId,
            dependenteId = aluno.id
        )

        try {
            val response = api.registrar(
                "Bearer $token",
                registro
            )

            if (response.raw().code == 200) {
                println("Embarque registrado com sucesso")
            } else {
                println("Error ao registrar embarque: ${response.raw().code}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))

        try {
            val notificacao = "Embarque registrado para ${aluno.nome} às ${dataHora}."
            val response = apiNotificacao.enviarNotificacao("Bearer $token", notificacao)

            if (response.isSuccessful) {
                println("Notificação enviada com sucesso")
            } else {
                println("Erro ao enviar notificação: ${response.code()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun updateDependenteAtual(context: Context)  {
        if (trajetoDependentes.isNullOrEmpty()) return

        viewModelScope.launch {
            dependenteAtual?.let { aluno ->
                registrarEmbarque(context, aluno)
            } ?: run {
                println("Dependente atual é nulo")
            }
        }

        dependenteAtualNumber = (dependenteAtualNumber ?: 0) + 1

        if (dependenteAtualNumber!! >= trajetoDependentes!!.size) {
            trajetoFinalizado = true
            return
        }

        dependenteAtual = trajetoDependentes!![dependenteAtualNumber!!]
    }

    fun onConfirmClick(context: Context) {
        updateDependenteAtual(context)
    }

    fun onDismissRequest() {
        modalInformacoesAdicionaisVisible = false
    }

    fun onMaisInformacoesClick() {
        modalInformacoesAdicionaisVisible = true
    }
}