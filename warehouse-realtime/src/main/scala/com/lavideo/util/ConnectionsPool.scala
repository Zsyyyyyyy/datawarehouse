package com.lavideo.util

import java.sql.{Connection, DriverManager}
import java.util
import java.util.Properties

object ConnectionsPool {
    private val maxConnectionNum = 10 //最大连接数
    private val perConnectionNum = 10 //每次获取的连接数
    private var connectionNum = 0 // 连接数
    private val connections = new util.LinkedList[Connection]() //连接池
    private val properties: Properties = PropertiesUtil.load("config.properties")
    private val mysqlUrl: String = properties.getProperty("mysql.url")
    private val mysqlUser: String = properties.getProperty("mysql.user")
    private val mysqlPW: String = properties.getProperty("mysql.password")
    def getDriver() : Unit = {
        if (connectionNum < maxConnectionNum && connections.isEmpty) {
            Class.forName("com.jdbc.mysql.Driver")
        } else if (connectionNum >= maxConnectionNum && connections.isEmpty) {
            print("无连接可用")
            Thread.sleep(1000)
            getDriver()
        }
    }
    def getConn() : Connection = {
        if (connections.isEmpty) {
            getDriver()
            for(i <- 1 to perConnectionNum) {
                val connection: Connection = DriverManager.getConnection(mysqlUrl,mysqlUser,mysqlPW)
                connections.push(connection)
                connectionNum += 1

            }
        }
        connections.pop()
    }
    def returnConnection(connection: Connection) : Unit = {
        connections.push(connection)
    }




}
