package com.example.cricscore

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.example.cricscore.ScoreDetails as ScoreDetails1

class MainActivity : AppCompatActivity() {
    lateinit var startScoring: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startScoring = findViewById(R.id.startBtn)

        startScoring.setOnClickListener { startActivity(Intent(this@MainActivity, ScoreDetails1::class.java)) }

    }
}