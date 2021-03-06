package br.org.institutoops.emailzaco.controller

import br.org.institutoops.emailzaco.service.SendMailService
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty


@RestController
@RequestMapping("/email")
class EmailSendController(
    private val sendMailService: SendMailService
) {
    
    @PostMapping
    fun sendEmail(
        @Valid @RequestBody emailSendRequest: EmailSendRequest
    ): ResponseEntity<String> {
        sendAllEmails(emailSendRequest)
        return ResponseEntity.accepted().build()
    }
    
    private fun sendAllEmails(emailSendRequest: EmailSendRequest) {
        // Empty
    }
}

data class EmailSendRequest(
    @JsonProperty("user") val user: User
)

data class User(
    
    @NotEmpty
    @JsonProperty("name")
    val name: String,
    
    @Email
    @JsonProperty("email")
    val email: String
)
