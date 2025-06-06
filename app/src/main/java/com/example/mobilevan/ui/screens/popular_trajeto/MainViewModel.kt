package com.example.mobilevan.ui.screens.popular_trajeto

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobilevan.config.RetrofitConfig
import com.example.mobilevan.service.DependenteService
import com.example.mobilevan.service.TrajetoService
import com.example.mobilevan.service.dto.DependenteDTO
import com.example.mobilevan.service.dto.ResponsavelDTO
import com.example.mobilevan.service.request.DependenteResponsavelRequest
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.flow.firstOrNull

class MainViewModel : ViewModel() {
    var nomeUsuario by mutableStateOf("")

    var searchInput by mutableStateOf("")

    val listaAlunos = mutableStateListOf<DependenteDTO>()
    val listaAlunosParaSalvar = mutableStateListOf<DependenteResponsavelRequest>()
    var trajetoPopulado by mutableStateOf(false)

    val listaResponsaveis = mutableStateListOf<ResponsavelDTO>()
    var responsavelSelecionado by mutableStateOf<ResponsavelDTO?>(null)
    var dependenteId by mutableStateOf<Int?>(null)
    var showResponsavelDialog by mutableStateOf(false)

    var token by mutableStateOf("")
    var userId by mutableStateOf(0)

    suspend fun onScreenLoad(context: Context, trajetoId: String?) {
        token = TokenStore.getToken(context).firstOrNull() ?: ""
        userId = TokenStore.getUserId(context).firstOrNull() ?: 0
        nomeUsuario = TokenStore.getUserName(context).firstOrNull() ?: ""

        buscarDependentes(userId, token)
        buscarDependentesTrajeto(trajetoId, token)
        ordenarListaAlunos()
    }

    private suspend fun buscarDependentes(userId: Int?, token: String?) {
        val api = RetrofitConfig.instance.create(DependenteService::class.java)

        if (token.isNullOrEmpty() || userId == null) {
            println("Token or User ID is null")
            return
        }

        try {
            val response = api.getDependentes(userId.toString(), "Bearer $token")

            if (response.raw().code == 200) {
                response.body()?.let { dependentes ->
                    println("Dependentes: $dependentes")
                    listaAlunos.addAll(dependentes)
                }
            } else if (response.raw().code == 204) {
                println("No content")
            } else {
                println("Error: ${response.raw().code}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun popularTrajeto(trajetoId: String?, listaOrdenada: List<DependenteResponsavelRequest>) {
        val api = RetrofitConfig.instance.create(TrajetoService::class.java)

        if (trajetoId != null) {

            if (token.isEmpty()) {
                println("Token is null")
                return
            }

            try {
                val response = api.popularTrajeto(trajetoId, "Bearer $token", listaOrdenada)

                if (response.raw().code == 200) {
                    println("Trajeto populated successfully")
                    trajetoPopulado = true
                } else {
                    println("Error: ${response.raw().code}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun buscarDependentesTrajeto(trajetoId: String?, token: String? = null) {
        val api = RetrofitConfig.instance.create(TrajetoService::class.java)

        if(trajetoId != null){

            if (token.isNullOrEmpty()) {
                println("Token is null")
                return
            }

            try {
                val response = api.getTrajeto(trajetoId, "Bearer $token")
                if(response.raw().code == 200){
                    response.body()?.let { trajeto ->
                        listaAlunosParaSalvar.addAll(trajeto.trajetoDependentes.map {
                            DependenteResponsavelRequest(
                                idDependente = it.responsavelDependente.dependenteId,
                                idResponsavel = it.responsavelDependente.responsavelId,
                                ordem = it.ordem
                            )
                        })
                    }
                    println("Dependentes: ${listaAlunos.size}")
                } else{
                    println("Error: ${response.raw().code}")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    suspend fun onNovoTrajetoClick(trajetoId: String?) {
        ordenarListaAlunos()

        val listaParaSalvarComOrdem  = listaAlunos
            .mapIndexedNotNull { index, aluno ->
                val responsavel = listaAlunosParaSalvar.find { it.idDependente == aluno.id }
                responsavel?.copy(ordem = index)
            }

        if (trajetoId != null) {
            popularTrajeto(trajetoId, listaParaSalvarComOrdem )
        } else {
            println("Trajeto ID is null")
        }
    }

    fun aoConfirmarResponsavelAluno(){
        val ordem = listaAlunos.indexOfFirst { it.id == dependenteId }
        showResponsavelDialog = false
        listaAlunosParaSalvar.add(
            DependenteResponsavelRequest(
                idDependente = dependenteId!!,
                idResponsavel = responsavelSelecionado!!.id,
                ordem = ordem
            )
        )
        listaResponsaveis.clear()
    }

    fun aoClicarCardAluno(
        aluno: DependenteDTO,
        isSelected: Boolean
    ) {
        val ordem = listaAlunos.indexOfFirst { it.id == aluno.id }

        if(isSelected){
            if(aluno.responsaveis.size == 1){
                listaAlunosParaSalvar.add(
                    DependenteResponsavelRequest(
                        idDependente = aluno.id,
                        idResponsavel = aluno.responsaveis[0].responsavelId,
                        ordem = ordem
                    )
                )
            }else{
                showResponsavelDialog = true
                listaResponsaveis.addAll(aluno.responsaveis.map { it.responsavel })
                dependenteId = aluno.id
            }
        }else{
            val alunoRemover = listaAlunosParaSalvar.find { it.idDependente == aluno.id }
            listaAlunosParaSalvar.remove(alunoRemover)
            listaAlunos.find { it.id == aluno.id }?.ordem = null
        }

        ordenarListaAlunos()
    }

    fun isAlunoSelecionado(aluno: DependenteDTO): Boolean {
        return listaAlunosParaSalvar.any { it.idDependente == aluno.id }
    }

    fun ordenarListaAlunos() {
        listaAlunos.forEach { aluno ->
            val correspondente = listaAlunosParaSalvar.find { it.idDependente == aluno.id }
            if (correspondente != null) {
                aluno.ordem = correspondente.ordem
            }
        }

        listaAlunos.sortWith(compareBy { it.ordem ?: Int.MAX_VALUE })
    }

    fun moverAluno(from: Int, to: Int) {
        if (from == to) return

        listaAlunos.apply {
            add(to, removeAt(from))
        }

        // Atualiza a ordem de todos os alunos com base na posição
        listaAlunos.forEachIndexed { index, aluno ->
            aluno.ordem = index

            // Se o aluno estiver na listaParaSalvar, também sincroniza a ordem dele
            listaAlunosParaSalvar.find { it.idDependente == aluno.id }?.ordem = index
        }
    }
}