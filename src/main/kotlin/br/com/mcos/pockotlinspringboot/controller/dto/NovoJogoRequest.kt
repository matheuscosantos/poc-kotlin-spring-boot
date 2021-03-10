package br.com.mcos.pockotlinspringboot.controller.dto

import br.com.mcos.pockotlinspringboot.domain.Jogo
import java.math.BigDecimal

data class NovoJogoRequest (
    val titulo: String,
    val preco: BigDecimal,
    val ano: Int
){
    fun paraJogo() = Jogo(
        titulo = this.titulo,
        preco = this.preco,
        ano = this.ano
    )
}