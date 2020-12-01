package com.lavideo.app

import java.sql.{Connection, DriverManager, PreparedStatement}
import java.text.{DateFormat, SimpleDateFormat}
import java.util.Date
import com.alibaba.fastjson.{JSON, JSONObject}
import com.lavideo.bean.{OrderNum, OriginalOrderLog}
import com.lavideo.utils.MyKafkaUtil1
import org.apache.flink.configuration.{Configuration, RestOptions}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import com.lavideo.bean.OrderNum
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}

object AlertApp1 {
    var conn : Connection = _
    var insertStmt: PreparedStatement = _
    def main(args: Array[String]): Unit = {
        val conf = new Configuration()
//        conf.setInteger(RestOptions.PORT, 8081)
//        val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf)
        val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
        val stream: DataStream[String] = MyKafkaUtil1.getKafkaDStream(env, "log-lavideo-order")
        stream.print()
//        val value: DataStream[(OriginalOrderLog, Int)] = stream.map(log => {
//            val originalOrderLog: OriginalOrderLog = JSON.parseObject(log, classOf[OriginalOrderLog])
//            (originalOrderLog, 1)
//        }
//        )
//        val sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm")
//        val orderDStream: DataStream[OrderNum] = value
//          .keyBy(_._1.platform)
//          .timeWindow(Time.seconds(10), Time.seconds(10))
//          .sum(1)
//          .map(log => {
//              var apple_num = 0
//              var google_num = 0
//              if (log._1.platform.equals("apple")) {
//                  apple_num = log._2
//              } else {
//                  google_num = log._2
//              }
//              OrderNum(apple_num, google_num, sdf.format(new Date(System.currentTimeMillis())))
//          })
//        orderDStream.addSink(new MyJDBCSink())
//        orderDStream.print()
        //value.print()
        env.execute()
    }
//    class MyJDBCSink() extends RichSinkFunction[OrderNum] {
//        //var updateStmt: PreparedStatement = _
//        override def open(parameters: Configuration): Unit = {
//        }
//        conn = DriverManager.getConnection(
//            "jdbc:mysql://cluster102:3306/visual?useSSL=false","root","123456")
//        insertStmt = conn.prepareStatement(
//            "INSERT INTO visual.order_pay_num(apple,google,ts) VALUES(?,?,?)")
//
//        override def invoke(value: OrderNum, context: SinkFunction.Context[_]): Unit = {
//            insertStmt.setInt(1,value.apple_num)
//            insertStmt.setInt(2,value.google_num)
//            insertStmt.setString(3,value.ts)
//            insertStmt.execute()
//            //conn.commit()
//        }
//        override def close(): Unit = {
//            insertStmt.close()
//            conn.close()
//        }
//    }

}




