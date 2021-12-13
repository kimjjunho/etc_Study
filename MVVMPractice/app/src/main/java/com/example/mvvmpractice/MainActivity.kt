package com.example.mvvmpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.button.setOnClickListener {
            mBinding!!.text.text = "ㅅㅂ"
        }
        mBinding!!.refresh.setOnRefreshListener {
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
            mBinding!!.refresh.isRefreshing = false
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}