//package jwplayer.jwplayer
//import android.view.Window
//import android.view.WindowManager
//import com.longtailvideo.jwplayer.JWPlayerView
//import com.longtailvideo.jwplayer.events.AdCompleteEvent
//import com.longtailvideo.jwplayer.events.AdErrorEvent
//import com.longtailvideo.jwplayer.events.AdPauseEvent
//import com.longtailvideo.jwplayer.events.AdPlayEvent
//import com.longtailvideo.jwplayer.events.AdSkippedEvent
//import com.longtailvideo.jwplayer.events.CompleteEvent
//import com.longtailvideo.jwplayer.events.ErrorEvent
//import com.longtailvideo.jwplayer.events.PauseEvent
//import com.longtailvideo.jwplayer.events.PlayEvent
//import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents
//import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents
//
//class KeepScreenOnHandler(jwPlayerView: JWPlayerView, window: Window) :
//    VideoPlayerEvents.OnPlayListener,
//    VideoPlayerEvents.OnPauseListener, VideoPlayerEvents.OnCompleteListener,
//    VideoPlayerEvents.OnErrorListener,
//    AdvertisingEvents.OnAdPlayListener, AdvertisingEvents.OnAdPauseListener,
//    AdvertisingEvents.OnAdCompleteListener,
//    AdvertisingEvents.OnAdSkippedListener, AdvertisingEvents.OnAdErrorListener {
//    /**
//     * The application window
//     */
//    private val mWindow: Window
//
//    init {
//        jwPlayerView.addOnPlayListener(this)
//        jwPlayerView.addOnPauseListener(this)
//        jwPlayerView.addOnCompleteListener(this)
//        jwPlayerView.addOnErrorListener(this)
//        jwPlayerView.addOnAdPlayListener(this)
//        jwPlayerView.addOnAdPauseListener(this)
//        jwPlayerView.addOnAdCompleteListener(this)
//        jwPlayerView.addOnAdErrorListener(this)
//        mWindow = window
//    }
//
//    private fun updateWakeLock(enable: Boolean) {
//        if (enable) {
//            mWindow.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        } else {
//            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        }
//    }
//
//    fun onError(errorEvent: ErrorEvent?) {
//        updateWakeLock(false)
//    }
//
//    fun onAdPlay(adPlayEvent: AdPlayEvent?) {
//        updateWakeLock(true)
//    }
//
//    fun onAdPause(adPauseEvent: AdPauseEvent?) {
//        updateWakeLock(false)
//    }
//
//    fun onAdComplete(adCompleteEvent: AdCompleteEvent?) {
//        updateWakeLock(false)
//    }
//
//    fun onAdSkipped(adSkippedEvent: AdSkippedEvent?) {
//        updateWakeLock(false)
//    }
//
//    fun onAdError(adErrorEvent: AdErrorEvent?) {
//        updateWakeLock(false)
//    }
//
//    fun onComplete(completeEvent: CompleteEvent?) {
//        updateWakeLock(false)
//    }
//
//    fun onPause(pauseEvent: PauseEvent?) {
//        updateWakeLock(false)
//    }
//
//    fun onPlay(playEvent: PlayEvent?) {
//        updateWakeLock(true)
//    }
//
//    /* player destroyed */
//    fun destroy() {
//        updateWakeLock(false)
//    }
//}