package com.rahmat.app.androidchat.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.rahmat.app.androidchat.R

/**
 * Created by muhrahmatullah on 09/10/18.
 */
class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var userText : TextView = itemView.findViewById(R.id.userText) as TextView
    var botText  :TextView = itemView.findViewById(R.id.botText) as TextView

}