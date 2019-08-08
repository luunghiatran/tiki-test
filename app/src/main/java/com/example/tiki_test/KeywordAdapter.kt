package com.example.tiki_test

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_keyword.view.*
import kotlin.collections.ArrayList


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
        holder.keywordTextView?.text = trimKeyword(items[position])

        val bgColorIndex = position % BG_COLORS.size
        holder.keywordTextView?.background = context.getDrawable(BG_COLORS[bgColorIndex])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun trimKeyword(keyword: String): String {
        val firstSpaceIndex = keyword.indexOf(" ")
        if (firstSpaceIndex == -1)
            // no space
            return keyword
        else if (firstSpaceIndex == keyword.lastIndexOf(" ")) {
            // one space
            return replaceText(keyword, "\n", firstSpaceIndex)
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

    private fun replaceText(mainText: String, newText: String, replaceIndex: Int): String {
        if (replaceIndex > -1 && replaceIndex < mainText.length) {
            val firstStr = mainText.substring(0, replaceIndex)
            val lastStr = mainText.substring(replaceIndex + 1)
            return firstStr + newText + lastStr
        }
        return mainText
    }
}

class KeywordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val keywordTextView: TextView? = view.keywordTextView
}