package com.grcanosa.utils

import java.time.LocalDate

import com.grcanosa.model.dtos.{Query, User}
import slick.jdbc.SQLiteProfile.api._
import slick.jdbc.meta.MTable
import slick.lifted.Tag

import scala.concurrent.Await
import scala.util.{Failure, Success, Try}
import concurrent.duration._

class DBController extends CommonConfigs {

  lazy val db = Database.forConfig("sqlitedb")


  def initDB(dbname : Option[String])= {
    val tables = Await.result(db.run(MTable.getTables), 1.seconds).toList
    log.info("Tables:"+tables.toString)
    log.info("Tables size:"+tables.size.toString)
    if(tables.size == 3){ //TODO: Mejorar esto.
      log.info("Table already created")
    } else {
      val schema = userTable.schema ++ queryTable.schema
      Try(Await.result(db.run(schema.create),30 seconds)) match {
        case Failure(e) => throw e
        case Success(_) => log.info("OK")
      }
    }
  }

  class UserTable(tag: Tag) extends Table[User](tag, "user"){

    def id = column[String]("id",O.PrimaryKey)

    def name = column[String]("name")

    def username = column[String]("username")

    def auth = column[Int]("auth")

    def * = (id, name.?, username.?, auth) <> (User.tupled, User.unapply)
  }

  lazy val userTable: TableQuery[UserTable] = TableQuery[UserTable]

  class QueryTable(tag: Tag) extends Table[Query](tag, "query"){

    def userId = column[String]("userId")

    def origin = column[String]("origin")

    def destination = column[String]("destination")

    import com.grcanosa.model.dtos.CustomColumns._

    def date = column[LocalDate]("date")

    def * = (userId, origin, destination, date) <> (Query.tupled, Query.unapply)
  }

  lazy val queryTable: TableQuery[QueryTable] = TableQuery[QueryTable]

}
