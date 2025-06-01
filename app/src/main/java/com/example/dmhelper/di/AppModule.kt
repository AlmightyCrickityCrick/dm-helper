package com.example.dmhelper.di

import com.example.dmhelper.presentation.campaignlist.CampaignListViewModel
import com.example.dmhelper.presentation.characterlist.CharacterListViewModel
import com.example.dmhelper.presentation.home.HomeViewModel
import com.example.dmhelper.presentation.login.LoginViewModel
import com.example.dmhelper.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
    viewModel { HomeViewModel() }
    viewModel { CampaignListViewModel() }
    viewModel { CharacterListViewModel() }


}