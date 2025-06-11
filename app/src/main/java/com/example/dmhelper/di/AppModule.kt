package com.example.dmhelper.di

import com.example.dmhelper.data.campaign.CampaignRepository
import com.example.dmhelper.data.character.CharacterRepository
import com.example.dmhelper.data.common.ApiService
import com.example.dmhelper.data.session.SessionRepository
import com.example.dmhelper.data.user.UserRepository
import com.example.dmhelper.data.user.UserRepositoryImpl
import com.example.dmhelper.presentation.campaign.list.CampaignListViewModel
import com.example.dmhelper.presentation.campaign.main.CampaignMainViewModel
import com.example.dmhelper.presentation.character.create.CharacterCreateViewModel
import com.example.dmhelper.presentation.character.list.CharacterListViewModel
import com.example.dmhelper.presentation.home.HomeViewModel
import com.example.dmhelper.presentation.login.LoginViewModel
import com.example.dmhelper.presentation.register.RegisterViewModel
import com.example.dmhelper.presentation.session.create.SessionCreateViewModel
import com.example.dmhelper.presentation.session.editor.SessionEditorViewModel
import com.example.dmhelper.presentation.session.list.SessionListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CampaignListViewModel(
        get(),
        userRepository = get(),
        charRepository = get()
    ) }
    viewModel { CharacterListViewModel(
        get(),
        userRepository = get()
    ) }
    viewModel { CharacterCreateViewModel(
        get(),
        userRepository = get()
    ) }
    viewModel { SessionListViewModel(
        get(),
        get(),
        get(),
    ) }
    viewModel { SessionCreateViewModel(
        get(),
        characterRepository = get(),
        get()
    ) }
    viewModel { CampaignMainViewModel(get()) }
    viewModel { SessionEditorViewModel(get(), get()) }

    single {
        Retrofit.Builder()
            .baseUrl("https://5744-89-28-69-173.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(ApiService::class.java) }
    single { CharacterRepository(get(), androidContext()) }
    single { CampaignRepository(get(), androidContext()) }
    single { SessionRepository(get(), androidContext()) }
    single <UserRepository>{ UserRepositoryImpl(get(), androidContext()) }

}