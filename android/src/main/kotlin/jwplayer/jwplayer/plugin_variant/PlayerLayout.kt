//package jwplayer.jwplayer
//
//import android.app.Activity
//import android.content.Context
//import android.content.res.Configuration
//import android.view.View
//import io.flutter.plugin.common.BinaryMessenger
//import io.flutter.plugin.common.MethodCall
//import io.flutter.plugin.common.MethodChannel
//import io.flutter.plugin.platform.PlatformView
//
//class PlayerView internal constructor(
//    context: Context?,
//    activity: Activity?,
//    id: Int,
//    messenger: BinaryMessenger?,
//    args: Any?
//) :
//    PlatformView, MethodChannel.MethodCallHandler {
//    private val player: PlayerLayout
//    private val channel: MethodChannel
//
//    init {
//        channel = MethodChannel(messenger, "jwplayer/JWPlayerView_$id")
//        channel.setMethodCallHandler(this)
//        player = PlayerLayout(context, activity, messenger, args)
//    }
//
//    val view: View
//        get() = player
//
//    fun dispose() {
//        player.onHostDestroy()
//    }
//
//    protected fun onPause() {
//        player.onHostPause()
//    }
//
//    protected fun onResume() {
//        player.onHostResume()
//    }
//
//    fun onConfigurationChange(newConfig: Configuration?) {
//        player.onConfigurationChanged(newConfig)
//    }
//
//    fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
//        when (call.method) {
//            "dispose" -> {
//                dispose()
//                result.success(true)
//            }
//
//            else -> result.notImplemented()
//        }
//    }
//}