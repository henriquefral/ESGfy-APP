package br.com.fiap.mvp_esgfy.model

class Fornecedor (
    var codigo      : Int = 0,
    var usuario     : Usuario = Usuario(),
    var nome        : String = "",
    var razaoSocial : String = "",
    var cgc         : String = ""
) {
}