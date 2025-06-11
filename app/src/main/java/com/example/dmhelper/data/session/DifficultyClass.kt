package com.example.dmhelper.data.session

enum class DifficultyClass(val dcValue: Int) {
    EASY(5),
    NORMAL(10),
    HARD(15),
    VERY_HARD(20),
    EXTREME(25),
    LEGENDARY(30);

    override fun toString(): String {
        return name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() } + " ($dcValue)"
    }
}