package com.erick.practica1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.erick.practica1.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var tv_anser : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //        Ocultando barra de estado
        actionBar?.hide()

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val answer = intent.getStringExtra("answer")

        tv_anser = binding.tvAnswer
        tv_anser.setText(answer)

    }
}