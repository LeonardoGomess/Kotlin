package org.example.br.com.alura.alugames.modelo

data class Game(val titulo:String, val capa:String) {
    var descricao:String? = null

    override fun toString(): String {
        return "My Game:\n" +
                "Titulo: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao \n"
    }
}