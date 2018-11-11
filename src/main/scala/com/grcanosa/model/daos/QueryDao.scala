package com.grcanosa.model.daos

import java.time.LocalDate

import slick.jdbc.SQLiteProfile.api._
import com.grcanosa.model.dtos.Query
import com.grcanosa.utils.DBController._

import scala.concurrent.Future

class QueryDao {

  def getAll:Future[Seq[Query]] = db.run(queryTable.result)

  def insert(query:Query): Future[Query] =
    db.run(queryTable returning queryTable += query)

  def remove(query:Query): Future[Int] =
    db.run(queryTable.filter{ q =>
      q.userId === query.userId && q.origin === query.origin &&
      q.destination === query.destination && q.date.asInstanceOf[LocalDate] == query.date
    }.delete)
}


