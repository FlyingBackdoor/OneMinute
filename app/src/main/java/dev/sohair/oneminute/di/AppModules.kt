package dev.sohair.oneminute.di

import dev.sohair.oneminute.ui.components.HomeScreenViewModel
import org.koin.dsl.module

object AppModules {
    val timerModule = module {
        single {
            HomeScreenViewModel()
        }
    }
}
