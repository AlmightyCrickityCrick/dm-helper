package com.example.dmhelper.presentation.common

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.dmhelper.R
import com.example.dmhelper.data.character.AbilityType
import com.example.dmhelper.data.character.ClassEnum
import com.example.dmhelper.data.character.ClassEnum.BARBARIAN
import com.example.dmhelper.data.character.ClassEnum.BARD
import com.example.dmhelper.data.character.ClassEnum.CLERIC
import com.example.dmhelper.data.character.ClassEnum.DRUID
import com.example.dmhelper.data.character.ClassEnum.FIGHTER
import com.example.dmhelper.data.character.ClassEnum.MONK
import com.example.dmhelper.data.character.ClassEnum.PALADIN
import com.example.dmhelper.data.character.ClassEnum.RANGER
import com.example.dmhelper.data.character.ClassEnum.ROGUE
import com.example.dmhelper.data.character.ClassEnum.SORCERER
import com.example.dmhelper.data.character.ClassEnum.WARLOCK
import com.example.dmhelper.data.character.ClassEnum.WIZARD
import com.example.dmhelper.data.character.RaceEnum

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun getScreenSizeDp(): Pair<Int, Int> {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    return screenWidthDp to screenHeightDp
}

@Composable
fun getFactors(): Pair<Float, Float> {
//    val (width, height) = getScreenSizeDp()
//    return width / 852 to height / 393
    return 1.5f to 2f
//    return 1f to 1f
}

fun getResource(charClassEnum: ClassEnum?) = when (charClassEnum) {
    ROGUE -> R.drawable.ic_rogue
    BARD -> R.drawable.ic_bard
    WARLOCK -> R.drawable.ic_warlock
    RANGER -> R.drawable.ic_ranger
    MONK -> R.drawable.ic_monk
    PALADIN -> R.drawable.ic_paladin
    BARBARIAN -> R.drawable.ic_barbarian
    SORCERER -> R.drawable.ic_sorcerer
    DRUID -> R.drawable.ic_druid
    CLERIC -> R.drawable.ic_cleric
    WIZARD -> R.drawable.ic_wizard
    FIGHTER -> R.drawable.ic_fighter
    null -> R.drawable.ic_fighter
}

fun getResource(charRaceEnum: RaceEnum?) = when (charRaceEnum) {
    RaceEnum.HUMAN -> R.drawable.human
    RaceEnum.ELF -> R.drawable.elf
    RaceEnum.HALF_ELF -> R.drawable.half_elf
    RaceEnum.DWARF -> R.drawable.dwarf
    RaceEnum.DRAGONBORN -> R.drawable.dragonborn
    RaceEnum.DROW -> R.drawable.drow
    RaceEnum.GNOME -> R.drawable.gnome
    RaceEnum.HALFLING -> R.drawable.halfling
    RaceEnum.HALF_ORC -> R.drawable.orc
    RaceEnum.TIEFLING -> R.drawable.tiefling
    RaceEnum.GITHYANKI -> R.drawable.gith
    RaceEnum.YUANTI -> R.drawable.yuanti
    RaceEnum.FIRBOLG -> R.drawable.firbolg
    RaceEnum.GENASI -> R.drawable.genasi
    RaceEnum.TRITON -> R.drawable.triton
    null -> R.drawable.human
}

fun getResource(abilityType: AbilityType) = when(abilityType){
    AbilityType.STRENGTH -> R.drawable.ic_strength
    AbilityType.DEXTERITY -> R.drawable.ic_dexterity
    AbilityType.CONSTITUTION -> R.drawable.ic_constitution
    AbilityType.INTELLIGENCE -> R.drawable.ic_intelligence
    AbilityType.WISDOM -> R.drawable.ic_wisdom
    AbilityType.CHARISMA -> R.drawable.ic_charisma
}
