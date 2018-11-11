package com.grcanosa.model.dtos

import java.sql.Timestamp
import java.time.LocalDate
import slick.jdbc.SQLiteProfile.api._


object CustomColumns {

  implicit val DateTimeTimestampType =
    MappedColumnType.base[LocalDate, Timestamp](
      ld => Timestamp.valueOf(ld.atStartOfDay),
      ts => ts.toLocalDateTime.toLocalDate
    )
}