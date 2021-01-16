package hayqua.app.complextest

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import hayqua.app.R
import hayqua.module.StickyHeadersRecyclerAdapter

class PeopleStickyHeadersAdapter(
    val mainActivity2: MainActivity2,
    sectionsHeader: Array<String>,
    textViewLayoutId: Int,
    sections: Array<Array<Person>>,
    populate: (LinearLayout, Person) -> Unit
) : StickyHeadersRecyclerAdapter<Person>(
    mainActivity2.layoutInflater,
    textViewLayoutId,
    sectionsHeader,
    sections,
    populate
) {

    override fun createItemView(parent: ViewGroup): View {
        val itemView = layoutInflator.inflate(R.layout.person_row, parent, false)
        return itemView
    }

    override fun onBindUpdateWithFeatures(itemView: View, data: Person) {
        super.onBindUpdateWithFeatures(itemView, data)
        itemView.findViewById<View>(R.id.waveBtn).setOnClickListener {
            Toast.makeText(mainActivity2, "Hello ${data.name}", Toast.LENGTH_SHORT).show()
        }
    }
}