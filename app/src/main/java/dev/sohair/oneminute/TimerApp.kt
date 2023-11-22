package dev.sohair.oneminute

import android.app.Application
import dev.sohair.oneminute.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TimerApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TimerApp)
            modules(AppModules.timerModule)
        }

        //Create notification channel
        NotificationUtils.createNotificationChannel(this)
    }
}