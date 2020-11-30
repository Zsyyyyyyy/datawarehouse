package com.lavideo.util

import java.io.InputStreamReader
import java.util.Properties

/**
 * 参数类
 */
object PropertiesUtil {
    def load(propertiesName: String) = {
        val prop = new Properties()
        prop.load(new InputStreamReader(
            Thread.currentThread().getContextClassLoader.getResourceAsStream(propertiesName)
        ))
        prop
    }

}
