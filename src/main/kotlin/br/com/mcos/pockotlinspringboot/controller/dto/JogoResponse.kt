package br.com.mcos.pockotlinspringboot.controller.dto

import br.com.mcos.pockotlinspringboot.domain.Jogo

class JogoResponse{
    var id: Long? = null
    var titulo: String = ""
    var preco: Int = 0
    var ano: Int = 0

    constructor(jogo: Jogo){
        this.id = jogo.id
        this.titulo = jogo.titulo
        this.preco = jogo.preco
        this.ano = jogo.ano
    }
}