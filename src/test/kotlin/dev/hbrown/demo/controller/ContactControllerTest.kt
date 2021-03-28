package dev.hbrown.demo.controller

import dev.hbrown.demo.domain.Contact
import dev.hbrown.demo.dto.SearchCriteria
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.rsocket.context.LocalRSocketServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import strikt.api.expectThat
import strikt.assertions.get
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull

@SpringBootTest
internal class ContactControllerTest {

    lateinit var requester: RSocketRequester

    @Suppress("unused") // called by JUnit
    @BeforeAll
    internal fun `before all`(
        @Autowired builder: RSocketRequester.Builder,
        @LocalRSocketServerPort port: Int,
        @Autowired strategies: RSocketStrategies,
    ) {
        requester = builder.tcp("localhost", port)
    }

    @Test
    internal fun `should be able to find contact by name`() {
        val result: List<Contact>? = requester
            .route("v1.contact.search")
            .data(SearchCriteria(name = "brian"))
            .retrieveFlux(Contact::class.java)
            .log()
            .collectList()
            .block()


        expectThat(result)
            .isNotNull()
            .hasSize(1)[0]
            .get { id }
            .isEqualTo(2L)

    }
}
