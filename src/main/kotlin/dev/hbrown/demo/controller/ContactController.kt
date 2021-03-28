package dev.hbrown.demo.controller

import dev.hbrown.demo.service.ContactService
import dev.hbrown.demo.domain.Contact
import dev.hbrown.demo.dto.SearchCriteria
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@MessageMapping("v1.contact")
@Controller
class ContactController(
    private val contactService: ContactService,
) {

    @MessageMapping("search")
    fun search(searchCriteria: SearchCriteria): Flux<Contact> =  contactService.search(searchCriteria)

}
