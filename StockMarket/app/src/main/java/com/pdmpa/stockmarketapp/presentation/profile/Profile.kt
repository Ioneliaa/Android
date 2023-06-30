package com.pdmpa.stockmarketapp.presentation.profile

import com.pdmpa.stockmarketapp.R

data class Profile(
    val firstName: String = "Kocsis",
    val lastName: String = "Bogdan",
    val email: String = "bogdankocsis@gmail.com",
    val telephone: String = "0756687554",
    val gender: String = "Male",
    val avatarUrl: Int = R.drawable.img_6,
)