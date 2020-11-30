package com.lavideo.bean

case class OrderNumLog (
                         statistics_time:String,
                         appid:String,
                         appleStoreNum:Int,
                         googleStoreNum:Array[Int]
)
