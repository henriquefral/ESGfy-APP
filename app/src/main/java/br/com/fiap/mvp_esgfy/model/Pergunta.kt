package br.com.fiap.mvp_esgfy.model

class Pergunta (
    val codigo : Int? = null,
    val titulo : String,
    val subTitulo : String = "",
    val tipo : String,
    val pontos : Int,
    val usuario : Int? = null,
    val escolhas : List<String>?,
    var resposta : String = ""
) {
}