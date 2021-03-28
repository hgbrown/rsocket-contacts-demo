package dev.hbrown.demo.service

import dev.hbrown.demo.dto.SearchCriteria
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import strikt.api.expectThat
import strikt.assertions.get
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull

@SpringBootTest
internal class ContactServiceTest(
    @Autowired val cut: ContactService
) {

    @Test
    internal fun `should be able to find a match by name even when case is different`() {
        val result = cut
            .search(SearchCriteria(name = "brian"))
            .log()
            .collectList()
            .block()
        expectThat(result)
            .isNotNull()
            .hasSize(1)[0]
            .get { id }
            .isEqualTo(2L)

    }

    @Test
    internal fun `should be able to find a match by part of mobile number`() {
        val result = cut
            .search(SearchCriteria(mobile = "333"))
            .log()
            .collectList()
            .block()
        expectThat(result)
            .isNotNull()
            .hasSize(1)[0]
            .get { id }
            .isEqualTo(3L)

    }

    @Test
    internal fun `should be able to find a match by part of email`() {
        val result = cut
            .search(SearchCriteria(email = "four"))
            .log()
            .collectList()
            .block()
        expectThat(result)
            .isNotNull()
            .hasSize(1)[0]
            .get { id }
            .isEqualTo(4L)

    }

    @Test
    internal fun `should be able to find multiple matches by part of email address`() {
        val result = cut
            .search(SearchCriteria(email = ".COM"))
            .log()
            .collectList()
            .block()
        expectThat(result)
            .isNotNull()
            .hasSize(3)
    }
}
