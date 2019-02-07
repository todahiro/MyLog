package com.todahiro.mylog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_add_log.*

class AddLogActivity : AppCompatActivity() {

    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_log)

        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        // Realmインスタンスの取得
        realm = Realm.getDefaultInstance()
    }

    override fun onPause() {
        super.onPause()

        realm.close()
    }

    private fun addLog() {
        realm.beginTransaction()
        val logDB = realm.createObject(LogDB::class.java)
        logDB.strLog = addTextLog.text.toString()
        realm.commitTransaction()

        // テキストフィールドの初期化
        addTextLog.setText("")

        // 登録完了メッセージを表示(トースト)
        Toast.makeText(this@AddLogActivity, "新しいログを追加しました", Toast.LENGTH_SHORT)
    }
}
