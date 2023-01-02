package android.banananabandit.figuresupdate

import android.app.Application
import android.content.Context

class WeeksApplication: Application() {
    init { app = this }
    companion object {
        private lateinit var app: WeeksApplication
        fun getAppContext(): Context = app.applicationContext
    }
}