package com.todahiro.mylog

import io.realm.RealmObject

// モデルクラス
open class LogDB : RealmObject() {
    var strLog: String = ""
}