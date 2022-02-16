package com.example.apitest.aac

abstract class IEvent {

    interface Handler {
        suspend fun onHandle(event: IEvent)
    }
}