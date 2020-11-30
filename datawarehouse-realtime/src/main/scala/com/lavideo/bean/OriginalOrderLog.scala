package com.lavideo.bean


/*
{
    common:{ //公共模块
        logType:"order",
        appId:"201",
        mid:"1465467577",
        ts:1601230098765,
        platform:"google"
        vc:"1.0.1"
}
    msg:{
        uid:"132412",
        order_id:"13241255",
        order_item:"flower",
        order_amount:"12.9",
        coin_type:"doller",
        room_id:"10005",

    }
}
 */
case class OriginalOrderLog(
                           logType:String,
                           appId:String,
                           mid:String,
                           ts:String,
                           platform:String,
                           vc:String,
                           uid:String,
                           order_id:String,
                           orderItem:String,
                           orderAmount:String,
                           currency:String,
                           room_id:String
                           )
