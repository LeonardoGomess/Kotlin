package org.example.br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.servicos.ConsumoAPI
import org.example.br.com.alura.alugames.modelo.Game
import transformarEmIdade
import java.util.*


fun main() {

    val scanner = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(scanner)
    println("Cadastro concluido!")
    println(gamer)
    println("Idade do Gamer: " + gamer.dataNascimento?.transformarEmIdade())


    do {
        println("Digite um codigo de jogo para buscar: ")
        val busca = scanner.nextLine()

        val buscaApi = ConsumoAPI()
        val informacaoJogo = buscaApi.buscaJogo(busca)


        var myGame: Game? = null
        val resultado = runCatching {
            myGame = Game(informacaoJogo.info.title, informacaoJogo.info.thumb)
        }

        resultado.onFailure {
            println("Jogo nao encontrado , tente outro id")
        }

        resultado.onSuccess {
            println("Deseja inserir um descrição personalizada? S/N")
            val opcao = scanner.nextLine()

            if (opcao.equals("s" , true)){
                println("Insira a descrição personalizada para o jogo: ")
                val  descricaoPersonalizada = scanner.nextLine()
                myGame?.descricao = descricaoPersonalizada
            }else {
                myGame?.descricao = myGame?.titulo
            }
            gamer.jogosBuscados.add(myGame)
        }

        println("Deseja Buscar um novo jogo? S/N")
        val resposta = scanner.nextLine()

    } while (resposta.equals("s",true))

    println("Jogos buscados: ")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenado por titulo: ")
    gamer.jogosBuscados.sortBy {it?.titulo}

    gamer.jogosBuscados.forEach {
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman",true) ?: false
    }
    println("\n Jogos Filtrados")
    println(jogosFiltrados)


    println("Deseja excluir algum jogo da lista original(Lista começa do 0)? S/N")
    val option = scanner.nextLine()

    if (option.equals("s",true)){
        println(gamer.jogosBuscados)
        println("\n Informe a posicao do jogo que deseja excluir: ")
        val posicao = scanner.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("\n Lista atualizada")
    println(gamer.jogosBuscados)

    println("Busca finalizada!")

}

