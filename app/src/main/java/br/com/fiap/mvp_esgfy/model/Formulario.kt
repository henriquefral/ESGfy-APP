package br.com.fiap.mvp_esgfy.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Formulario (
    val codigo    : Int? = null,
    val nome      : String,
    val descricao : String? = null,
    val emissao   : String = LocalDateTime.now().format(DateTimeFormatter
                                                      .ofPattern("dd/MM/yyyy")),
    val finalizacao : String? = null,
    val perguntas   : List<Pergunta>? = null,
    val usuario     : Int? = null,
    val responsavel : Int? = null
) {
}