//package jwplayer.jwplayer
//
//import android.view.Window
//import android.view.WindowManager
//import com.jwplayer.pub.api.JWPlayer
//import com.jwplayer.pub.api.events.AdCompleteEvent
//import com.jwplayer.pub.api.events.AdErrorEvent
//import com.jwplayer.pub.api.events.AdPauseEvent
//import com.jwplayer.pub.api.events.AdPlayEvent
//import com.jwplayer.pub.api.events.AdSkippedEvent
//import com.jwplayer.pub.api.events.CompleteEvent
//import com.jwplayer.pub.api.events.ErrorEvent
//import com.jwplayer.pub.api.events.EventType
//import com.jwplayer.pub.api.events.PauseEvent
//import com.jwplayer.pub.api.events.PlayEvent
//import com.jwplayer.pub.api.events.listeners.AdvertisingEvents
//import com.jwplayer.pub.api.events.listeners.VideoPlayerEvents
//
///**
// * Sets the FLAG_KEEP_SCREEN_ON flag during playback - disables it when playback is stopped
// */
//class KeepScreenOnHandler(player: JWPlayer, window: Window) : VideoPlayerEvents.OnPlayListener,
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
//        player.addListener(EventType.PLAY, this)
//        player.addListener(EventType.PAUSE, this)
//        player.addListener(EventType.COMPLETE, this)
//        player.addListener(EventType.ERROR, this)
//        player.addListener(EventType.AD_PLAY, this)
//        player.addListener(EventType.AD_PAUSE, this)
//        player.addListener(EventType.AD_COMPLETE, this)
//        player.addListener(EventType.AD_ERROR, this)
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
//    @Override
//    fun onError(errorEvent: ErrorEvent?) {
//        updateWakeLock(false)
//    }
//
//    @Override
//    fun onAdPlay(adPlayEvent: AdPlayEvent?) {
//        updateWakeLock(true)
//    }
//
//    @Override
//    fun onAdPause(adPauseEvent: AdPauseEvent?) {
//        updateWakeLock(false)
//    }
//
//    @Override
//    fun onAdComplete(adCompleteEvent: AdCompleteEvent?) {
//        updateWakeLock(false)
//    }
//
//    @Override
//    fun onAdSkipped(adSkippedEvent: AdSkippedEvent?) {
//        updateWakeLock(false)
//    }
//
//    @Override
//    fun onAdError(adErrorEvent: AdErrorEvent?) {
//        updateWakeLock(false)
//    }
//
//    @Override
//    fun onComplete(completeEvent: CompleteEvent?) {
//        updateWakeLock(false)
//    }
//
//    @Override
//    fun onPause(pauseEvent: PauseEvent?) {
//        updateWakeLock(false)
//    }
//
//    @Override
//    fun onPlay(playEvent: PlayEvent?) {
//        updateWakeLock(true)
//    }
//}
