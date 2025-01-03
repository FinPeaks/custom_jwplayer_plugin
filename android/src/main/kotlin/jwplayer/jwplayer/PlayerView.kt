package jwplayer.jwplayer

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.jwplayer.pub.api.JWPlayer
import com.jwplayer.pub.api.configuration.PlayerConfig
import com.jwplayer.pub.api.events.EventListener
import com.jwplayer.pub.api.events.EventType
import com.jwplayer.pub.view.JWPlayerView
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink
import io.flutter.plugin.platform.PlatformView


internal class PlayerView(
    context: Context?,
    activity: Activity?,
    eventSink: QueueEventSink,
    id: Int, owner: LifecycleOwner
) :
    PlatformView,
    PlayerInterface,
    EventListener {
    private var playerView: JWPlayerView
    private var mPlayer: JWPlayer? = null
    private var config: PlayerConfig? = null
    private var _eventSink: QueueEventSink
    private var _context: Context? = null
    private var _owner: LifecycleOwner

    init {
        val layout = PlayerLayout(context!!, activity!!, owner)
        playerView = layout.mPlayerView!!
        playerView.id = id
        _context = context
        _eventSink = eventSink
        _owner = owner

        if (config != null) {

            playerView!!.getPlayerAsync(
                context,
                owner,
                JWPlayer.PlayerInitializationListener { jwPlayer: JWPlayer ->
                    mPlayer = jwPlayer
                    mPlayer?.setup(config)
                    setupListeners(jwPlayer, eventSink)
                })
        }
    }

    private fun setupListeners(player: JWPlayer, eventSink: QueueEventSink) {
        player.addListeners(PlayerEventListener(eventSink), *AllEvents)
    }

    override fun getView(): View {
        return playerView
    }

    override fun setConfig(config: PlayerConfig) {
        this.config = config
//        mPlayer?.setup(config)

//        mPlayer = playerView!!.getPlayer(_owner)
//
//        mPlayer?.setup(config)
//        mPlayer?.addListeners(PlayerEventListener(_eventSink), *AllEvents)
//        setupListeners(mPlayer, _eventSink)



        playerView!!.getPlayerAsync(
            _context,
            _owner,
            JWPlayer.PlayerInitializationListener { jwPlayer: JWPlayer ->
                mPlayer = jwPlayer
                mPlayer?.setup(config)
                mPlayer?.addListeners(PlayerEventListener(_eventSink), *AllEvents)
//                setupListeners(mPlayer, _eventSink)
            })
    }

    override fun play() {
        mPlayer?.play()
    }

    override fun pause() {
        mPlayer?.pause()
    }

    override fun stop() {
        mPlayer?.stop()
    }

    override fun dispose() {
        mPlayer?.stop()
        mPlayer = null
    }
}

interface PlayerInterface {
    fun setConfig(config: PlayerConfig)
    fun play()
    fun pause()
    fun stop()
}