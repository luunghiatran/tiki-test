package com.example.tiki_test

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_keyword.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


private val BG_COLORS = arrayOf(
    R.color.color1,
        R.color.color2,
        R.color.color3,
        R.color.color4,
        R.color.color5
)


class KeywordAdapter(var items: ArrayList<String>, val context: Context) : RecyclerView.Adapter<KeywordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.item_list_keyword, null, false)
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        rootView.layoutParams = lp
        return KeywordViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder?.keywordTextView?.text = trimKeyword(items[position])

        val bgColorIndex = Random.nextInt(BG_COLORS.size - 1)
        holder?.keywordTextView?.background = context.getDrawable(BG_COLORS[bgColorIndex])
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    fun trimKeyword(keyword: String): String {
        if (keyword.indexOf(" ") == -1)
            // no space
            return keyword
        else if (keyword.split(" ").size == 2) {
            // one space
            val replaceSpaceIndex = keyword.indexOf(" ")
            return replaceText(keyword, "\n", replaceSpaceIndex)
        } else {
            val headStr = keyword.substring(0, keyword.length / 2)
            val tailStr = keyword.substring(keyword.length / 2, keyword.length)

            val lastSpaceIndexOfHead = headStr.lastIndexOf(" ")
            val inverseLastSpaceIndexOfHead = headStr.length - 1 - lastSpaceIndexOfHead
            val firstSpaceIndexOfTail = tailStr.indexOf(" ")

            if (inverseLastSpaceIndexOfHead <= firstSpaceIndexOfTail) {
                val replaceSpaceIndex = headStr.lastIndexOf(" ")
                return replaceText(keyword, "\n", replaceSpaceIndex)
            } else {
                val replaceSpaceIndex = headStr.length + firstSpaceIndexOfTail
                return replaceText(keyword, "\n", replaceSpaceIndex)
            }
        }
    }

    fun replaceText(mainText: String, newText: String, replaceIndex: Int): String {
        Log.e("nghia", mainText + "---" + replaceIndex)
        val firstStr = mainText.substring(0, replaceIndex)
        val lastStr = mainText.substring(replaceIndex + 1)
        return firstStr + newText + lastStr
    }
}

class KeywordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val keywordTextView = view.keywordTextView
}