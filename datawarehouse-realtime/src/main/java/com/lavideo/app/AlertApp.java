package com.lavideo.app;

import com.lavideo.utils.MyKafkaUtil;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;


public class AlertApp {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.setInteger(RestOptions.PORT, 8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        String topic = "log-lavideo-order";
        String bootstrapServers = "localhost:9092";
        String groupId = "orderNum";
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        DataStream<String> kafkaStream = MyKafkaUtil.getKafkaStream(env, topic, bootstrapServers, groupId);
        KeyedStream<String, Tuple> stringTupleKeyedStream = kafkaStream.keyBy(0);
        stringTupleKeyedStream.flatMap
        env.execute();
    }
}
