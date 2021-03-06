package br.com.mcos.pockotlinspringboot.domain

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Jogo(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null,
    val titulo: String,
    val preco: BigDecimal,
    val ano: Int,
)