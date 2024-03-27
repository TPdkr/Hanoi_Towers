package org.example

//class that stores one tower
class Tower(private val height: Int) {
    //val height=tall//number of disks
    var disks = mutableListOf<Int>()//list of radius of all disks
    var num: Int = 0//an index of last disk--!!!! need to get rid of it
    fun isEmptyTower() = (num == 0)//method for checking if empty

    //a method for checking if a tower is full
    fun full(): Boolean = (num == height)
    fun print() {//a function for printing raw data without visuals
        disks.forEach { print("$it ") }
        println("")

    }

    //method for checking if we can place a disk
    fun canPlace(disk: Int) = (!full()) && (isEmptyTower() || (disk < disks[num - 1]))

    //method for placing a disk, adding it to list
    fun placeDisk(disk: Int) {
        disks[num] = disk
        num++

    }

    //this method returns the value of last disk
    fun getDisk(): Int = if (!full()) {
        disks[num - 1]
    } else {
        0
    }

    //this method subtracts a disk from the list
    fun takeDisk(): Int {
        if (!isEmptyTower()) {//we need it to not be empty
            num--//move further away
            val disk = disks[num]
            disks[num] = 0
            return disk

        } else {
            return 0
        }
    }

    //I created a function for placing a disk to a different tower
    fun putTo(destination: Tower): Boolean {
        if (isEmptyTower()) {//no disks in tower
            println("The chosen tower is empty. No disks to take.")
            return false
        }
        if (this == destination) {//same tower
            println("Choose a different tower, from the one you are taking from")
            return false
        }
        if (!destination.canPlace(this.getDisk())) {//too big
            println("The disk is too large you can't place it")
            return false
        } else {
            println("Disk placed!")
            destination.placeDisk(this.takeDisk())//transfer
            return true
        }
    }
}
