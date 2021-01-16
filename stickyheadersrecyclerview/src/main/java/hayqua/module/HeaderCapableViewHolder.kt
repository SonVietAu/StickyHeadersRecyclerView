package hayqua.module

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout

/**
 * HeaderCapableViewHolder is a view holder that can display a section header within it's itemView.
 * @param headerCapableLLItemView is required to be a LinearLayout as the layout is the simplest one to display the section header without needing to further specify layout parameters
 */
class HeaderCapableViewHolder(val headerCapableLLItemView: LinearLayout) :
    RecyclerView.ViewHolder(headerCapableLLItemView) {

    private var headerView: View? = null

    fun addHeaderView(headerView: View) {
        this.headerView = headerView
        headerCapableLLItemView.addView(headerView, 0)
        headerCapableLLItemView.invalidate()
    }

    // Must be call to free headerView from its parent, a viewHolder that was header-positioned to be use in another viewHolder that just became header-positioned
    fun removeHeaderView() {
        if (headerView != null) {
            headerCapableLLItemView.removeView(headerView)
            headerView = null
        }
    }

}

