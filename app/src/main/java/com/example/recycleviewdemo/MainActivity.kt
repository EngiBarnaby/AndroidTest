package com.example.recycleviewdemo


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewdemo.ApiClient.ApiInterface
import com.example.recycleviewdemo.adapters.ItemAdapter
import com.example.recycleviewdemo.data.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler_view_items = findViewById<RecyclerView>(R.id.recycler_view_items)
        recycler_view_items.layoutManager = LinearLayoutManager(this)

        val itemAdapter = ItemAdapter(getItemsList())

        recycler_view_items.adapter = itemAdapter

        val apiInterface = ApiInterface.create().getMovies("069dbebb98d9fbc7983dc052e724319f")

        apiInterface.enqueue( object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("response", "Response success ${response?.body()?.results}")
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("response", "Response fail ${t?.message}")
            }
        })
    }


    private fun getItemsList(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 1..15) {
            list.add("Item $i")
        }

        return list
    }
}

