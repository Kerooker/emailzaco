package br.org.institutoops.emailzaco.controller

import br.org.institutoops.emailzaco.service.SendMailService
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailSendController(
    private val sendMailService: SendMailService
) {
    
    @PostMapping
    fun sendEmail(
        @RequestBody emailSendRequest: EmailSendRequest
    ): ResponseEntity<String> {
        sendAllEmails(emailSendRequest)
        return ResponseEntity.accepted().build()
    }
    
    private fun sendAllEmails(emailSendRequest: EmailSendRequest) {
        emailSendRequest.parliamentarianRequests.forEach {
            sendMailService.sendMail(
                emailSendRequest.user.name,
                emailSendRequest.user.email,
                it.name,
                it.mailBody
            )
        }
    }
}

data class EmailSendRequest(
    @JsonProperty("user") val user: User,
    @JsonProperty("parliamentarian_mails") val parliamentarianRequests: List<ParliamentarianMailRequest>
)

data class User(
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String
)

data class ParliamentarianMailRequest(
    @JsonProperty("name") val name: String,
    @JsonProperty("mail_body") val mailBody: String
)