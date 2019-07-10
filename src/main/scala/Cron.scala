import scala.io.Source

object Cron {
  def getCurrentTime(int: Int): Unit = {print("Current Time : " + java.time.LocalTime.now().getHour + ":" + java.time.LocalTime.now().getMinute)}

  def displayConfig(): Unit = {
    println("Following is the content read: \n")
    Source.fromFile("target/scala-2.13/classes/Config/scheduler config") .foreach({print})
  }

  def verifyTimeInputHour(hour: Int): Int = {
    println(s"The User Input The Hour $hour:")
    hour match{
      case x if x > 24 =>
        println("Hour Was Above 24")
        sys.exit()
      case x if x < 0 =>
        println("Hour Was Below 0")
        sys.exit()
      case _ => println(s"The Hour $hour Is Valid")
    }
    hour
  }

  def verifyTimeInputMinuite(minutes: Int): Int = {
    println(s"The User Input The Minutes $minutes")
    minutes match{
      case x if (x > 60) => {
        println("Minutes Was Above 60")
        sys.exit()}
      case x if (x < 0) => {
        println("Minutes Was Below 0")
        sys.exit()}
      case _ => println(s"The Minuites $minutes Is Valid")
    }
    minutes
  }

  def readFromConfig(hoursInput: Int , minuitesInput: Int): Unit = {

    val hoursInputChecked = Cron.verifyTimeInputHour(hoursInput)
    val minuitesInputChecked = Cron.verifyTimeInputMinuite(minuitesInput)

    println("Following is the content read:")
    for (line <- Source.fromFile("target/scala-2.13/classes/Config/scheduler config").getLines()) {

      val nextLine: Array[String] = line.split((" "))

      var timeMinutes: String = nextLine(0)
      var timeHour: String = nextLine(1)
      val folderStart: String = nextLine(2)

      if (timeHour == "*" || timeMinutes == "*") {
        if (timeHour == "*") {
          println("This Will Run Every Hour")
          timeHour = hoursInputChecked.toString
        }
        if (timeMinutes == "*") {
          println("This Will Run Every Minuite")
          if (minuitesInputChecked - 1 < -1) {
            println(minuitesInputChecked -1)
            timeMinutes = (minuitesInputChecked - 1).toString
          }
          else {
            timeMinutes = "00"
          }
        }
      }
      else {
        if (timeHour.toInt == hoursInputChecked.toInt) {
          println("Starting Hour Was Found")
          if (timeMinutes.toInt == minuitesInputChecked.toInt) {
            print(" And Starting Minutes Found")
            println("")
          }
        }
        else {
          println("The Times Enter And The Start Time Were Different")
        }
      }

      if (hoursInputChecked == timeHour.toInt) {
        println("This Job Will Run Shortly")
        if (minuitesInputChecked > timeMinutes.toInt) {
          println("THIS JOB HAS HAPPENED")
        }
      }
      else if (hoursInputChecked > timeHour.toInt) {
        println("THIS JOB HAS HAPPENED")
      }
      else {
        println("THIS JOB WILL Happen AT")
      }
      println(s" $timeHour : $timeMinutes   In Location $folderStart \n")
    }
  }
}
