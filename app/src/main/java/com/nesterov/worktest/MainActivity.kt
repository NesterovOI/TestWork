package com.nesterov.worktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesterov.worktest.adapter.UsersAdapter
import com.nesterov.worktest.databinding.ActivityMainBinding
import com.nesterov.worktest.retrofit.UsersAPi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter()
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
        }

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val userApi = retrofit.create(UsersAPi::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val list = userApi.getAllUsers()
            runOnUiThread {
                binding.apply {
                    val sortetLogin = list.sortedBy {
                        it.login
                    }
                    adapter.submitList(sortetLogin)
                }
            }
        }
    }

}