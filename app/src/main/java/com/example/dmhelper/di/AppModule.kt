package com.example.dmhelper.di

import com.example.dmhelper.presentation.campaign.list.CampaignListViewModel
import com.example.dmhelper.presentation.campaign.main.CampaignMainViewModel
import com.example.dmhelper.presentation.character.create.CharacterCreateViewModel
import com.example.dmhelper.presentation.character.list.CharacterListViewModel
import com.example.dmhelper.presentation.home.HomeViewModel
import com.example.dmhelper.presentation.login.LoginViewModel
import com.example.dmhelper.presentation.register.RegisterViewModel
import com.example.dmhelper.presentation.session.create.SessionCreateViewModel
import com.example.dmhelper.presentation.session.list.SessionListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
    viewModel { HomeViewModel() }
    viewModel { CampaignListViewModel() }
    viewModel { CharacterListViewModel() }
    viewModel { CharacterCreateViewModel() }
    viewModel { SessionListViewModel() }
    viewModel { SessionCreateViewModel() }
    viewModel { CampaignMainViewModel() }
}