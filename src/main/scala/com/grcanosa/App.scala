package com.grcanosa

import com.grcanosa.model.daos.UserDao
import com.grcanosa.model.dtos.User
import com.grcanosa.utils.{CommonConfigs, DBController}

import scala.concurrent.Await

import concurrent.duration._

/**
 * @author ${user.name}
 */
object App extends CommonConfigs{
  

  def main(args : Array[String]) {
    DBController.initDB
    log.info("Database initialized")
    val userDao = new UserDao
    val user = new User(1.toString,Some("pepe"),Some("pepe"),0)
    Await.result(userDao.insert(user), 10 seconds)
  }

}
