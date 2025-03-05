package org.example.a03_esgi_spring.controller

import jakarta.servlet.http.HttpSession
import org.example.a03_esgi_spring.model.StudentBean
import org.example.a03_esgi_spring.model.UserBean
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
class MyController {


    @GetMapping("/login") //Affiche le formulaire
    fun login(userBean: UserBean, httpSession: HttpSession, model: Model): String {
        println("/login")
        userBean.login = "toto"
        model.addAttribute("errorMessage", httpSession.id )


        return "login" //Lance login.html
    }

    @PostMapping("/loginSubmit") //traite le formulaire
    fun loginSubmit(userBean: UserBean, redirectAttributes: RedirectAttributes) : String {
        println("/loginSubmit : userBean=$userBean")

        try {
            if(userBean.login.length < 3){
                throw Exception("Ilf aut au moins 3 caractères")
            }

            redirectAttributes.addFlashAttribute("userBean", userBean)

            return "redirect:userRegister"
        }
        catch(e:Exception){
            e.printStackTrace()

            redirectAttributes.addFlashAttribute("userBean", userBean)
            redirectAttributes.addFlashAttribute("errorMessage", e.message)
            return "redirect:login"
        }

    }

    @GetMapping("/userRegister") //Affiche la page résultat
    fun userRegister(): String {
        println("/userRegister")

        return "userRegister" //Lance userRegister.html
    }

    //http://localhost:8080/hello
    @GetMapping("/hello")
    fun testHello(model : Model): String {
        println("/hello")

        model.addAttribute("texte", "Bonjour")
        model.addAttribute("studentBean", StudentBean("Toto", 12))
        model.addAttribute("studentList",
            arrayOf(StudentBean("Bob", 10), StudentBean("Bobby", 8), StudentBean("Charles", 20)))

        //Nom du fichier HTML que l'on souhaite afficher
        return "welcome"
    }
}