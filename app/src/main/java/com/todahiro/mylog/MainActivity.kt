package com.todahiro.mylog

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import io.realm.Realm
import io.realm.RealmResults

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { //view ->
            val intent = Intent(this@MainActivity, AddLogActivity::class.java)
            //intent.putExtra(getString(R.string.intent_key_status), getString(R.string.))
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        // Realmインスタンスの取得
        realm = Realm.getDefaultInstance()

        // DBに登録しているログを一覧表示
        val results: RealmResults<LogDB> = realm.where(LogDB::class.java).findAll()

        // 表示形式の変更
        val log_list = ArrayList<String>()
        //val length = results.size
        //for (i in 0..length - 1){
        //    log_list.add(results[i]!!.strLog)
        //}

        results.asReversed().forEach {
            log_list.add(it.strLog)
        }

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, log_list)
        listView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()

        realm.close()
    }
}
