package com.example.dmhelper.presentation.common

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.dmhelper.R
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
    BARD -> R.drawable.ic_crown
    WARLOCK -> R.drawable.ic_crown
    RANGER -> R.drawable.ic_crown
    MONK -> R.drawable.ic_crown
    PALADIN -> R.drawable.ic_crown
    BARBARIAN -> R.drawable.ic_crown
    SORCERER -> R.drawable.ic_crown
    DRUID -> R.drawable.ic_crown
    CLERIC -> R.drawable.ic_crown
    WIZARD -> R.drawable.ic_crown
    FIGHTER -> R.drawable.ic_crown
    null -> R.drawable.ic_crown
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
