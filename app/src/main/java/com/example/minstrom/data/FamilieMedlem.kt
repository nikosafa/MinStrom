package com.example.minstrom.data
import com.google.firebase.Timestamp

//Dette er data "skabelonen"

data class FamilieMedlem(
    val familieNavn: String = "",
    val medlemmer: List<String>,
    val oprettet: Timestamp = Timestamp.now()
)
