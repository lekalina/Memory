package di

import data.helpers.NetworkCreator
import domain.repos.MemoryRepo
import domain.repos.SettingsRepo
import domain.repos.SimonRepo
import domain.source.LocalSource
import org.koin.dsl.module
import ui.screens.memory.MemoryGameVM
import ui.screens.settings.SettingsVM
import ui.screens.simon.SimonVM

fun sharedModule() = module {
    single { NetworkCreator() }
    single { LocalSource(get()) }
    single { MemoryRepo(get()) }
    single { SettingsRepo(get()) }
    single { SimonRepo(get()) }
    viewModelDefinition { MemoryGameVM(get()) }
    viewModelDefinition { SettingsVM(get()) }
    viewModelDefinition { SimonVM(get()) }
}