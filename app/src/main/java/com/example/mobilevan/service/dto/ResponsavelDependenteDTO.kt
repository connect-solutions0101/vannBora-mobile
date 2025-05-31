package com.example.mobilevan.service.dto

import com.example.mobilevan.enums.TipoResponsavel
import java.io.Serializable

data class ResponsavelDependenteDTO (
    val responsavelId: Int,
    val dependenteId: Int,
    val tipoResponsavel: TipoResponsavel,
    val responsavel: ResponsavelDTO,
    val dependente: DependenteDTO
) : Serializable
