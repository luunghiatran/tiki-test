package com.example.tiki_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiki_test.network.TikiService
import com.example.tiki_test.network.TikiWS
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"

    private lateinit var keywordAdapter: KeywordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupKeywordRecyclerView()
        fetchKeyword()
    }

    override fun onResume() {
        fetchKeyword()
        super.onResume()
    }

    private fun setupKeywordRecyclerView() {
        keywordAdapter = KeywordAdapter(ArrayList(), this@MainActivity)
        keywordRecyclerView.adapter = keywordAdapter

        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        keywordRecyclerView.layoutManager = layoutManager
    }

    private fun fetchKeyword() {
        val tikiService = TikiService.getInstance().create(TikiWS::class.java).getKeyword()
        tikiService.enqueue(object: Callback<ArrayList<String>> {
            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                showError(t.message + "")
            }

            override fun onResponse(call: Call<ArrayList<String>>, response: Response<ArrayList<String>>) {
                if (response.body() != null) {
                    refreshKeywords(response.body()!!)
                } else {
                    showError(response.raw().message())
                }
            }
        })
    }

    private fun refreshKeywords(keywordList: java.util.ArrayList<String>) {
        this.keywordAdapter.items = keywordList
        this.keywordAdapter.notifyDataSetChanged()
    }

    private fun showError(message: String){
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
