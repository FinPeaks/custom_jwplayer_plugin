//package jwplayer.jwplayer
//
//import android.app.ActionBar
//import android.app.Activity
//import android.content.Context
//import android.content.res.Configuration
//import android.view.KeyEvent
//import android.widget.FrameLayout
//import com.longtailvideo.jwplayer.JWPlayerView
//import com.longtailvideo.jwplayer.configuration.PlayerConfig
//import com.longtailvideo.jwplayer.events.FullscreenEvent
//import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents
//import org.json.JSONObject
//import io.flutter.plugin.common.BinaryMessenger
//import io.flutter.plugin.common.EventChannel
//import io.flutter.plugin.common.JSONMethodCodec
//
//class PlayerLayout(
//    @NonNull context: Context?,
//    activity: Activity,
//    messenger: BinaryMessenger,
//    arguments: Any
//) :
//    FrameLayout(context), VideoPlayerEvents.OnFullscreenListener {
//    /**
//     * Reference to the [JWPlayerView]
//     */
//    private var mPlayerView: JWPlayerView? = null
//
//    /**
//     * Reference to the [PlayerConfig]
//     */
//    private val playerConfig: PlayerConfig = Builder().build()
//
//    /**
//     * An instance of our event handling class
//     */
//    private var mEventHandler: JWEventHandler? = null
//
//    /**
//     * App main activity
//     */
//    private val activity: Activity
//    private val messenger: BinaryMessenger
//    private var eventChannel: EventChannel? = null
//    private var keepScreenOnHandler: KeepScreenOnHandler? = null
//
//    init {
//        this.activity = activity
//        this.messenger = messenger
//        try {
//            val args: JSONObject = arguments as JSONObject
//            setFile(args.getString("file"))
//            setAutoPlay(args.getBoolean("autoPlay"))
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    private fun initPlayer() {
//        mPlayerView = JWPlayerView(activity, playerConfig)
//
//        /* handle hiding/showing of ActionBar */mPlayerView.addOnFullscreenListener(this)
//
//        /* enable background audio */mPlayerView.setBackgroundAudio(true)
//
//        /* keep the screen on during playback */keepScreenOnHandler =
//            KeepScreenOnHandler(mPlayerView, activity.getWindow())
//        mEventHandler = JWEventHandler(mPlayerView)
//        eventChannel = EventChannel(
//            messenger,
//            "jwplayer/JWEventHandler",
//            JSONMethodCodec.INSTANCE
//        )
//        eventChannel.setStreamHandler(mEventHandler)
//        this.addView(mPlayerView)
//    }
//
//    fun setFile(file: String?) {
//        playerConfig.setFile(file)
//        initPlayer()
//    }
//
//    fun setAutoPlay(autoPlay: Boolean) {
//        playerConfig.setAutostart(autoPlay)
//        if (mPlayerView != null && autoPlay) {
//            mPlayerView.play()
//        }
//    }
//
//    fun onConfigurationChanged(newConfig: Configuration) {
//
//        /* set fullscreen when the device is rotated to landscape */
//        if (mPlayerView != null) {
//            mPlayerView.setFullscreen(
//                newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE,
//                true
//            )
//        }
//        super.onConfigurationChanged(newConfig)
//    }
//
//    fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        /* exit fullscreen when the user pressed the Back button */
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mPlayerView.getFullscreen()) {
//                mPlayerView.setFullscreen(false, true)
//                return false
//            }
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//
//    fun onFullscreen(fullscreenEvent: FullscreenEvent) {
//        val actionBar: ActionBar = activity.getActionBar()
//        if (actionBar != null) {
//            if (fullscreenEvent.getFullscreen()) {
//                actionBar.hide()
//            } else {
//                actionBar.show()
//            }
//        }
//    }
//
//    fun retryFailedPlayback() {
//        try {
//
//            /* retry playback */
//            mPlayerView.play()
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onHostResume() {
//        try {
//
//            /* let JW Player know that the app has returned from the background */
//            mPlayerView.onResume()
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onHostPause() {
//        try {
//
//            /* let JW Player know that the app is going to the background */
//            mPlayerView.onPause()
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onHostDestroy() {
//        try {
//
//            /* stop controlling screen wake lock */
//            keepScreenOnHandler.destroy()
//
//            /* let JW Player know that the app is being destroyed */mPlayerView.onDestroy()
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//}