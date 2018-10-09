package com.rahmat.app.androidchat.feature

import ai.api.AIDataService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.rahmat.app.androidchat.entity.ChatMessage
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by muhrahmatullah on 09/10/18.
 */
class MainPresenter(val aiDataAIService: AIDataService,
                    val ref: DatabaseReference) : MainContract.Presenter {

    val aiRequest = AIRequest()

    override fun sendMessage(message: String) {
        val chatMessage = ChatMessage(message, "user")
        ref.child("chat").push().setValue(chatMessage)
        aiRequest.setQuery(message)

        async(UI) {
            val response = bg {
                aiDataAIService.request(aiRequest)
            }
            if (response.await() != null) {
                sendMessageToServer(response.await())
            }else{
                Log.v("test", "didn't send")
            }
        }
    }
    private fun sendMessageToServer(response: AIResponse?) {
        val result = response?.result
        val reply = result?.fulfillment?.speech
        val chatMessage = ChatMessage(reply, "bot")
        ref.child("chat").push().setValue(chatMessage)
    }
}