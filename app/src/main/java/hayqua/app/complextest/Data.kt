package hayqua.app.complextest

import java.util.*

data class Person(val id: Int, val name: String, var age: Int, var gender: String, var height: Int)

val rand = Random()

val headers = arrayOf("Sydney Siders", "Melbourians", "Bananabenders", "Crow Eaters", "Sandgropers")

var id = 1
val SydneySiders = arrayOf(
    Person(id++, "Aran Applegale", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Dennis Odman", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Kevin Arter", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Actus Paree", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Rick Sanchez", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Matt Mortha", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Mojo Genns", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Rob Ainstand", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Penor Dar", rand.nextInt(100), "Male", rand.nextInt(170)+ 50)
)

val Melbourians = arrayOf(
    Person(id++, "Rogu Rangatha", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Larsic Croft", rand.nextInt(100), "Female", rand.nextInt(170)+ 50),
    Person(id++, "Inna Rerfin", rand.nextInt(100), "Female", rand.nextInt(170)+ 50),
    Person(id++, "Erlo Close", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Esper Fuller", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Marg Rue", rand.nextInt(100), "Female", rand.nextInt(170)+ 50)
)

val Bananabenders = arrayOf(
    Person(id++, "Jen Dormer", rand.nextInt(100), "Female", rand.nextInt(170)+ 50),
    Person(id++, "Will Grave", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Pantha Sieka", rand.nextInt(100), "Female", rand.nextInt(170)+ 50),
    Person(id++, "Cindy Colbert", rand.nextInt(100), "Female", rand.nextInt(170)+ 50),
    Person(id++, "Stephen Landers", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Blade Kings", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Dan Downers", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Phillip Gassia", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Sean Evans", rand.nextInt(100), "Male", rand.nextInt(170)+ 50)
)

val CrowEaters = arrayOf(
    Person(id++, "Orbi Kashi", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Steve Reins", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Peter Swanson", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Ron Parkers", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Trevor Moah", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Bradly Jones", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Adam Markers", rand.nextInt(100), "Male", rand.nextInt(170)+ 50)
)

val Sandgropers = arrayOf(
    Person(id++, "Zoe Pitt", rand.nextInt(100), "Female", rand.nextInt(170)+ 50),
    Person(id++, "Bill Morrison", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Grabriel Iker", rand.nextInt(100), "Male", rand.nextInt(170)+ 50),
    Person(id++, "Karen Gills", rand.nextInt(100), "Female", rand.nextInt(170)+ 50),
    Person(id++, "Harry Ford", rand.nextInt(100), "Male", rand.nextInt(170)+ 50)
)