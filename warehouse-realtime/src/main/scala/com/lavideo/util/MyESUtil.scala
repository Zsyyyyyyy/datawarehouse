package com.lavideo.util

import java.util
import java.util.{Objects, Properties}

import io.searchbox.client.config.HttpClientConfig
import io.searchbox.client.{JestClient, JestClientFactory}
import io.searchbox.core.{Bulk, BulkResult, Index}

import collection.JavaConversions._


// ES工具类
object MyESUtil {
    /**
     * 批量导入ES
     * @param str  索引
     * @param docList  文档
     */
    def insertBulk(str: String, docList: List[(String, Any)]): Unit = {
        if (docList.nonEmpty) {
            //获取客户端
            val client: JestClient = getClient
            //默认索引str，默认类型_doc
            val bulkBuilder: Bulk.Builder = new Bulk.Builder().defaultIndex(str).defaultType("_doc")
            //构建插入ES对象
            //遍历docList，有索引的使用id为索引，没有索引的新建索引
            for ((id, doc) <- docList) {

                val indexBuilder = new Index.Builder(doc)
                if (id != null) {
                    indexBuilder.id(id)
                }
                //建索引
                val index: Index = indexBuilder.build()
                bulkBuilder.addAction(index)
            }
            //建批量对象
            val bulk: Bulk = bulkBuilder.build()

            var items: util.List[BulkResult#BulkResultItem] = null
            try {
                items = client.execute(bulk).getItems
            } catch {
                case ex: Exception => println(ex.toString)
            } finally {
                close(client)
                println("保存" + items.size() + "条数据")
                for(item <- items) {
                    if(item.error != null && item.error.nonEmpty) {
                        println(item.error)
                        println(item.errorReason)
                    }
                }
            }

        }
    }


    private var factory: JestClientFactory = null
    val properties: Properties = PropertiesUtil.load("config.properties")
    val esHost: String = properties.getProperty("es.host")
    val esPort: String = properties.getProperty("es.port")
    //获取Jest客户端
    def getClient : JestClient = {
        if (factory == null) {
            build()
        }
        factory.getObject
    }
    //新建Jest客户端
    private def build() = {
        factory = new JestClientFactory
        factory.setHttpClientConfig(new HttpClientConfig.Builder("http://" + esHost + ":" + esPort)
          .multiThreaded(true)
          .maxTotalConnection(200)
          .connTimeout(10000)
          .build())
    }
    //关闭客户端
    def close(client: JestClient) = {
        if (!Objects.isNull(client)) {
            try {
                client.shutdownClient()
            } catch {
                case e: Exception =>
                    e.printStackTrace()
            }
        }
    }

}
