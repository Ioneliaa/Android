package com.pdmpa.stockmarketapp.util

object AuthUtil {
    fun isEmailValid(email: String): Boolean {
        val emailRegex =
            """^([a-zA-Z]+)([!#$%&'*+\-/=?^_{|]?[a-zA-Z0-9]+)*@([a-zA-Z]+)([.-]?[a-zA-Z0-9]+)*\.[a-zA-Z]{2,4}""".toRegex()
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordRegex =
            """^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@${'$'}%^&*-]).{8,}""".toRegex()
        return passwordRegex.matches(password)
    }
}