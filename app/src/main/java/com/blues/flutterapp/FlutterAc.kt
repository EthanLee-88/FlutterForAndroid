package com.blues.flutterapp

import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class FlutterAc : FlutterActivity() {
    companion object {
        private const val TAG = "FlutterAc"
        private const val CHANNEL_ID = "com.channel.id"
        private const val METHOD_TO_FLUTTER = "method_to_flutter"

        /**
         * 初始化引擎
         */
        fun withCachedEngine(
            activityClass: Class<out FlutterAc?>,
            engineId: String,
        ): CachedEngineIntentBuilder {
            return CachedEngineIntentBuilder(activityClass, engineId)
        }
    }

    private var mMethodChannel: MethodChannel? = null


    /**
     * 创建通信通道
     */
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d(TAG, "configureFlutterEngine")
        mMethodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL_ID)
        setOnMethodChannelListener();
    }

    /**
     * 监听 Flutter消息
     */
    private fun setOnMethodChannelListener() {
        mMethodChannel?.apply {
            setMethodCallHandler { call, result ->
                Log.d(TAG, "call = ${call.method} \n ${call.arguments}")
                result.success("from native =  ${call.arguments}")
                sendMsgToFlutter()
            }
        }
    }

    /**
     * 发消息给 Flutter
     */
    private fun sendMsgToFlutter() {
        mMethodChannel?.apply {
            invokeMethod("method_get_home_page", "send from native")
            invokeMethod(METHOD_TO_FLUTTER,
                mutableMapOf<String, String>().apply {
                    this["keyOne"] = "from native key"
                },
                object : MethodChannel.Result {
                    override fun success(result: Any?) {
                        Log.d(TAG, "invokeMethod = $result")
                    }

                    override fun error(
                        errorCode: String,
                        errorMessage: String?,
                        errorDetails: Any?,
                    ) {

                    }

                    override fun notImplemented() {

                    }
                })
        }
    }
}


//flutter_boost:
//dependency: "direct main"
//description: flutter_boost
//path: "."
//ref: "4.6.2"
//resoled-ref: "58dd18ca21a8dd256783b7064c442b1ff7683679"
//url: "https://github.com/alibaba/flutter_boost.git"
//source: git
//version: "4.6.2"