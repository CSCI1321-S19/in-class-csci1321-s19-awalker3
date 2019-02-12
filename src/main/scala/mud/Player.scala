package mud
import scala.io.StdIn._
//import mud.Item

//import mud.Room

class Player() {
//  println("Enter username plz or forever hold your piece of life that is meaningless in this vast vast universe")
//  val username = readLine()
//  println("That's a weird username but it's fine I guess")
  

  println("You are starting in " + Room.rooms(Player.currentRoom).name + ".")
  println("Type 'look' to see what's around.")
  println("If you're ever confused, type 'help' to see what you can do.")
//  println(look(Player.currentRoom))
//  var Player.currentRoomInventory:List[Item] = List()
  val directions: Array[String] = Array("North", "South", "East", "West", "Up", "Down")
  
  def processCommand(command: String): Unit = {
    var input = command.trim.toLowerCase.split(" ")
    input(0) match {
      case "north" => move(0)
      case "south" => move(1)
      case "east" => move(2)
      case "west" => move(3)
      case "up" => move(4)
      case "down" => move(5)
      case "n" => move(0)
      case "s" => move(1)
      case "e" => move(2)
      case "w" => move(3)
      case "u" => move(4)
      case "d" => move(5)
      case "look" => look(Player.currentRoom)
      case "inv" => print(inv(Player.inventor))
      case "inventory" => print(Player.inventor)
      case "get" => {
        addToInventory(getItemType(input(1), Room.rooms(Player.currentRoom).roomInventory))
        Room.rooms(Player.currentRoom).getItem(input(1))
      }
      case "drop" => {
        getFromInventory(input(1))
//        Room.rooms(Player.currentRoom).dropItem(getItem1(input(1),Room.rooms(Player.currentRoom).roomInventory))
        Room.rooms(Player.currentRoom).dropItem(getItemType(input(1),Room.rooms(Player.currentRoom).roomInventory))
      }
      case "help" => printHelpCommands
      case _ => {
        println("Nope. Try again.")
        processCommand(readLine())
      }
    }
  }
  
  def getItemIndex(itemName:String, inventory:List[Item]): Int = {
   val itemIndex = inventory.indexWhere(item => item.name.toLowerCase == itemName.trim.toLowerCase)
   itemIndex
  }
  
  def getItemType(itemName:String, inventory:List[Item]): Item = {
   val itemIndex = inventory.indexWhere(item => item.name.toLowerCase == itemName.trim.toLowerCase)
//   println(inventory)
   if (itemIndex > -1) {
     val itemItself = inventory(itemIndex)
     itemItself
   } else {
     Item("None", "Nothing")
   }
  } 
  
  def getFromInventory(itemName: String): Option[Item] = {
   //take item out of inventory
   val itemIndex = Player.inventor.indexWhere(item => item.name == itemName)
   if (itemIndex > -1) {
     val itemItself = Player.inventor.find(item => item.name == itemName)
     if((itemItself) == None) {
       println("You don't have that item to get rid of. Don't you know what you have?")
       //processCommand(readLine)
       None
     } else {
       Player.inventor = Player.inventor.filter(a => a != Player.inventor(itemIndex))
       println("Ok, just throw it on the ground. Classic.")
       None
     }
   } else {
     println("That's not even a real item what the hell are you doing?")
     None
   }   
  }
  
  def addToInventory(item: Item): Unit = {
    if (getItemIndex(item.name, Room.rooms(Player.currentRoom).roomInventory) > -1){
    Player.inventor = (Room.rooms(Player.currentRoom).roomInventory(getItemIndex(item.name, Room.rooms(Player.currentRoom).roomInventory))) :: Player.inventor
//    Room.rooms(Player.currentRoom).roomInventory = item :: inventor
//    println(item.name + " added to inventory")
    println(item.desc)
    } else {
      println("That's not even a real item what the hell are you doing?")
    }
  }
  
  def move(dir: Int): Unit = {
    if(Room.rooms(Player.currentRoom).exits(dir) != -1) {
      Player.currentRoom = Room.rooms(Player.currentRoom).exits(dir)
      look(Player.currentRoom)
    }
    else println("Invalid direction try again.")
  }
  
  def look(myRoom:Int): Unit = {
    println((Room.rooms(Player.currentRoom).name))
    println((Room.rooms(Player.currentRoom).desc))
    print("Exits: ")
    
    for (i <- 0 until 5) {
      if (Room.rooms(Player.currentRoom).exits(i) != -1) {
        print(directions(i) + " to " + Room.rooms(Room.rooms(Player.currentRoom).exits(i)).name)
      }
      if ((Room.rooms(Player.currentRoom).exits(i+1) != -1)) {
        print(", ")
      } 
    }
    println("")
    print("Items: ")
    if (Room.rooms(Player.currentRoom).roomInventory.isEmpty == false) {
      for (i <- 0 until (Room.rooms(Player.currentRoom).roomInventory.length)-1) print(Room.rooms(Player.currentRoom).roomInventory(i).name + ", ")
      for (i <- (Room.rooms(Player.currentRoom).roomInventory.length)-1 until (Room.rooms(Player.currentRoom).roomInventory.length)) print(Room.rooms(Player.currentRoom).roomInventory(i).name)
      //if (Room.rooms(Player.currentRoom).roomInventory == null) print("None")
    }
    println("")
  }
  
  def inv(a: List[Item]):List[String] = {
    if (a.length <1) println("Inventory is empty, go get something.")
    else {
      print("Your inventory: ")
      for (i <- 0 until a.length-1){
        Player.invNames = a(i).name :: Player.invNames
        //print(Player.invNames(i) + ", ")
      }
      for (j <- a.length-1 until a.length){
        Player.invNames = a(j).name :: Player.invNames
       // println(Player.invNames(j))
      }
    }
    Player.invNames
  }
  
  def printHelpCommands(): Unit = {
    println("Type 'north' or 'n' to move north")
    println("Type 'south' or 's' to move south")
    println("Type 'east' or 'e' to move east")
    println("Type 'west' or 'w' to move west")
    println("Type 'up' or 'u' to move up")
    println("Type 'down' or 'd' to move down")
    println("Type 'look' to see the current room")
    println("Type 'inventory' or 'inv' to print the current inventory")
    println("Type 'get' and one of the items in the room to put the item into your inventory")
    println("Type 'drop' and one of the items in your inventory to remove it from your inventory and put it in the room.")
    println("Type 'exit' to exit the game.")
  }
}

object Player {
  var inventor:List[Item] = List()
  var invNames:List[String] = List()
  var currentRoom:Int = 2
}