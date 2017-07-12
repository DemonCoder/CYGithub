package com.github.ui.activity

import com.github.R
import com.github.base.SimpleActivity
import com.github.ui.fragment.FaceFragment
import com.github.ui.fragment.LampFragment
import com.github.ui.fragment.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*
import me.yokeyword.fragmentation.SupportFragment
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView

class MainActivity : SimpleActivity() {

    var mFragments: MutableList<SupportFragment>? = null
    var mTabPosition: Int = 0

    override fun getLayout(): Int = R.layout.activity_main

    override fun initData() {

    }

    override fun initView() {
        initBottomNavigationView()
        initFragment()
    }

    /**
     * 初始化三个Fragment
     */
    private fun initFragment() {
        mFragments = mutableListOf<SupportFragment>()

        var faceFragment = FaceFragment()
        var weatherFragment = WeatherFragment()
        var lampFragment = LampFragment()

        mFragments?.add(faceFragment)
        mFragments?.add(weatherFragment)
        mFragments?.add(lampFragment)

        loadMultipleRootFragment(R.id.fl_container, 0,
                mFragments?.get(0),
                mFragments?.get(1),
                mFragments?.get(2))

    }

    /**
     * 初始化底部导航栏
     */
    private fun initBottomNavigationView() {
        bnve.enableAnimation(false)
        bnve.enableShiftingMode(false)
        bnve.enableItemShiftingMode(false)

        bnve.setOnNavigationItemSelectedListener {
            item ->
            var title = item.title
            var position = bnve.getMenuItemPosition(item)
            showHideFragment(mFragments?.get(position), mFragments?.get(mTabPosition))
            mTabPosition = position
            true
        }

        addBadgeAt(2, 1)
    }

    /**
     * 给底部导航栏增加气泡
     */
    private fun addBadgeAt(position: Int, number: Int): Badge {
        // add badge
        return QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12F, 2F, true)
                .bindTarget(bnve.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener { dragState, badge, targetView ->
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED === dragState) {
                        //清除气泡的回调
                    }
                }
    }

}
