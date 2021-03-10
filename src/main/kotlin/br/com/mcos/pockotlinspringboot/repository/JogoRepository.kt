package br.com.mcos.pockotlinspringboot.repository

import br.com.mcos.pockotlinspringboot.domain.Jogo
import org.springframework.data.jpa.repository.JpaRepository

interface JogoRepository : JpaRepository<Jogo, Long>{}