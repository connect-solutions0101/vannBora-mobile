package com.example.mobilevan.ui.screens.feature_criar_trajeto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var nomeUsuario by mutableStateOf("Roberto")
        private set

    val listaAlunos = mutableStateListOf<Pair<String, String>>(
        Pair("Aluno 1", "Escola A"),
        Pair("Aluno 2", "Escola B"),
        Pair("Aluno 3", "Escola C"),
        Pair("Aluno 4", "Escola A"),
        Pair("Aluno 5", "Escola D")
    )

    fun onNovoTrajetoClick() {
        println("Botão 'Novo Trajeto' clicado!")
    }

    fun aoClicarCardAluno(parAluno: Pair<String, String>) {
        println("Clicou no Card do Aluno: ${parAluno.first}")
    }
}