package com.lavideo.app

import java.sql.{Connection, Statement}
import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.JSON
import com.lavideo.bean.PayLog
import org.apache.commons.lang.StringEscapeUtils
import com.lavideo.util.{ConnectionsPool, MyKafkaUtil}
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.kafka.clients.consumer.ConsumerRecord
import com.lavideo.LogConstants.ORDER_APPLE
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import org.roaringbitmap.IntIterator

import scala.util.control.Breaks._

object OrderAlertApp {
    def main(args: Array[String]): Unit = {
        //创建sparkStreaming
        val sparkConf: SparkConf = new SparkConf().setAppName("alertApp").setMaster("local[*]").set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        val ssc = new StreamingContext(sparkConf,Seconds(5))

        //判断日志类型

        //读取Kafka Event 主题数据创建流
        val kafkaDStream: InputDStream[ConsumerRecord[String, String]] =
        MyKafkaUtil.getKafkaStream(ssc, Set("log-vivalive-xjp"))
//        MyKafkaUtil.getKafkaStream(ssc, Set("log-lavideo-order"))
        kafkaDStream.map(record=>record.value).print()

        //处理数据
//        val sdf1 = new SimpleDateFormat("yyyy-MM-dd HH")
//        val sdf2 = new SimpleDateFormat("yyyy-MM-dd-HH:mm")
//
//        val CaseDStream: DStream[PayLog] = kafkaDStream.map((record: ConsumerRecord[String, String]) => {
//            //创建样例类对象
//            val casePayLog: PayLog = JSON.parseObject(record.value(),classOf[PayLog])
//            val timeStamp: Long = casePayLog.`ts`.toLong
//            val dateHour: String = sdf1.format(new Date(timeStamp))
//            val dateHourArr: Array[String] = dateHour.split(" ")
//            casePayLog.`logData` = dateHourArr(0)
//            casePayLog.`logTime` = dateHourArr(1)
//            casePayLog
//        })
//        //打印测试
//        CaseDStream.cache()
//        var sql = ""
//        CaseDStream.window(Minutes(10))
//        CaseDStream.foreachRDD(rdd=>{
//            rdd.foreachPartition(log=>{
//                val connection: Connection = ConnectionsPool.getConn()
//                val statement: Statement = connection.createStatement()
//                log.foreach(log=>{
//                    sql = "insert into Log(logType,ts) values('" + log.`type` + "'," + log.`ts` + ")"
//                    statement.execute(sql)
//                })
//                ConnectionsPool.returnConnection(connection)
//            })
//        })
        ssc.start()
        ssc.awaitTermination()
    }
}
