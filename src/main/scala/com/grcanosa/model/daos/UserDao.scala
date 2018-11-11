package com.grcanosa.model.daos

import com.grcanosa.model.dtos.User
import com.grcanosa.utils.DBController._

import slick.jdbc.SQLiteProfile.api._

import scala.concurrent.Future


class UserDao {

  def getAll:Future[Seq[User]] = db.run(userTable.result)

  def insert(user:User): Future[Int] =
    db.run(userTable += user)

  def update(user:User): Future[Int] = db.run {
    userTable.filter(_.id === user.id).update(user)
  }

  def get(id:String): Future[User] = db.run(userTable.filter(_.id === id).result.head)
}


