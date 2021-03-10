package br.com.mcos.pockotlinspringboot.controller.dto

import br.com.mcos.pockotlinspringboot.domain.Jogo

data class NovoJogoRequest (
    val titulo: String,
    val preco: Int,
    val ano: Int
){
    fun paraJogo() = Jogo(
        titulo = this.titulo,
        preco = this.preco,
        ano = this.ano
    )
}