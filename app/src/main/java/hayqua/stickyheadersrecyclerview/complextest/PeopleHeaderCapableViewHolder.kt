package hayqua.stickyheadersrecyclerview.complextest

import android.widget.LinearLayout
import android.widget.TextView
import hayqua.stickyheadersrecyclerview.R

fun populateWithAPerson(itemView: LinearLayout, data: Person) {
    itemView.findViewById<TextView>(R.id.idAndNameTV).text = "${data.id}: ${data.name}"
    itemView.findViewById<TextView>(R.id.ageTV).text = "${data.age}"
    itemView.findViewById<TextView>(R.id.genderTV).text = data.gender
    itemView.findViewById<TextView>(R.id.heightTV).text = "${data.height}"
}