package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer

fun main(){
    var gamer1 = Gamer("leo" ,"wew@email.com")
    println(gamer1)


    gamer1.let {
        it.dataNascimento ="17/34/3232"
        it.usuario = "id"
    }.also {
        println(gamer1.idInterno)
    }
    println(gamer1)
}