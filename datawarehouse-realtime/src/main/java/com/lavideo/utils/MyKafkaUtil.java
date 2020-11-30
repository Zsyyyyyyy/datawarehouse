package com.lavideo.utils;


/**
 *
 */

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;


import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.Properties;

public class MyKafkaUtil{

    public static DataStream<String> getKafkaStream(StreamExecutionEnvironment env, String topic, String bootstrapServers, String groupId) {
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers",bootstrapServers);
        prop.setProperty("group.id",groupId);
        prop.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.setProperty("auto.offset.reset", "latest");

        DataStreamSource<String> kafkaStream = env.addSource(
                new FlinkKafkaConsumer011<String>(topic, new SimpleStringSchema(), prop)
        );
        return kafkaStream;

    }


}
