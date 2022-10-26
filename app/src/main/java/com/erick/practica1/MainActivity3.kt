package com.erick.practica1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class MainActivity3: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        Ocultando barra de estado
        actionBar?.hide()

        setContentView(R.layout.activity_main3)

        lifecycleScope.launchWhenStarted {
            delay(3000)
            val intent = Intent(this@MainActivity3, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}