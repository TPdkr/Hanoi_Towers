package org.example

object TowersService {
    var height = 0//the number of disks which we can change

    //list of 3 towers that we created
    private var towers = mutableListOf<Tower>()

    //this function initialises all the lists to default values
    init {//this is needed when we start the game
        //we get the height from the users input
        while (height <= 0) {
            println("Enter the difficulty(height)")
            val h = readlnOrNull() ?: continue//needed checks for valid input
            height = h.toIntOrNull() ?: continue
        }
        towers = mutableListOf<Tower>(Tower(height), Tower(height), Tower(height))
        //first tower
        towers[0].disks = (height downTo 1).toMutableList()
        towers[0].num = height
        //2 empty towers
        towers[1].disks = MutableList(height) { 0 }
        towers[1].num = 0
        towers[2].disks = MutableList(height) { 0 }
        towers[2].num = 0
        //towers.twrs.forEach{it.print()} -was used to check
    }

    //function for getting a safe input which can only be a number between 1 and 3
    fun safeInput(): Pair<Int, Int> {
        while (true) {
            println("Please enter 2 numbers each between 1 and 3, separated by a space")
            val str = readlnOrNull()
            if (str.isNullOrEmpty()) {//no input given
                println("emty stirng! Try again")
                continue
            }
            var ret = str.split(" ").map { it.toIntOrNull() ?: 0 }//we split into a list
            ret = ret.map { if (it < 1 || it > 3) 0 else it }//check range
            if (ret[0] != 0 && ret[1] != 0) {//exit condition
                return Pair<Int, Int>(ret[0] - 1, ret[1] - 1)
            } else {
                println("Invalid input!")
            }
        }
    }

    //funtion for executing exactly one turn
    fun turn() {
        output(towers)//we print the towers
        println("Choose the tower from which to get the disk")
        do {
            val inp = safeInput()
            val from = inp.first//index of tower
            val to = inp.second//destination
        } while (!towers[from].putTo(towers[to]))//we attempt move to a new tower
    }

    fun game() {
        //here we print the rules
        println("Rules:")
        println("Your goal is to get all the disk form tower 1 to tower 3.\nBe aware that a disk can only be placed on a larger disk")
        do {
            turn()//we have a turn
        } while (!towers[2].full())//did transfer all disks? check
        output(towers)//print the final tower state
        println("You won!")
    }
}