package br.com.fiap.mvp_esgfy.model

data class PilarESG (
    var codigo   : Int = 0,
    var nome     : String = "",
    var objetivoSustentavel : String = "",
    var usuario: Usuario = Usuario()
) {
}