package org.example
//class that stores one tower
class tower(tall:Int){
    val height=tall//number of disks
    var disks= mutableListOf<Int>()//list of radius of all disks
    var num:Int=tall//an index of last disk
    fun isempty()=(num==height)//method for checking if empty
    fun print(){//a function for printing raw data without visuals
        disks.forEach{print("$it ")}
        println("")

    }
    //method for checking if we can place a disk
    fun canplace(disk: Int)=(num>0)&&(num==height||(disk<disks[num]))
    //method for placing a disk, adding it to list
    fun placedisk(disk:Int){
        num--
        disks[num]=disk

    }
    //this method returns the value of last disk
    fun getdisk(): Int=if(disks.isNotEmpty()){disks[num]} else {0}
    //a method for checking if a tower is full
    fun full():Boolean=(num==0)
    //this method subtracts a disk from the list
    fun takedisk(): Int{
        if(disks.isNotEmpty()) {//we need it to not be empty
            val disk = disks[num]
            disks[num]=0
            num+=1//move further away
            return disk

        }else {return 0}
    }
}
//a class with 3 towers
object towers {
    val height=5//the number of disks which we can change
    //list of 3 towers that we created
    var twrs= mutableListOf<tower>(tower(height), tower(height), tower(height))
}