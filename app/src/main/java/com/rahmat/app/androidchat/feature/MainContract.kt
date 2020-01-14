package com.rahmat.app.androidchat.feature

/**
 * Created by muhrahmatullah on 09/10/18.
 */
interface MainContract {

    interface View{

    }

    interface Presenter{
        fun sendMessage(message: String)
        fun onDestroy()
    }

}