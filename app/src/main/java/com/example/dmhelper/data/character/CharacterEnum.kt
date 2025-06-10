package com.example.dmhelper.data.character

import com.example.dmhelper.R

enum class RaceEnum {
    HUMAN,
    ELF,
    HALF_ELF,
    DWARF,
    DRAGONBORN,
    DROW,
    GNOME,
    HALFLING,
    HALF_ORC,
    TIEFLING,
    GITHYANKI,
    YUANTI,
    FIRBOLG,
    GENASI,
    TRITON
}

enum class ClassEnum {
    BARD,
    ROGUE,
    WARLOCK,
    RANGER,
    MONK,
    PALADIN,
    BARBARIAN,
    SORCERER,
    DRUID,
    CLERIC,
    WIZARD,
    FIGHTER;
}

inline fun <reified T : Enum<T>> Int.toEnumOrNull(): T? {
    return enumValues<T>().getOrNull(this)
}

inline fun <reified T : Enum<T>> Int.toEnumNameOrNull(): String? {
    return enumValues<T>().getOrNull(this)?.name
}