package com.lavideo.bean

/*
            {
                "type":"order",
                "mid":"123",
                "uid":"321",
                "appID":"567",
                "total_amount":"23.99",
                "pay_channel":"google",
                "pay_way":"payTm",
                "logData":"2020-09-09",
                "logTime":"13:33:22",
                "ts":"12331231233"
             }
 */
case class PayLog(
                 `type`:String,
                 `mid`:String,
                 `uid`:String,
                 `appID`:String,
                 `total_amount`:String,
                 `pay_serial_num`:String,
                 `pay_channel`:String,
                 `pay_way`:String,
                 var `logData`:String,
                 var `logTime`:String,
                 var `ts`:String
                 )
