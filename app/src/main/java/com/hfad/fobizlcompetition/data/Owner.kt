package com.hfad.fobizlcompetition.data

import androidx.room.Entity

@Entity
data class Owner(
    val display_name: String,
    val profile_image: String,
)