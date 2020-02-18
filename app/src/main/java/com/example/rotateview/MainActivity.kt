package com.example.rotateview

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val earthAnimator = animatePlanet(earth_image, TimeUnit.SECONDS.toMillis(2))
        val marsAnimator = animatePlanet(mars_image, TimeUnit.SECONDS.toMillis(8))
        val saturnAnimator =
            animatePlanet(saturn_image, TimeUnit.SECONDS.toMillis(10))

        earthAnimator.start()
        marsAnimator.start()
        saturnAnimator.start()

    }
    fun animatePlanet(imageview : ImageView, orbitDuration:Long ): ValueAnimator {

        val anim : ValueAnimator = ValueAnimator.ofInt(0,359)

        anim.addUpdateListener { valueAnimator: ValueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams =
                imageview.getLayoutParams() as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = value.toFloat()
            imageview.setLayoutParams(layoutParams)
        }

        anim.duration = orbitDuration
        anim.interpolator = LinearInterpolator()
        anim.repeatMode = ValueAnimator.RESTART
        anim.repeatCount = ValueAnimator.INFINITE

        return  anim
    }
}
