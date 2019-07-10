import scala.io.Source

object Cron {
  def getCurrentTime(int: Int): Unit = {
    print("Current Time : " + java.time.LocalTime.now().getHour + ":" + java.time.LocalTime.now().getMinute)
  }

  def displayConfig(): Unit = {
    println("Following is the content read:")
    //Source.fromFile("target/scala-2.13/classes/Config/scheduler config") .foreach({print})
  }

  def verifyTimeInputHour(hour: Int): Int = {
    println(s"The User Input The Hour $hour:")
    hour match{
      case x if x > 24 => {
        println("Hour Was Above 24")
        sys.exit()}
      case x if x < 0 => {
        println("Hour Was Below 0")
        sys.exit()}
      case _ => println(s"The Hour $hour Is Valid")
    }
    hour
  }

  def verifyTimeInputMinuite(minuites: Int): Int = {
    println(s"The User Input The Minuites $minuites")
    minuites match{
      case x if (x > 60) => {
        println("Minutes Was Above 60")
        sys.exit()}
      case x if (x < 0) => {
        println("Minutes Was Below 0")
        sys.exit()}
      case _ => println(s"The Minuites $minuites Is Valid")
    }
    minuites
  }

  def readFromConfig(hoursInput: Int , minuitesInput: Int): Unit = {

    var hoursInputChecked = Cron.verifyTimeInputHour(hoursInput)
    var minuitesInputChecked = Cron.verifyTimeInputMinuite(minuitesInput)

    println("Following is the content read:" )
    for(line <- Source.fromFile("target/scala-2.13/classes/Config/scheduler config").getLines()) {
      //println(line)

      val nextLine: Array[String] = line.split((" "))

      var timeMinuite: String = nextLine(0)
      var timeHour: String = nextLine(1)
      val folderStart: String = nextLine(2)

      if (timeHour == "*" || timeMinuite == "*") {
        if (timeHour == "*") {
          println("This Will Run Every Hour")
          timeHour = hoursInputChecked.toString
        }
        if (timeMinuite == "*") {
          println("This Will Run Every Minuite")
          if (minuitesInputChecked - 1 < -1){
            timeMinuite = (minuitesInputChecked - 1).toString
          }
          else {
            timeMinuite = "00"
          }

        }
      }
      else {
        if (timeHour.toInt == hoursInputChecked.toInt) {
          print("Starting Hour Was Found")
          if (timeMinuite.toInt == minuitesInputChecked.toInt) {
            print(" And Starting Minuites Found")
            println("")
          }
        }
        else {
          println("The Times Enter And The Start Time Were Different")
        }
      }
      val done : String = "tomorrow"
      if (hoursInputChecked > timeHour.toInt && minuitesInputChecked > timeMinuite.toInt) {
        println("THIS JOB HAS HAPPENED")
      }

      println(s" $timeHour : $timeMinuite   In Location $folderStart \n")
    }

  }
}
