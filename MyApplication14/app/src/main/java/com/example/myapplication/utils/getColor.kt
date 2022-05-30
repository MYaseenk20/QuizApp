package com.example.myapplication.utils

object getColor {
    val color = arrayOf(
        "#3EB9DF",
        "#3685BC",
        "#D36280",
        "#E44F55",
        "#FA8056",
        "#818BCA",
        "#7D659F",
        "#51BAB3",
        "#4FB66C",
        "#E3AD17",
        "#627991",
        "#EF8EAD",
        "#B5BFC6"
    )

    var currentColor = 0

    fun getColor() : String{
        currentColor = (currentColor + 1) % color.size
        return color[currentColor]

    }

}