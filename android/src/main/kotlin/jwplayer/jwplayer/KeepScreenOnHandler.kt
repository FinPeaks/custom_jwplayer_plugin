package jwplayer.jwplayer

import android.view.Window
import android.view.WindowManager
import com.jwplayer.pub.api.JWPlayer
import com.jwplayer.pub.api.events.AdCompleteEvent
import com.jwplayer.pub.api.events.AdErrorEvent
import com.jwplayer.pub.api.events.AdPauseEvent
import com.jwplayer.pub.api.events.AdPlayEvent
import com.jwplayer.pub.api.events.AdSkippedEvent
import com.jwplayer.pub.api.events.CompleteEvent
import com.jwplayer.pub.api.events.ErrorEvent
import com.jwplayer.pub.api.events.EventType
import com.jwplayer.pub.api.events.PauseEvent
import com.jwplayer.pub.api.events.PlayEvent
import com.jwplayer.pub.api.events.listeners.AdvertisingEvents
import com.jwplayer.pub.api.events.listeners.VideoPlayerEvents

/**
 * Sets the FLAG_KEEP_SCREEN_ON flag during playback - disables it when playback is stopped
 */
class KeepScreenOnHandler(player: JWPlayer, window: Window) : VideoPlayerEvents.OnPlayListener,
    VideoPlayerEvents.OnPauseListener, VideoPlayerEvents.OnCompleteListener,
    VideoPlayerEvents.OnErrorListener, AdvertisingEvents.OnAdPlayListener,
    AdvertisingEvents.OnAdPauseListener, AdvertisingEvents.OnAdCompleteListener,
    AdvertisingEvents.OnAdSkippedListener, AdvertisingEvents.OnAdErrorListener {
    /**
     * The application window
     */
    private val mWindow: Window

    init {
        player.addListener(EventType.PLAY, this)
        player.addListener(EventType.PAUSE, this)
        player.addListener(EventType.COMPLETE, this)
        player.addListener(EventType.ERROR, this)
        player.addListener(EventType.AD_PLAY, this)
        player.addListener(EventType.AD_PAUSE, this)
        player.addListener(EventType.AD_COMPLETE, this)
        player.addListener(EventType.AD_ERROR, this)
        mWindow = window
    }

    private fun updateWakeLock(enable: Boolean) {
        if (enable) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    override fun onError(errorEvent: ErrorEvent?) {
        updateWakeLock(false)
    }

    override fun onAdPlay(adPlayEvent: AdPlayEvent?) {
        updateWakeLock(true)
    }

    override fun onAdPause(adPauseEvent: AdPauseEvent?) {
        updateWakeLock(false)
    }

    override fun onAdComplete(adCompleteEvent: AdCompleteEvent?) {
        updateWakeLock(false)
    }

    override fun onAdSkipped(adSkippedEvent: AdSkippedEvent?) {
        updateWakeLock(false)
    }

    override fun onAdError(adErrorEvent: AdErrorEvent?) {
        updateWakeLock(false)
    }

    override fun onComplete(completeEvent: CompleteEvent?) {
        updateWakeLock(false)
    }

    override fun onPause(pauseEvent: PauseEvent?) {
        updateWakeLock(false)
    }

    override fun onPlay(playEvent: PlayEvent?) {
        updateWakeLock(true)
    }
}
