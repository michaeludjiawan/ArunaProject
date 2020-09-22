package com.michaeludjiawan.arunaproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.michaeludjiawan.arunaproject.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_main_host, HomeFragment())
            .commit()
    }
}