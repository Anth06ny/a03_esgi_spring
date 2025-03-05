package org.example.a03_esgi_spring.restcontroller

import org.example.a03_esgi_spring.model.StudentBean
import org.example.a03_esgi_spring.model.TeacherBean
import org.example.a03_esgi_spring.model.TeacherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MyRestController(val teacherService: TeacherService) {

    //http://localhost:8080/testPublic
    @GetMapping("/testPublic")
    fun testPublic(): String {
        println("/testPublic")
        return "Hello public"
    }

    //http://localhost:8080/testPrivate
    @GetMapping("/testPrivate")
    fun testPrivate(): String {
        println("/testPrivate")
        return "Hello private"
    }

    //http://localhost:8080/testPrivateAdmin
    @GetMapping("/testPrivateAdmin")
    fun testPrivateAdmin(): String {
        println("/testPrivateAdmin")
        return "Hello private admin"
    }

    //http://localhost:8080/addTeacher?name=toto&code=5
    @GetMapping("/addTeacher")
    fun addTeacher(name: String, code: Int): List<TeacherBean> {
        println("/addTeacher name=$name code=code")

        teacherService.createTeacher(name, code)

        return teacherService.getAll()
    }

    //http://localhost:8080/receiveStudent
//Json Attendu : {"name": "toto","note": 12}
    @PostMapping("/receiveStudent")
    fun receiveStudent(@RequestBody student: StudentBean) {
        println("/receiveStudent : $student")

        //traitement, mettre en base…
        //Retourner d'autres données
    }

    /* -------------------------------- */
    // GET
    /* -------------------------------- */

    //http://localhost:8080/test
    @GetMapping("/test")
    fun testMethode(): String {
        println("/test")

        return "helloWorld"
    }

    //http://localhost:8080/getStudent
    @GetMapping("/getStudent")
    fun getStudent(): StudentBean {
        println("/getStudent")
        var student = StudentBean("toto", 12)

        return student
    }

    //http://localhost:8080/max?p1=-3
    @GetMapping("/max")
    fun max(p1: Int? = null, p2: Int? = null): Int? {
        println("/max p1=$p1 p2=$p2")
        if (p1 == null) {
            return p2
        }
        else if (p2 == null) {
            return p1
        }
        else {
            return Math.max(p1, p2)
        }
    }

    //http://localhost:8080/boulangerie?nbCroissant=3&nbSandwich=2
    @GetMapping("/boulangerie")
    fun boulangerie(nbCroissant: Int = 0, nbSandwich: Int = 0): String {
        println("/boulangerie nbCroissant=$nbCroissant nbSandwich=$nbSandwich")

        return "${nbCroissant * 0.95 + nbSandwich * 4} €"
    }

    //http://localhost:8080/maxv2?p1=-3
    @GetMapping("/maxv2")
    fun maxv2(p1: String? = null, p2: String? = null): Int? {
        println("/max p1=$p1 p2=$p2")

        var p1Int = p1?.toIntOrNull()
        var p2Int = p2?.toIntOrNull()

        if (p1Int == null) {
            return p2Int
        }
        else if (p2Int == null) {
            return p1Int
        }
        else {
            return Math.max(p1Int, p2Int)
        }
    }
}