package com.todahiro.mylog

import io.realm.RealmObject
import java.util.*

// モデルクラス
open class LogDB : RealmObject() {
    var dateLog: Date = Date()
    var strLog: String = ""
}