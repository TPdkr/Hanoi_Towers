package org.example

//this function transfers a disk value to a string
fun disktoline(disk: Int, maxwidth: Int): String {
    val newline = StringBuilder()
    newline.append(" ")//border
    if (disk > 0) {
        //we have a disk
        newline.append(" ".repeat(maxwidth - disk))
        newline.append("█".repeat(2 * disk + 1))
        newline.append(" ".repeat(maxwidth - disk))
    } else {
        //we have no disk value 0
        newline.append(" ".repeat(maxwidth))
        newline.append("|")
        newline.append(" ".repeat(maxwidth))
    }
    newline.append(" ")//border
    return newline.toString()
}

//this function creates a string with tower bases
fun base(disk: Int): String {
    val newline = StringBuilder()
    newline.append(" ")//border
    for (i in 0..2) {
        newline.append("_".repeat(disk))
        newline.append("┴")//center
        newline.append("_".repeat(disk))
        newline.append("  ")
    }
    return newline.toString()
}

//this function creates a string with indexes
fun labels(disk: Int): String {
    val newline = StringBuilder()
    newline.append("^".repeat(disk))//first tower
    newline.append("[1]")
    newline.append("^".repeat(disk))
    for (i in 1..2) {//the other 2 towers
        newline.append(" ".repeat(disk))
        newline.append("[${i + 1}]")
        newline.append(" ".repeat(disk))
    }
    return newline.toString()
}

fun output(towers: MutableList<Tower>) {
    val sidemaxwidth = towers[0].disks.size//how far from the tower center can we go
    for (i in sidemaxwidth - 1 downTo 0) {//for each layer
        val newline = StringBuilder()
        for (j in 0 until 3) {
            newline.append(disktoline(towers[j].disks[i], sidemaxwidth))
        }
        println(newline.toString())
    }
    println(base(sidemaxwidth))//base
    println(labels(sidemaxwidth))//labels

}