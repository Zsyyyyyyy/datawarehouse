package com.lavideo.utils

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011
import org.apache.flink.streaming.api.scala._

object MyKafkaUtil1 {
    private val prop = new Properties()
    //prop.setProperty("bootstrap.servers","cluster102:9092,cluster103:9092,cluster104:9092")
    prop.setProperty("bootstrap.servers","localhost:9092")

    prop.setProperty("group.id","order-num")
    prop.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    prop.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    prop.setProperty("auto.offset.reset", "latest")
    val topic = "log-lavideo-order"
    def getKafkaDStream(env : StreamExecutionEnvironment, topic : String) :DataStream[String] = {

        val value: DataStream[String] = env.addSource(
            new FlinkKafkaConsumer011[String](topic, new SimpleStringSchema(), prop)
        )
        value
    }


}
