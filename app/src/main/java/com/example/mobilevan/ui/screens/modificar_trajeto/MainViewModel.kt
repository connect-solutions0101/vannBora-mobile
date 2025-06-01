package com.example.mobilevan.ui.screens.modificar_trajeto;

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobilevan.config.RetrofitConfig
import com.example.mobilevan.service.TrajetoService
import com.example.mobilevan.service.dto.DependenteDTO
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.flow.firstOrNull

class MainViewModel : ViewModel() {

    var nomeUsuario by mutableStateOf("")
    var token by mutableStateOf("")
    var userId by mutableStateOf(0)

    val listaAlunosTrajeto = mutableStateListOf<DependenteDTO>()
    val listaAlunosTrajetoAtual = mutableStateListOf<DependenteDTO>()

    var iniciarTrajeto by mutableStateOf(false)

    suspend fun onScreenLoad(context: Context, trajetoId: String?) {
        token = TokenStore.getToken(context).firstOrNull() ?: ""
        userId = TokenStore.getUserId(context).firstOrNull() ?: 0
        nomeUsuario = TokenStore.getUserName(context).firstOrNull() ?: ""

        buscarDependentesTrajeto(trajetoId, token)
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
                        listaAlunosTrajeto.addAll(trajeto.trajetoDependentes.map {
                            DependenteDTO(
                                id = it.responsavelDependente.dependenteId,
                                nome = it.responsavelDependente.dependente.nome,
                                responsaveis = listOf(it.responsavelDependente),
                                turno = it.responsavelDependente.dependente.turno,
                                escola = it.responsavelDependente.dependente.escola,
                            )
                        })

                        listaAlunosTrajetoAtual.addAll(
                            trajeto.trajetoDependentes.map {
                                DependenteDTO(
                                    id = it.responsavelDependente.dependenteId,
                                    nome = it.responsavelDependente.dependente.nome,
                                    responsaveis = listOf(it.responsavelDependente),
                                    turno = it.responsavelDependente.dependente.turno,
                                    escola = it.responsavelDependente.dependente.escola,
                                )
                            }
                        )
                    }
                } else{
                    println("Error: ${response.raw().code}")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun aoClicarCardAluno(
        aluno: DependenteDTO,
        isSelected: Boolean
    ) {
        if(isSelected){
            listaAlunosTrajetoAtual.add(aluno)
        }else{
            val alunoRemover = listaAlunosTrajetoAtual.find { it.id == aluno.id }
            listaAlunosTrajetoAtual.remove(alunoRemover)
        }
    }

    fun isAlunoSelecionado(aluno: DependenteDTO): Boolean {
        return listaAlunosTrajetoAtual.contains(aluno)
    }

    fun onIniciarTrajetoClick(){
        iniciarTrajeto = true
    }
}
