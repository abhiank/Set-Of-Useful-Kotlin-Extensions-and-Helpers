package com.crazylegend.kotlinextensions.viewpager

import androidx.viewpager.widget.ViewPager


/**
 * Created by hristijan on 3/5/19 to long live and prosper !
 */

fun ViewPager.onPageScrollStateChanged(onPageScrollStateChanged: (state: Int) -> Unit = { _ -> }) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
        }

    })
}


fun ViewPager.onPageScrolled(onPageScrolled: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit = { _, _, _ -> }) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
        }

    })
}

fun ViewPager.onPageSelected(onPageSelected: (position: Int) -> Unit = { _ -> }) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            onPageSelected(position)
        }

    })
}

fun ViewPager.listener(onPageScrollStateChanged: (state: Int) -> Unit = { _ -> },
                       onPageSelected: (position: Int) -> Unit = { _ -> },
                       onPageScrolled: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit = { _, _, _ -> }) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            onPageSelected(position)
        }
    })
}

fun ViewPager.back(animate: Boolean = true) {
    setCurrentItem(currentItem - 1, animate)
}

fun ViewPager.forward(animate: Boolean = true) {
    setCurrentItem(currentItem + 1, animate)
}

fun ViewPager.isOnLastPage(): Boolean {
    return currentItem == (adapter?.count ?: 0) - 1
}

fun ViewPager.isOnFirstPage(): Boolean {
    return currentItem == 0
}

/**
 * Checks if ViewPager can swipe back.
 */
fun ViewPager.canGoBack() = currentItem > 0

/**
 * Checks if ViewPager can swipe next
 */
fun ViewPager.canGoNext() = adapter != null && currentItem < adapter!!.count - 1

/**
 * Swipes ViewPager back
 */
fun ViewPager.goPrevious() {
    if (canGoBack()) currentItem -= 1
}

/**
 * Swipes ViewPager next
 */
fun ViewPager.goNext() {
    if (canGoNext()) currentItem += 1
}

val ViewPager.length: Int?
    get() = adapter?.count

val ViewPager.lastIndex: Int?
    get() = adapter?.count?.minus(1)

val ViewPager.isLastView: Boolean
    get() = currentItem == length?.minus(1)

fun ViewPager.next() {
    if (!isLastView) {
        currentItem += 1
    }
}

fun ViewPager.next(lastCallback: () -> Unit) {
    if (!isLastView) {
        currentItem += 1
    } else {
        lastCallback()
    }
}

fun ViewPager.nextCircular() {
    if (!isLastView) {
        currentItem += 1
    } else {
        currentItem = 0
    }
}

