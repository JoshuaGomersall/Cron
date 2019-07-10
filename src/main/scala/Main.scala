
  /*
  30 1 /bin/run_me_daily
  45 * /bin/run_me_hourly
  * * /bin/run_me_every_minute
  * 19 /bin/run_me_sixty_times
  */

  /*
  1:30 tomorrow ­ /bin/run_me_daily
  16:45 today ­ /bin/run_me_hourly
  16:10 today ­ /bin/run_me_every_minute
  19:00 today ­ /bin/run_me_sixty_times
  */

object Main {
  def main(args: Array[String]): Unit = {
    //Cron.getCurrentTime(10)

    Cron.readFromConfig(2,30)
  }
}
