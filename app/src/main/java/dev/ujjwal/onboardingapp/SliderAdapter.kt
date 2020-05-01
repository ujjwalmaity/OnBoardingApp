package dev.ujjwal.onboardingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.layout_slide.view.*


class SliderAdapter(context: Context) : PagerAdapter() {

    private val mContext = context
    private lateinit var layoutInflater: LayoutInflater

    private val slideBackground = arrayListOf(
        R.color.bg_screen1,
        R.color.bg_screen2,
        R.color.bg_screen3,
        R.color.bg_screen4
    )
    private val slideImage = arrayListOf(
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3,
        R.drawable.a4
    )
    private val slideTitle = arrayListOf(
        R.string.slide_1_title,
        R.string.slide_2_title,
        R.string.slide_3_title,
        R.string.slide_4_title
    )
    private val slideDesc = arrayListOf(
        R.string.slide_1_desc,
        R.string.slide_2_desc,
        R.string.slide_3_desc,
        R.string.slide_4_desc
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = slideTitle.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.layout_slide, container, false)

        view.slide_layout.background = mContext.getDrawable(slideBackground[position])
        view.image_view.setImageResource(slideImage[position])
        view.tv_title.text = mContext.getText(slideTitle[position])
        view.tv_desc.text = mContext.getText(slideDesc[position])

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}