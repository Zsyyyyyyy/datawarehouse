package com.lavideo.util

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

/**
 * 连接kafka工具类
 */
object MyKafkaUtil {
    //创建配置信息对象
    private val properties: Properties = PropertiesUtil.load("config.properties")
    //初始化连接到集群的地址
    private val broker_List: String = properties.getProperty("kafka.broker.list")

    //kafka消费者配置
    private val kafkaPara: Map[String, Object] = Map(
        "bootstrap.servers" -> broker_List,
        "key.deserializer" -> classOf[StringDeserializer],
        "value.deserializer" -> classOf[StringDeserializer],
        "group.id" -> "lavkafka",
        "auto.offset.reset" -> "latest",
        "enable.auto.commit" -> (true: java.lang.Boolean)
    )

    //创建DStream kafka数据流返回数据
    def getKafkaStream(ssc : StreamingContext,topic : Set[String]) : InputDStream[ConsumerRecord[String,String]] = {
        val dStream : InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String,String](
            ssc,
            LocationStrategies.PreferConsistent,
            ConsumerStrategies.Subscribe[String, String](topic, kafkaPara)
        )
        dStream
    }
}

