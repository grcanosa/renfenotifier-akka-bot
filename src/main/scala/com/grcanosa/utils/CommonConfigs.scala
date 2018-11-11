package com.grcanosa.utils

import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory


trait CommonConfigs {

  lazy val config = {
    ConfigFactory.load("application.conf")
  }


  val log = LoggerFactory.getLogger(config.getString("logger.name"))
}
