package hayqua.app

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import hayqua.module.StickyHeadersRecyclerAdapter

fun populateAString(headerCapableLLItemView: LinearLayout, data: String) {
    headerCapableLLItemView.findViewById<TextView>(R.id.infoTV).text = data
}

internal class VehiclesStickyHeadersRecyclerAdapter(
    val mainActivity: AppCompatActivity,
    val sectionsHeader: Array<String>,
    sections: Array<Array<String>>,
    populate: (LinearLayout, String) -> Unit
) : StickyHeadersRecyclerAdapter<String>(
    mainActivity.layoutInflater,
    R.layout.header_copy,
    sectionsHeader,
    sections,
    populate
) {

    override fun createItemView(parent: ViewGroup): View {
        return mainActivity.layoutInflater.inflate(R.layout.atextview, parent, false)
    }

}