package org.example.a03_esgi_spring.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service


@Service
class TeacherService(val teacherRep:TeacherRepository) {

    //@Transactional si je souhaite le faire dans une transaction
    fun createTeacher(name:String?, code:Int) : TeacherBean {
        if(name.isNullOrBlank()){
            throw Exception("Name missing")
        }
        else if(code !in 1..10){
            throw Exception("Code incorrecte")
        }
        val teacher = TeacherBean(null, name, code, null)
        teacherRep.save(teacher)
        return teacher
    }

    fun getAll() = teacherRep.findAll()

}

@Repository                       //<Bean, Typage Id>
interface TeacherRepository : JpaRepository<TeacherBean, Long> {

}

@Entity
@Table(name = "teacher")
data class TeacherBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String = "",
    @Column(name = "code")
    var code: Int? = null,
    var sessionId : String? = null //Ira chercher session_id dans la table
)