package di2k.lintaspena.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import di2k.lintaspena.instagramlayout.R
import kotlinx.android.synthetic.main.activity_fullscreen.*

class FullscreenActivity : AppCompatActivity() {
    private val mHideHandler = Handler()
    private var mContentView: View? = null
    private val mHidePart2Runnable = object : Runnable {
        @SuppressLint("InlinedApi")
        @Override
        override fun run() {
            mContentView!!.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }
    private var mControlsView: View? = null
    private val mShowPart2Runnable = object : Runnable {
        @Override
        override fun run() {
            // Delayed display of UI elements
            val actionBar = getSupportActionBar()
            if (actionBar != null) {
                actionBar.show()
            }
            mControlsView!!.setVisibility(View.VISIBLE)
        }
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = object : Runnable {
        @Override
        override fun run() {
            hide()
        }
    }


    private val mDelayHideTouchListener = object : View.OnTouchListener {
        @Override
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS)
            }
            return false
        }
    }

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)

        mVisible = true
        mControlsView = findViewById(R.id.fullscreen_content_controls)
        mContentView = findViewById(R.id.fullscreen_content)


        // Set up the user interaction to manually show or hide the system UI.
        mContentView!!.setOnClickListener(object : View.OnClickListener {
            @Override
            override fun onClick(view: View) {
                toggle()
            }

        } )

        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener)


    }

    @Override
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        delayedHide(100)
    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        val actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.hide()
        }
        mControlsView!!.setVisibility(View.GONE)
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    @SuppressLint("InlinedApi")
    private fun show() {
        // Show the system bar
        mContentView!!.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        mVisible = true

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }


    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        //Created by 디딬 Didik M. Hadiningrat on 22 July 2019
        private val AUTO_HIDE = true

        private val AUTO_HIDE_DELAY_MILLIS = 3000

        private val UI_ANIMATION_DELAY = 300
    }
}