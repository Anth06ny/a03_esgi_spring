package org.example.a03_esgi_spring.model

import io.swagger.v3.oas.annotations.media.Schema

data class StudentBean(var name: String = "", var note: Int = 0)

data class UserBean(var login: String = "", var password: String = "")

