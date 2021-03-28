package dev.hbrown.demo.service

import dev.hbrown.demo.domain.Contact
import dev.hbrown.demo.dto.SearchCriteria
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import javax.annotation.PostConstruct

interface ContactService {

    fun search(searchCriteria: SearchCriteria): Flux<Contact>

}

@Service
class ContactServiceInMemoryImpl : ContactService {

    private val db: ConcurrentMap<Long, Contact> = ConcurrentHashMap()

    @PostConstruct
    fun populateTestData() {
        db.putAll(
            arrayOf(
                1L to Contact(id = 1L, firstName = "Amy", lastName = "Aniston", mobileNumber = "27830000000", email = "amy@one.com"),
                2L to Contact(id = 2L, firstName = "Brian", lastName = "Brown", mobileNumber = "27821111111", email = "brian.brown@two.com"),
                3L to Contact(id = 3L, firstName = "Cindy", lastName = "Crawford", mobileNumber = "27813333333", email = "cc@three.com"),
                4L to Contact(id = 4L, firstName = "Donald", lastName = "Drew", mobileNumber = "27804444444", email = "drew@four.co.za"),
            )
        )
    }


    override fun search(searchCriteria: SearchCriteria): Flux<Contact> = Flux.fromIterable(
        db.values.filter {
            with(searchCriteria) {
                (name != null && it.firstName.contains(name, ignoreCase = true)) ||
                        (name != null && it.lastName.contains(name, ignoreCase = true)) ||
                        (mobile != null && it.mobileNumber.contains(mobile, ignoreCase = true)) ||
                        (email != null && it.email.contains(email, ignoreCase = true))
            }
        }
    )

}
