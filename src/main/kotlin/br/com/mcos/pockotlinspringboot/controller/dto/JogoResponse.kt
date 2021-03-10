package br.com.mcos.pockotlinspringboot.controller.dto

import br.com.mcos.pockotlinspringboot.domain.Jogo
import java.math.BigDecimal

class JogoResponse {
    var id: Long
    var titulo: String
    var preco: BigDecimal
    var ano: Int

    constructor(jogo: Jogo) {
        this.id = jogo.id!!
        this.titulo = jogo.titulo
        this.preco = jogo.preco
        this.ano = jogo.ano
    }
}