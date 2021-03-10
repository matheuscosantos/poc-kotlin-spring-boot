package br.com.mcos.pockotlinspringboot

import br.com.mcos.pockotlinspringboot.controller.dto.NovoJogoRequest
import br.com.mcos.pockotlinspringboot.domain.Jogo
import br.com.mcos.pockotlinspringboot.repository.JogoRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class TestJogo(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val mapper: ObjectMapper,
    @Autowired private val repository: JogoRepository
){

    @BeforeEach
    fun setup() {
        val jogo1 = Jogo(1, "Neverwinter", "0.00".toBigDecimal(), 2015)
        val jogo2 = Jogo(2, "Star Wars - Falling Order", "50.00".toBigDecimal(), 2019)
        repository.save(jogo1)
        repository.save(jogo2)
    }

    @Test
    fun deveCriarUmJogoERetornar400(): Unit {
        val novoJogo = NovoJogoRequest(
            "Half-Life 3",
            "120.00".toBigDecimal(),
            2023
        )

        val jogoJson = mapper.writeValueAsString(novoJogo)
        val request = MockMvcRequestBuilders
                            .post("/api/jogos")
                            .content(jogoJson)
                            .contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun deveSolicitarAListagemDeTodosJogosCriadosERetornar400(): Unit{
        val request = MockMvcRequestBuilders
            .get("/api/jogos")
            .contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun deveSolicitarABuscaDeUmJogoPorIdERetornar400(): Unit{
        val jogo1 = Jogo(1, "Neverwinter", "0.00".toBigDecimal(), 2015)
        val jogo2 = Jogo(2, "Star Wars - Falling Order", "50.00".toBigDecimal(), 2019)

        val jsonResponse = """{"id":2,"titulo":"Star Wars - Falling Order","preco":50.00,"ano":2019}"""

        repository.save(jogo1)
        repository.save(jogo2)

        val request = MockMvcRequestBuilders
            .get("/api/jogos/2")
            .contentType(MediaType.APPLICATION_JSON)

        mockMvc
            .perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(jsonResponse))
    }

    @Test
    fun deveSolicitarAAtualizacaoDeUmJogoERetornar400(): Unit{

        val requestJogoAtualizado = NovoJogoRequest(
            "Star Wars - Falling Order - Atualizado",
            "50.00".toBigDecimal(),
            2019
        )

        val jogoJson = mapper?.writeValueAsString(requestJogoAtualizado)

        val jsonResponse = """{"id":2,
            |"titulo":"Star Wars - Falling Order - Atualizado",
            |"preco":50.00,"ano":2019}""".trimMargin()

        val request = MockMvcRequestBuilders
            .put("/api/jogos/2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jogoJson)

        mockMvc
            .perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(jsonResponse))
    }

    @Test
    fun deveSolicitarADelecaoDeUmJogoERetornar400(): Unit{
        val jogo1 = Jogo(1, "Neverwinter", "0.00".toBigDecimal(), 2015)

        repository.save(jogo1)

        val requestDelete = MockMvcRequestBuilders
            .delete("/api/jogos/1")
            .contentType(MediaType.APPLICATION_JSON)

        val requestBusca = MockMvcRequestBuilders
            .get("/api/jogos/1")
            .contentType(MediaType.APPLICATION_JSON)

        mockMvc
            .perform(requestBusca)
            .andExpect(MockMvcResultMatchers.status().isOk)

        mockMvc
            .perform(requestDelete)
            .andExpect(MockMvcResultMatchers.status().isOk)

        mockMvc
            .perform(requestBusca)
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}
