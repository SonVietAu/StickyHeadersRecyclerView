package hayqua.stickyheadersrecyclerview

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

/**
 * StickyHeadersRecyclerView is a wrapper designed to work exactly like a RecyclerView with the exception that the wrapper supports sticky sections header. Implementation wise, StickyHeadersRecyclerView uses RelativeLayout ability to overlay views on top of other views allowing a copy of the sections header to remain 'sticky' at the top of recycler views. To use this view, a {@link StickyHeadersRecyclerAdapter} must be given.
 *
 */

class StickyHeadersRecyclerView(context: Context, attributeSet: AttributeSet? = null) :
    RelativeLayout(context, attributeSet) {

    private val wrappedRecyclerView = RecyclerView(context)

    // wrapping RecyclerView adapter and enforce use of StickyHeadersRecyclerAdapter
    var adapter: StickyHeadersRecyclerAdapter<*>?
        get() = wrappedRecyclerView.adapter as StickyHeadersRecyclerAdapter<*>
        set(value) {
            wrappedRecyclerView.adapter = value
        }

    private var currentStickyHeaderView: View? = null

    init {
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        wrappedRecyclerView.layoutParams = layoutParams
        // Implementation can currently only support LinearLayout
        wrappedRecyclerView.layoutManager = LinearLayoutManager(context)

        // Working out if a sticky section header need to be display
        wrappedRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val topChild = recyclerView?.getChildAt(0)
                if (topChild != null) {
                    val topChildPosition = recyclerView.getChildAdapterPosition(topChild)

                    val headerCopyView = adapter?.getCurrentPositionStickyHeaderView(
                        topChildPosition
                    )!!

                    if (currentStickyHeaderView == null || !(currentStickyHeaderView === headerCopyView)) {
                        if (currentStickyHeaderView != null)
                            this@StickyHeadersRecyclerView.removeView(currentStickyHeaderView)
                        this@StickyHeadersRecyclerView.addView(headerCopyView)
                        this@StickyHeadersRecyclerView.currentStickyHeaderView = headerCopyView
                    }

                    if (currentStickyHeaderView != null) {
                        if (adapter?.isPositionAHeader(
                                topChildPosition + 1
                            ) ?: false
                        ) {
                            val secondChild = recyclerView.getChildAt(1)
                            // Ensuring the bottom of currentStickyHeaderView is just above the header-positioned secondChild
                            if (currentStickyHeaderView!!.height > secondChild.y) {
                                currentStickyHeaderView!!.y = -(currentStickyHeaderView!!.height - secondChild.y)
                                currentStickyHeaderView!!.invalidate()
                            }
                        } else {
                            // Reset the position if needed
                            if (currentStickyHeaderView!!.y != 0f) {
                                currentStickyHeaderView!!.y = 0f
                                currentStickyHeaderView!!.invalidate()
                            }
                        }
                    }

                }
            }
        })

        addView(wrappedRecyclerView)
    }
}