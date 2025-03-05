package org.example.a03_esgi_spring.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service


@Service
class MessageService(val messageRepository: MessageRepository) {

    //@Transactional si je souhaite le faire dans une transaction
    fun addMessage(messageBean: MessageBean) {
        if(messageBean.message.isBlank()){
            throw Exception("Message missing")
        }

        messageRepository.save(messageBean)
    }


    fun get10Last() = messageRepository.findFirst10ByOrderByIdDesc()
    fun findById(id:Long) = messageRepository.findByIdOrNull(id)

    fun getAll() = messageRepository.findAll()

}

@Repository                       //<Bean, Typage Id>
interface MessageRepository : JpaRepository<MessageBean, Long> {
    fun findFirst10ByOrderByIdDesc(): List<MessageBean>
}

//@Schema :  pour la documentation avec Swagger

@Entity
@Table(name = "message")
@Schema(description = "Représente un message dans le tchat")
data class MessageBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Schema(description = "Pseudo de l'utilisateur", example = "toto")
    var pseudo: String = "",
    var message : String = "")

@Schema(description = "Représente un message dans le tchat")
data class MessageDTO(
    @Schema(description = "Pseudo de l'utilisateur", example = "toto")
    var pseudo: String = "",
    var message : String = "")