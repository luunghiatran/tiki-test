package com.example.tiki_test

import android.content.Context
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_keyword.view.*

class KeywordAdapter(var items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<KeywordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        return KeywordViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_keyword, parent, false))
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder?.keywordTextView?.text = trimKeyword(items[position])
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    final fun trimKeyword(keyword: String): String {
        if (keyword.indexOf(" ") == -1)
            return keyword
        else {
            val firstPart = keyword.substring(0, keyword.length/2)
            val lastPart = keyword.substring(keyword.length/2, keyword.length)

            val spaceIndexOfFirst = firstPart.lastIndexOf(" ") - firstPart.length
            val spaceIndexOfLast = lastPart.indexOf(" ")
            if (spaceIndexOfFirst >= spaceIndexOfLast) {
                val replaceSpaceIndex = firstPart.lastIndexOf(" ")
                return replaceChar(keyword, "\n", replaceSpaceIndex)
            } else {
                val replaceSpaceIndex = firstPart.length + spaceIndexOfLast
                return replaceChar(keyword, "\n", replaceSpaceIndex)
            }
            return "2"
        }
    }

    fun replaceChar(str: String, ch: String, index: Int): String {
        return str.substring(0, index) + ch + str.substring(index + 1)
    }
}

class KeywordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val keywordTextView = view.keywordTextView
}