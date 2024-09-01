package com.blues.flutterapp

import android.app.Application
import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class FlutterApplication : Application() {

    companion object {
        private const val TAG = "FlutterApplication"
        const val FLUTTER_ENGINE_ID = "flutter_engine_id"
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate")
        super.onCreate()
        initFlutterEngine()
    }

    private fun initFlutterEngine() {
        FlutterEngineCache.getInstance()
            .put(FLUTTER_ENGINE_ID, FlutterEngine(this).apply {
                dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
            })
    }

}