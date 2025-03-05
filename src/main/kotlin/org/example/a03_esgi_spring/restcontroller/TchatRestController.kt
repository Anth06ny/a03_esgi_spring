package org.example.a03_esgi_spring.restcontroller

import org.example.a03_esgi_spring.model.MessageBean
import org.example.a03_esgi_spring.model.MessageRepository
import org.example.a03_esgi_spring.model.MessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tchat")
class TchatRestController(val messageService: MessageService) {


    @PostMapping("/saveMessage")
    fun saveMessage(@RequestBody messageBean: MessageBean) = messageService.addMessage(messageBean)

    @GetMapping("/allMessages")
    fun allMessages() = messageService.getAll()

}