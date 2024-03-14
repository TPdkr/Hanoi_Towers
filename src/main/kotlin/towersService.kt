package org.example

class TowersService{
    //this function initialises all the lists to default values
    fun start(){
        val h=towers.height
        //first tower
        towers.twrs[0].disks= (1..h).toMutableList()
        towers.twrs[0].num=0
        //2 empty towers
        towers.twrs[1].disks= MutableList(h){0}
        towers.twrs[1].num=h
        towers.twrs[2].disks= MutableList(h){0}
        towers.twrs[2].num=h
        //towers.twrs.forEach{it.print()} -was used to check

    }
    //function for getting a safe input which can only be a number between 1 and 3
    fun safeinput(): Int{
        var ret=0//return value
        do{
            println("Please enter a number between 1 and 3")
            val str= readlnOrNull()
            if (str.isNullOrEmpty()){//ni input given
                println("emty stirng! Try again")
                continue
            } else if(str.any { !it.isDigit() }){//not +int or some text
                println("not evry character is a digit!")
                continue
            } else {
                ret = str.toInt()
                if(ret<1 || ret> 3){//integer out of range
                    println("out of range!")
                    ret=0
                    continue
                }
            }
        } while(ret==0)
        return ret
    }
    //funtion for executing exactly one turn
    fun turn(){
        var done=true//is the turn done? condition for loop
        output()//we print the towers
        println("Choose the tower from which to get the disk")
        do{
            val from=safeinput()-1//index of tower
            if(towers.twrs[from].isempty()) {
                //no disks in tower
                println("The chosen tower is empty. No disks to take.")
                continue
            }
            if(towers.twrs.all{!it.canplace(towers.twrs[from].getdisk())}){
                //can't place the disk anywhere
                println("This disk can't be placed anywhere")
                continue
            }
            //get destination
            println("Choose the tower where to place the disk")
            val to=safeinput()-1
            if(to==from){
                println("Choose a different tower, from the one you are taking from")
                continue
            }
            if(!towers.twrs[to].canplace(towers.twrs[from].getdisk())){
                //to big
                println("The disk is too large you can't place it")
                continue
            } else{
                println("Disk placed!")
                done=false
                towers.twrs[to].placedisk(towers.twrs[from].takedisk())//transfer
            }
        } while(done)
    }
    fun play(){
        start()//we initialize
        //here we print the rules
        println("Rules:")
        println("Your goal is to get all the disk form tower 1 to tower 3.\nBe aware that a disk can only be placed on a larger disk")
        do{
            turn()//we have a turn
        } while(!towers.twrs[2].full())//did transfer all disks? check
        output()//print the final tower state
        println("You won!")
    }
}