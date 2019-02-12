package mud

//import mud.Item

class Room(
    val name: String,
    val desc: String,
    private var items: List[Item],
    val exits: Array[Int]
) {
  
  //var _exits = exits.toList
  var roomInventory:List[Item] = items:List[Item]
  def description(): String = {
    name
  }
  
  def getExit(dir: Int): Option[Room] = {
    if(exits(dir) == -1) None
    else Some(Room.rooms(exits(dir)))
  }

  def getItem(itemName: String): Option[Item] = {
    // take item out of room
    val itemIndex = roomInventory.indexWhere(item => item.name == itemName)
    if (itemIndex > -1) {
      val itemItself = roomInventory.find(item => item.name == itemName)
      if(itemItself == None) {
        println("You don't have that item to get rid of. Don't you know what you have?")
        itemItself
      } else {
        roomInventory = roomInventory.filterNot(a => a == roomInventory(itemIndex))
        itemItself //None
      }
    } else {
     None
   } 
  } 
  
  /*def getItem1(itemName:String): Option[Item] = {
    val itemItself = roomInventory.find(item => item.name == itemName)
    if (itemItself != None) {
    //if(roomInventory(mud.Player.currentRoom).name.contains(itemName) == true) {
    roomInventory = roomInventory.filterNot(a => (a == roomInventory(roomInventory.indexWhere(item => item.name == itemName))))
    }
    else {
      println("That item does not exist in this plane of reality, either get something else or look harder.")
      None
    }
    itemItself
  }*/

  def dropItem(item: Item): Unit = {
    items = item :: items 
  }


}

object Room {
  val rooms = readRooms()
  
  def readRooms(): Array[Room] = {
    val source = scala.io.Source.fromFile("babymap.txt")
    val lines = source.getLines()
    Array.fill(lines.next.trim.toInt)(readRoom(lines))
  }
  
  def readRoom(lines: Iterator[String]): Room = {
    val name = lines.next
    val desc = lines.next
    val items = List.fill(lines.next.trim.toInt) {
      Item(lines.next,lines.next)
    }
    val exits = lines.next.split(",").map(_.trim.toInt)
    new Room(name, desc, items, exits)
  }
}