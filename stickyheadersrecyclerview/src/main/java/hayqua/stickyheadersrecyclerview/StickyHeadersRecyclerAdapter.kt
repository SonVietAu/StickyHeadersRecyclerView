package hayqua.stickyheadersrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*

/**
 * StickyHeadersRecyclerAdapter handles the displaying of the data 'B' for the {@link StickyHeadersRecyclerView}.
 *
 * @param layoutInflator for inflating the sections header TextViews
 * @param sectionsHeader as a list for headers for displaying in the header TextView
 * @param sectionHeaderTextViewLayoutId is a customisable TextView that will be used to display the headers
 * @param sections as a list of lists of the data 'B'
 * @param populate is a function that is use to display the data 'B' on the RecyclerView ViewHolder itemView
 *
 * @param <HeaderCapableViewHolder<B>> as the final ViewHolder that this StickyHeadersRecyclerAdapter will support. The {@link HeaderCapableViewHolder} is a ViewHolder that is capable of using the header TextViews created as part of this adapter
 *
 * To use this adapter, the 'createItemView' function must be implemented to return a view that is capable of displaying the data 'B'.
 */

abstract class StickyHeadersRecyclerAdapter<B>(
    val layoutInflator: LayoutInflater,
    sectionHeaderTextViewLayoutId: Int,
    sectionsHeader: Array<String>,
    private val sections: Array<Array<B>>,
    private val populate: (LinearLayout, B) -> Unit
) : RecyclerView.Adapter<HeaderCapableViewHolder>() {

    // Note: An implementation of this function could also have been passed in as a parameter
    abstract fun createItemView(parent: ViewGroup): View

    // Allow overriding implementations to set up features that may require to process the data. For example, showing a message with information from the data. The itemView is available to give access to views that feature maybe actioned from
    open fun onBindUpdateWithFeatures(itemView: View, data: B) {}


    private val headerPositionsV = Vector<Int>()
    // The header views will be inserted into a ViewHolder
    private val headerViewsMap = Vector<Pair<Int, View>>()
    // The copies will be used as sticky headers
    private val headerViewsCopyMap = Vector<Pair<Int, View>>()

    private val count: Int

    init {
        if (sections.size != sections.size)
            throw RuntimeException("Number of headers must equal the number of sections")

        var populationCount = 0
        var headerIndexIncrementer = 0

        sections.forEach {
            headerPositionsV.addElement(populationCount)

            val header = layoutInflator.inflate(sectionHeaderTextViewLayoutId, null, false) as TextView
            header.text = sectionsHeader[headerIndexIncrementer]
            headerViewsMap.add(Pair(populationCount, header))

            val headerCopy = layoutInflator.inflate(sectionHeaderTextViewLayoutId, null, false) as TextView
            headerCopy.text = sectionsHeader[headerIndexIncrementer]

            val layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            headerCopy.layoutParams = layoutParams
            headerViewsCopyMap.add(Pair(populationCount, headerCopy))

            populationCount += it.size
            headerIndexIncrementer++
        }

        count = populationCount
    }

    override fun getItemCount(): Int {
        return count
    }

    fun isPositionAHeader(position: Int): Boolean {
        return headerPositionsV.contains(position)
    }

    // Finalised here to specifically require the use of HeaderCapableViewHolder
    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderCapableViewHolder {

        val headerCapableLLViewHolder = layoutInflator.inflate(R.layout.header_capable_view_holder, parent, false) as LinearLayout

        val itemView = createItemView(headerCapableLLViewHolder)

        val layoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        itemView.layoutParams = layoutParams

        headerCapableLLViewHolder.addView(itemView)
        headerCapableLLViewHolder.layoutParams = layoutParams

        return HeaderCapableViewHolder(headerCapableLLViewHolder)
    }

    // Finalized here to ensure header positioning is checked and displayed accordingly, ensure the populate function is called and allow for updating with other features that my need to process the data
    final override fun onBindViewHolder(holder: HeaderCapableViewHolder, position: Int) {
        if (isPositionAHeader(position)) {
            holder.addHeaderView(getCurrentHeaderView(position)!!)
        }
        val data = getData(position)
        populate(holder.itemView as @kotlin.ParameterName(name = "headerCapableLLItemView") LinearLayout, data)
        onBindUpdateWithFeatures(holder.itemView, data)
    }

    private fun getData(position: Int): B {
        var index = position
        sections.forEach {
            if (index < it.size)
                return it[index]
            else
                index -= it.size
        }
        // Should never get here. Currently a better solution is not known
        throw ArrayIndexOutOfBoundsException("Position $position does not match to any of the supplied data")
    }

    private fun getCurrentHeaderView(position: Int): View? {
        headerViewsMap.forEach {
            if (it.first == position)
                return it.second
        }
        return null
    }

    fun getCurrentPositionStickyHeaderView(position: Int): View? {
        var index: Int = headerViewsCopyMap.size - 1
        while (index >= 0) {
            val headerPair = headerViewsCopyMap[index]
            if (headerPair.first <= position) {
                return headerPair.second
            }
            index--
        }

        return null

    }

    // Finalised to ensure the removal of the header view from ViewHolders ensuring that multiple-parents-view exception do not occur
    final override fun onViewRecycled(holder: HeaderCapableViewHolder) {
        super.onViewRecycled(holder)
        holder.removeHeaderView()
    }
}

