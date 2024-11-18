//package jwplayer.jwplayer
//
//import android.app.Activity
//import android.content.Context
//import android.content.res.Configuration
//import io.flutter.plugin.common.BinaryMessenger
//import io.flutter.plugin.common.JSONMessageCodec
//import io.flutter.plugin.platform.PlatformView
//import io.flutter.plugin.platform.PlatformViewFactory
//
//class PlayerViewFactory(activity: Activity, messenger: BinaryMessenger) :
//    PlatformViewFactory(JSONMessageCodec.INSTANCE) {
//    private val activity: Activity
//    private var playerView: PlayerView? = null
//    private val messenger: BinaryMessenger
//
//    init {
//        this.activity = activity
//        this.messenger = messenger
//    }
//
//    fun create(context: Context?, id: Int, args: Any?): PlatformView? {
//        playerView = PlayerView(context, activity, id, messenger, args)
//        return playerView
//    }
//
//    fun onResume() {
//        if (playerView != null) {
//            playerView.onResume()
//        }
//    }
//
//    fun onPause() {
//        if (playerView != null) {
//            playerView.onPause()
//        }
//    }
//
//    fun onConfigurationChange(newConfig: Configuration?) {
//        if (playerView != null) {
//            playerView.onConfigurationChange(newConfig)
//        }
//    }
//}