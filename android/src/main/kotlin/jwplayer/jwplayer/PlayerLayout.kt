package jwplayer.jwplayer

import android.app.Activity
import android.content.Context
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

import androidx.lifecycle.LifecycleOwner
import com.jwplayer.pub.view.JWPlayerView
import com.jwplayer.pub.api.JWPlayer
import android.view.View
import android.view.ViewGroup


class PlayerLayout(
    context: Context,
    /**
     * App main activity
     */
    private val activity: Activity,

    private val owner: LifecycleOwner
) : ConstraintLayout(context) {

    var mPlayerView: JWPlayerView? = null

    private fun initPlayer() {
        mPlayerView = JWPlayerView(context)
//        println("......" + R.id.activity_jwplayerview)
//        mPlayerView = findViewById(R.id.activity_jwplayerview)

//        mPlayerView = findViewById(R.id.jwplayer)
//        val mPlayer: JWPlayer = mPlayerView.getPlayer()?
        println("----- " + mPlayerView)
        try {
            //add the view with 0dp width and height
//            val layoutParams = ConstraintLayout.LayoutParams(0, 0)
//            val view = View(context)
//            view.layoutParams = layoutParams
//            view.id = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) View.generateViewId() else R.id.activity_jwplayerview


//            //apply the default width and height constraints in code
//            val constraints = ConstraintSet()
////            constraints.clone(parent)
//            constraints.constrainDefaultHeight(view.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
//            constraints.constrainDefaultWidth(view.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
//            constraints.applyTo(parent)
        } catch (e: Exception) {
            println(e)
        }
    }

    init {
        initPlayer()
    }
}

