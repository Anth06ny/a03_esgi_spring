package org.example.a03_esgi_spring

import org.example.a03_esgi_spring.model.MessageBean
import org.example.a03_esgi_spring.model.MessageRepository
import org.example.a03_esgi_spring.model.MessageService
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals


@SpringBootTest
class MessageServiceTest {

    var messageRepository: MessageRepository = Mockito.mock(MessageRepository::class.java)

    @InjectMocks
    lateinit var messageService: MessageService

    @Test
    fun testAddMessage() {

        val message = MessageBean(null, "test1", "Mesasge de test")
        messageService.addMessage(message)
        Mockito.verify(messageRepository, times(1)).save(message);

//        assertTrue(message.id != null && message.id!! > 0, "Message id must be greater than zero")
//
//        val messageFromBase = messageService.findById(message.id!!)
//
//        assertNotSame(message, messageFromBase)
//        assertEquals(message.pseudo, message.pseudo)
//        assertEquals(message.message, message.message)
    }

    @Test
    fun testGet10Last(){

        val messages10 = ArrayList<MessageBean>()

        repeat(15){
            val message = MessageBean(null, "test$it", "Message $it")
            messageService.addMessage(message)
            messages10.add(message)
        }

        Mockito.`when`(messageRepository.findFirst10ByOrderByIdDesc())
            .thenReturn(messages10.takeLast(10).reversed())


        val list = messageService.get10Last()

        assertEquals(list.size, 10, "Il ne doit y avoir que les 10 derniers")
        assertEquals("test14", list[0].pseudo)
    }
}