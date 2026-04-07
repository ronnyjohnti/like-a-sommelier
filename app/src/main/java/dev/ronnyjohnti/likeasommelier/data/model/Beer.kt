package dev.ronnyjohnti.likeasommelier.data.model

data class Beer(
    val brand: String,
    val type: String,
    val alcohol: String,
    val rating: Float,
    val id: Int = 0
)
