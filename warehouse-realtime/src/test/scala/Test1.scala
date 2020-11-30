import com.alibaba.fastjson.JSON
import javax.xml.ws.spi.http.HttpContext
import org.apache.http.{Consts, HttpHeaders}
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils

import scala.collection.mutable


object Test1 {
    def main(args: Array[String]): Unit = {
        val url = "https://open.feishu.cn/open-apis/bot/v2/hook/6191a2c1-44a2-443a-9170-0931ae2e2217"
        val httpClient: CloseableHttpClient = HttpClients.createDefault()
        val httpPost = new HttpPost(url)
        httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        val entity = new StringEntity("{\"msg_type\":\"text\",\"content\":{\"text\": \"测试\"}}",Consts.UTF_8.toString)
        httpPost.setEntity(entity)

        val response = httpClient.execute(httpPost)
        val result = EntityUtils.toString(response.getEntity,"UTF-8")

        print(result)
    }


}
