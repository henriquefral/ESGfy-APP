package br.com.fiap.mvp_esgfy.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class Resposta (
    val codigo    : Int? = null,
    val formulario: Formulario,
    val pergunta  : Pergunta,
    val resposta  : String? = null,
    val emissao   : String = LocalDateTime.now().format(DateTimeFormatter
                                                        .ofPattern("dd/MM/yyyy")),
    val pontos    : Int? = null,
    val resultado : Int? = null,
    val usuario   : Int? = null
) {

}