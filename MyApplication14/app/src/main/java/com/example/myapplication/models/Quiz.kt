package com.example.myapplication.models

data class Quiz (
    var id : String = "",
    val title : String = "",
    val question: MutableMap<String,Question> = mutableMapOf()
)