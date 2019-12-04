package hayqua.stickyheadersrecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cars = arrayOf(
            "Sedan", "Wagon", "Coup", "Sport",
            "Sedan1", "Wagon", "Coup", "Sport",
            "Sedan2", "Wagon", "Coup", "Sport",
            "Sedan3", "Wagon", "Coup", "Sport",
            "Sedan4", "Wagon", "Coup", "Sport",
            "Sedan5", "Wagon", "Coup", "Sport5")
        val motorCycles = arrayOf(
            "Chopper", "Spyder RS", "Freewheeler 114", "Street Bob 107",
            "Chopper1", "Spyder RS1", "Freewheeler 114", "Street Bob 107",
            "Chopper2", "Spyder RS2", "Freewheeler 114", "Street Bob 107",
            "Chopper3", "Spyder RS3", "Freewheeler 114", "Street Bob 107",
            "Chopper4", "Spyder RS4", "Freewheeler 114", "Street Bob 107",
            "Chopper5", "Spyder RS5", "Freewheeler 114", "Street Bob 107")
        val trucks = arrayOf(
            "Freightliner C112", "Kenworth K200", "Isuzu F 2012", "Eurocargo ML 120",
            "Freightliner1 C112", "Kenworth K200", "Isuzu F 2012", "Eurocargo ML 120",
            "Freightliner2 C112", "Kenworth K200", "Isuzu F 2012", "Eurocargo ML 120",
            "Freightliner3 C112", "Kenworth K200", "Isuzu F 2012", "Eurocargo ML 120",
            "Freightliner4 C112", "Kenworth K200", "Isuzu F 2012", "Eurocargo ML 120",
            "Freightliner5 C112", "Kenworth K200", "Isuzu F 2012", "Eurocargo ML 120")

        myRecyclerView.adapter = VehiclesStickyHeadersRecyclerAdapter(this, arrayOf("Cars", "Motocycles", "Trucks"), arrayOf(cars, motorCycles, trucks), ::populateAString)

    }
}
