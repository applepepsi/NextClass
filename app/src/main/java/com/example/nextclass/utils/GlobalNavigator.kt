package com.example.nextclass.utils

object GlobalNavigator {
    private var handler: GlobalNavigationHandler? = null


    fun registerHandler(handler: GlobalNavigationHandler) {
        this.handler = handler
    }


    fun unregisterHandler() {
        handler = null
    }


    fun logout() {
        handler?.logout()
    }


}

interface GlobalNavigationHandler {
    fun logout()

}