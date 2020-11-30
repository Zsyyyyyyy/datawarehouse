package com.lavideo.bean


case class OrderLog(
                  `type`:String,
                  mid:String,
                  uid:String,
                  appId:String,
                  totalAmount:String,
                  payChannel:String,
                  logData:String,
                  logTime:String,
                  ts:String
                  )
