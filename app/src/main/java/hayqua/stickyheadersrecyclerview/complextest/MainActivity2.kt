package hayqua.stickyheadersrecyclerview.complextest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hayqua.stickyheadersrecyclerview.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecyclerView.adapter =
            PeopleStickyHeadersAdapter(
                this, headers, R.layout.header_copy,
                arrayOf(
                    SydneySiders, Melbourians,
                    Bananabenders, CrowEaters,
                    Sandgropers
                ),
                ::populateWithAPerson
            )
    }
}