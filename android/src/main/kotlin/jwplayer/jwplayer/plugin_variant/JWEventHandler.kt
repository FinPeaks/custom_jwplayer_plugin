//package jwplayer.jwplayer
//
//import android.util.Log
//import com.longtailvideo.jwplayer.JWPlayerView
//import com.longtailvideo.jwplayer.events.AdClickEvent
//import com.longtailvideo.jwplayer.events.AdCompleteEvent
//import com.longtailvideo.jwplayer.events.AdErrorEvent
//import com.longtailvideo.jwplayer.events.AdImpressionEvent
//import com.longtailvideo.jwplayer.events.AdPauseEvent
//import com.longtailvideo.jwplayer.events.AdPlayEvent
//import com.longtailvideo.jwplayer.events.AdScheduleEvent
//import com.longtailvideo.jwplayer.events.AdSkippedEvent
//import com.longtailvideo.jwplayer.events.AdTimeEvent
//import com.longtailvideo.jwplayer.events.AudioTrackChangedEvent
//import com.longtailvideo.jwplayer.events.AudioTracksEvent
//import com.longtailvideo.jwplayer.events.BeforeCompleteEvent
//import com.longtailvideo.jwplayer.events.BeforePlayEvent
//import com.longtailvideo.jwplayer.events.BufferEvent
//import com.longtailvideo.jwplayer.events.CaptionsChangedEvent
//import com.longtailvideo.jwplayer.events.CaptionsListEvent
//import com.longtailvideo.jwplayer.events.CompleteEvent
//import com.longtailvideo.jwplayer.events.ControlBarVisibilityEvent
//import com.longtailvideo.jwplayer.events.ControlsEvent
//import com.longtailvideo.jwplayer.events.DisplayClickEvent
//import com.longtailvideo.jwplayer.events.ErrorEvent
//import com.longtailvideo.jwplayer.events.FirstFrameEvent
//import com.longtailvideo.jwplayer.events.FullscreenEvent
//import com.longtailvideo.jwplayer.events.IdleEvent
//import com.longtailvideo.jwplayer.events.LevelsChangedEvent
//import com.longtailvideo.jwplayer.events.LevelsEvent
//import com.longtailvideo.jwplayer.events.MetaEvent
//import com.longtailvideo.jwplayer.events.MuteEvent
//import com.longtailvideo.jwplayer.events.PauseEvent
//import com.longtailvideo.jwplayer.events.PlayEvent
//import com.longtailvideo.jwplayer.events.PlaylistCompleteEvent
//import com.longtailvideo.jwplayer.events.PlaylistEvent
//import com.longtailvideo.jwplayer.events.PlaylistItemEvent
//import com.longtailvideo.jwplayer.events.ReadyEvent
//import com.longtailvideo.jwplayer.events.SeekEvent
//import com.longtailvideo.jwplayer.events.SeekedEvent
//import com.longtailvideo.jwplayer.events.SetupErrorEvent
//import com.longtailvideo.jwplayer.events.TimeEvent
//import com.longtailvideo.jwplayer.events.VisualQualityEvent
//import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents
//import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents
//import org.json.JSONObject
//import io.flutter.plugin.common.EventChannel
//
//class JWEventHandler(jwPlayerView: JWPlayerView) : VideoPlayerEvents.OnSetupErrorListener,
//    VideoPlayerEvents.OnPlaylistListener, VideoPlayerEvents.OnPlaylistItemListener,
//    VideoPlayerEvents.OnPlayListener, VideoPlayerEvents.OnPauseListener,
//    VideoPlayerEvents.OnBufferListener,
//    VideoPlayerEvents.OnIdleListener, VideoPlayerEvents.OnErrorListener,
//    VideoPlayerEvents.OnSeekListener,
//    VideoPlayerEvents.OnTimeListener, VideoPlayerEvents.OnFullscreenListener,
//    VideoPlayerEvents.OnAudioTracksListener,
//    VideoPlayerEvents.OnAudioTrackChangedListener, VideoPlayerEvents.OnCaptionsListListener,
//    VideoPlayerEvents.OnMetaListener, VideoPlayerEvents.OnPlaylistCompleteListener,
//    VideoPlayerEvents.OnCompleteListener, VideoPlayerEvents.OnLevelsChangedListener,
//    VideoPlayerEvents.OnLevelsListener, VideoPlayerEvents.OnCaptionsChangedListener,
//    VideoPlayerEvents.OnControlsListener, VideoPlayerEvents.OnControlBarVisibilityListener,
//    VideoPlayerEvents.OnDisplayClickListener, VideoPlayerEvents.OnMuteListener,
//    VideoPlayerEvents.OnSeekedListener,
//    VideoPlayerEvents.OnVisualQualityListener, VideoPlayerEvents.OnFirstFrameListener,
//    VideoPlayerEvents.OnReadyListener, AdvertisingEvents.OnAdClickListener,
//    AdvertisingEvents.OnAdCompleteListener,
//    AdvertisingEvents.OnAdSkippedListener, AdvertisingEvents.OnAdErrorListener,
//    AdvertisingEvents.OnAdImpressionListener,
//    AdvertisingEvents.OnAdTimeListener, AdvertisingEvents.OnAdPauseListener,
//    AdvertisingEvents.OnAdPlayListener,
//    AdvertisingEvents.OnAdScheduleListener, AdvertisingEvents.OnBeforePlayListener,
//    AdvertisingEvents.OnBeforeCompleteListener, StreamHandler {
//    private val TAG: String = JWEventHandler::class.java.getName()
//    private var eventSink: EventChannel.EventSink? = null
//
//    init {
//        // Subscribe to all JW Player events
//        jwPlayerView.addOnReadyListener(this)
//        jwPlayerView.addOnFirstFrameListener(this)
//        jwPlayerView.addOnSetupErrorListener(this)
//        jwPlayerView.addOnPlaylistListener(this)
//        jwPlayerView.addOnPlaylistItemListener(this)
//        jwPlayerView.addOnPlayListener(this)
//        jwPlayerView.addOnPauseListener(this)
//        jwPlayerView.addOnBufferListener(this)
//        jwPlayerView.addOnIdleListener(this)
//        jwPlayerView.addOnErrorListener(this)
//        jwPlayerView.addOnSeekListener(this)
//        jwPlayerView.addOnTimeListener(this)
//        jwPlayerView.addOnFullscreenListener(this)
//        jwPlayerView.addOnLevelsChangedListener(this)
//        jwPlayerView.addOnLevelsListener(this)
//        jwPlayerView.addOnCaptionsListListener(this)
//        jwPlayerView.addOnCaptionsChangedListener(this)
//        //  jwPlayerView.addOnRelatedCloseListener(this);
//        //  jwPlayerView.addOnRelatedOpenListener(this);
//        //  jwPlayerView.addOnRelatedPlayListener(this);
//        jwPlayerView.addOnControlsListener(this)
//        jwPlayerView.addOnControlBarVisibilityListener(this)
//        jwPlayerView.addOnDisplayClickListener(this)
//        jwPlayerView.addOnMuteListener(this)
//        jwPlayerView.addOnVisualQualityListener(this)
//        jwPlayerView.addOnSeekedListener(this)
//        jwPlayerView.addOnAdClickListener(this)
//        jwPlayerView.addOnAdCompleteListener(this)
//        jwPlayerView.addOnAdSkippedListener(this)
//        jwPlayerView.addOnAdErrorListener(this)
//        jwPlayerView.addOnAdImpressionListener(this)
//        jwPlayerView.addOnAdTimeListener(this)
//        jwPlayerView.addOnAdPauseListener(this)
//        jwPlayerView.addOnAdPlayListener(this)
//        jwPlayerView.addOnMetaListener(this)
//        jwPlayerView.addOnPlaylistCompleteListener(this)
//        jwPlayerView.addOnCompleteListener(this)
//        jwPlayerView.addOnBeforePlayListener(this)
//        jwPlayerView.addOnBeforeCompleteListener(this)
//        jwPlayerView.addOnAdScheduleListener(this)
//    }
//
//    fun onListen(o: Any?, eventSink: EventChannel.EventSink?) {
//        this.eventSink = eventSink
//    }
//
//    fun onCancel(o: Any?) {
//        eventSink = null
//    }
//
//    fun onAdClick(adClickEvent: AdClickEvent?) {
//        Log.d(TAG, "onAdClick")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAdClick")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAdComplete(adCompleteEvent: AdCompleteEvent?) {
//        Log.d(TAG, "onAdComplete")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAdComplete")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAdError(adErrorEvent: AdErrorEvent?) {
//        Log.d(TAG, "onAdError")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAdError")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAdImpression(adImpressionEvent: AdImpressionEvent?) {
//        Log.d(TAG, "onAdImpression")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAdImpression")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAdPause(adPauseEvent: AdPauseEvent?) {
//        Log.d(TAG, "onAdPause")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAdPause")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAdPlay(adPlayEvent: AdPlayEvent?) {
//        Log.d(TAG, "onAdPlay")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAdPlay")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAdSchedule(adScheduleEvent: AdScheduleEvent?) {
//        Log.d(TAG, "onAdSchedule")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAdSchedule")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAdSkipped(adSkippedEvent: AdSkippedEvent?) {
//        Log.d(TAG, "onAdSkipped")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAdSkipped")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAdTime(adTimeEvent: AdTimeEvent?) {
//        Log.d(TAG, "onAdTime")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAdTime")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onBeforeComplete(beforeCompleteEvent: BeforeCompleteEvent?) {
//        Log.d(TAG, "onBeforeComplete")
//        try {
//            val message = JSONObject()
//            message.put("name", "onBeforeComplete")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onBeforePlay(beforePlayEvent: BeforePlayEvent?) {
//        Log.d(TAG, "onBeforePlay")
//        try {
//            val message = JSONObject()
//            message.put("name", "onBeforePlay")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAudioTrackChanged(audioTrackChangedEvent: AudioTrackChangedEvent?) {
//        Log.d(TAG, "onAudioTrackChanged")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAudioTrackChanged")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onAudioTracks(audioTracksEvent: AudioTracksEvent?) {
//        Log.d(TAG, "onAudioTracks")
//        try {
//            val message = JSONObject()
//            message.put("name", "onAudioTracks")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onBuffer(bufferEvent: BufferEvent) {
//        Log.d(TAG, "onBuffer")
//        try {
//            val message = JSONObject()
//            message.put("name", "onBuffer")
//            message.put("oldState", bufferEvent.getOldState())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onCaptionsChanged(captionsChangedEvent: CaptionsChangedEvent) {
//        Log.d(TAG, "onCaptionsChanged")
//        try {
//            val message = JSONObject()
//            message.put("name", "onCaptionsChanged")
//            message.put("currentTrack", captionsChangedEvent.getCurrentTrack())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onCaptionsList(captionsListEvent: CaptionsListEvent?) {
//        Log.d(TAG, "onCaptionsList")
//        try {
//            val message = JSONObject()
//            message.put("name", "onCaptionsList")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onComplete(completeEvent: CompleteEvent?) {
//        Log.d(TAG, "onComplete")
//        try {
//            val message = JSONObject()
//            message.put("name", "onComplete")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onControlBarVisibilityChanged(controlBarVisibilityEvent: ControlBarVisibilityEvent) {
//        Log.d(TAG, "onControlBarVisibilityChanged")
//        try {
//            val message = JSONObject()
//            message.put("name", "onControlBarVisibilityChanged")
//            message.put("controls", controlBarVisibilityEvent.isVisible())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onControls(controlsEvent: ControlsEvent) {
//        Log.d(TAG, "onControls")
//        try {
//            val message = JSONObject()
//            message.put("name", "onControls")
//            message.put("controls", controlsEvent.getControls())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onDisplayClick(displayClickEvent: DisplayClickEvent?) {
//        Log.d(TAG, "onDisplayClick")
//        try {
//            val message = JSONObject()
//            message.put("name", "onDisplayClick")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onError(errorEvent: ErrorEvent) {
//        Log.d(TAG, "onError")
//        try {
//            val message = JSONObject()
//            message.put("name", "onError")
//            message.put("message", errorEvent.getMessage())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onFirstFrame(firstFrameEvent: FirstFrameEvent) {
//        Log.d(TAG, "onFirstFrame")
//        try {
//            val message = JSONObject()
//            message.put("name", "onFirstFrame")
//            message.put("loadTime", firstFrameEvent.getLoadTime())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onFullscreen(fullscreenEvent: FullscreenEvent) {
//        Log.d(TAG, "onFullscreen")
//        try {
//            val message = JSONObject()
//            message.put("name", "onFullscreen")
//            message.put("fullscreen", fullscreenEvent.getFullscreen())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onIdle(idleEvent: IdleEvent?) {
//        Log.d(TAG, "onIdle")
//        try {
//            val message = JSONObject()
//            message.put("name", "onIdle")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onLevelsChanged(levelsChangedEvent: LevelsChangedEvent?) {
//        Log.d(TAG, "onLevelsChanged")
//        try {
//            val message = JSONObject()
//            message.put("name", "onLevelsChanged")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onLevels(levelsEvent: LevelsEvent?) {
//        Log.d(TAG, "onLevels")
//        try {
//            val message = JSONObject()
//            message.put("name", "onLevels")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onMeta(metaEvent: MetaEvent?) {
//        Log.d(TAG, "onMeta")
//        try {
//            val message = JSONObject()
//            message.put("name", "onMeta")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onMute(muteEvent: MuteEvent) {
//        Log.d(TAG, "onMute")
//        try {
//            val message = JSONObject()
//            message.put("name", "onMute")
//            message.put("mute", muteEvent.getMute())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onPause(pauseEvent: PauseEvent) {
//        Log.d(TAG, "onPause")
//        try {
//            val message = JSONObject()
//            message.put("name", "onPause")
//            message.put("oldState", pauseEvent.getOldState())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onPlay(playEvent: PlayEvent) {
//        Log.d(TAG, "onPlay")
//        try {
//            val message = JSONObject()
//            message.put("name", "onPlay")
//            message.put("oldState", playEvent.getOldState())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onPlaylistComplete(playlistCompleteEvent: PlaylistCompleteEvent?) {
//        Log.d(TAG, "onPlaylistComplete")
//        try {
//            val message = JSONObject()
//            message.put("name", "onPlaylistComplete")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onPlaylistItem(playlistItemEvent: PlaylistItemEvent?) {
//        Log.d(TAG, "onPlaylistItem")
//        try {
//            val message = JSONObject()
//            message.put("name", "onPlaylistItem")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onPlaylist(playlistEvent: PlaylistEvent?) {
//        Log.d(TAG, "onPlaylist")
//        try {
//            val message = JSONObject()
//            message.put("name", "onPlaylist")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onReady(readyEvent: ReadyEvent) {
//        Log.d(TAG, "onReady")
//        try {
//            val message = JSONObject()
//            message.put("name", "onReady")
//            message.put("setupTime", readyEvent.getSetupTime())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onSeek(seekEvent: SeekEvent) {
//        Log.d(TAG, "onSeek")
//        try {
//            val message = JSONObject()
//            message.put("name", "onSeek")
//            message.put("offset", seekEvent.getOffset())
//            message.put("position", seekEvent.getPosition())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onSeeked(seekedEvent: SeekedEvent) {
//        Log.d(TAG, "onSeeked")
//        try {
//            val message = JSONObject()
//            message.put("name", "onSeeked")
//            message.put("position", seekedEvent.getPosition())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onSetupError(setupErrorEvent: SetupErrorEvent) {
//        Log.d(TAG, "onSetupError")
//        try {
//            val message = JSONObject()
//            message.put("name", "onSetupError")
//            message.put("message", setupErrorEvent.getMessage())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onTime(timeEvent: TimeEvent) {
//        Log.d(TAG, "onTime")
//        try {
//            val message = JSONObject()
//            message.put("name", "onTime")
//            message.put("duration", timeEvent.getDuration())
//            message.put("position", timeEvent.getPosition())
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//
//    fun onVisualQuality(visualQualityEvent: VisualQualityEvent?) {
//        Log.d(TAG, "onVisualQuality")
//        try {
//            val message = JSONObject()
//            message.put("name", "onVisualQuality")
//            eventSink.success(message)
//        } catch (e: java.lang.Exception) { /* ignore */
//        }
//    }
//}