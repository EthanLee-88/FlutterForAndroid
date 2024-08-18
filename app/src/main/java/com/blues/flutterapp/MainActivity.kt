package com.blues.flutterapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.blues.flutterapp.FlutterApplication.Companion.FLUTTER_ENGINE_ID
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.main_bt).setOnClickListener {
            startActivity(
                FlutterAc
                    .withCachedEngine(FlutterAc::class.java, FLUTTER_ENGINE_ID)
                    .build(this)
            )
        }
    }
}