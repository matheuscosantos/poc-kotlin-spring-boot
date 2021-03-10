package br.com.mcos.pockotlinspringboot.controller

import br.com.mcos.pockotlinspringboot.controller.dto.JogoResponse
import br.com.mcos.pockotlinspringboot.controller.dto.NovoJogoRequest
import br.com.mcos.pockotlinspringboot.domain.Jogo
import br.com.mcos.pockotlinspringboot.repository.JogoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@RestController
@RequestMapping("api/jogos")
class JogoController(val repository: JogoRepository) {

    @PostMapping
    fun cria(@RequestBody request: NovoJogoRequest) : JogoResponse {
        val novoJogo = request.paraJogo()
        val jogoSalvo = repository.save(novoJogo)
        return JogoResponse(jogoSalvo)
    }

    @GetMapping
    fun buscaTodos() = ResponseEntity.ok(repository.findAll())

    @GetMapping("{id}")
    fun buscaPorId(@PathVariable id: Long) = ResponseEntity.of(repository.findById(id))

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody jogo: Jogo): ResponseEntity<JogoResponse>{
        val possivelJogo = repository.findById(id)
        val jogoParaSalvar = possivelJogo
            .orElseThrow{ RuntimeException("Jogo com o $id não foi encontrado.") }
            .copy(titulo = jogo.titulo, preco = jogo.preco, ano = jogo.ano)
        val jogoAtualizado = repository.save(jogoParaSalvar)
        return ResponseEntity.ok(JogoResponse(jogoAtualizado))
    }

    @DeleteMapping("{id}")
    fun deletaPorId(@PathVariable id: Long) = repository
        .findById(id)
        .ifPresent { repository.delete(it)}
}