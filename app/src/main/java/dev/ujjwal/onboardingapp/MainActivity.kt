package dev.ujjwal.onboardingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TOTAL_SLIDE = 4
        var CURRENT_SLIDE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sliderAdapter = SliderAdapter(this)
        view_pager.adapter = sliderAdapter

        addDotIndicator(0)

        val viewPagerPageChangeListener = object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                addDotIndicator(position)
                CURRENT_SLIDE = position

                when (position) {
                    0 -> {
                        btn_prev.visibility = View.INVISIBLE
                    }
                    TOTAL_SLIDE - 1 -> {
                        btn_next.text = getString(R.string.finish)
                    }
                    else -> {
                        btn_prev.visibility = View.VISIBLE
                        btn_next.text = getString(R.string.next)
                    }
                }
            }
        }
        view_pager.addOnPageChangeListener(viewPagerPageChangeListener)

        btn_prev.setOnClickListener {
            view_pager.currentItem = CURRENT_SLIDE - 1
        }
        btn_next.setOnClickListener {
            if (CURRENT_SLIDE == TOTAL_SLIDE - 1) {
                finish()
            } else {
                view_pager.currentItem = CURRENT_SLIDE + 1
            }
        }
    }


    private fun addDotIndicator(currentPage: Int) {
        val dots = arrayListOf<TextView>()
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)

        layout_dots.removeAllViews()
        for (i in 0 until TOTAL_SLIDE) {
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#8226;")
            dots[i].textSize = 35f
            dots[i].setTextColor(colorsInactive[currentPage])

            layout_dots.addView(dots[i])
        }

        if (dots.size > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage])
    }
}
