package org.example.movieservice.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Entity
@Table(name = "Movie")
class Movie (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String? = null,
    var length: Int? = null
)

@Repository
interface MovieRepository : JpaRepository<Movie, Long>

