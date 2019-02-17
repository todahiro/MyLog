package com.todahiro.mylog

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView

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
        // 1
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        // 2
        val searchMenuItem = menu.findItem(R.id.search_item)
        val searchView = searchMenuItem.actionView as SearchView
        // 3
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        // 4
        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(componentName))
        return true
    }

    override fun onResume() {
        super.onResume()

        // Realmインスタンスの取得
        realm = Realm.getDefaultInstance()

        // DBに登録しているログを一覧表示
        val results: RealmResults<LogDB> = realm.where(LogDB::class.java).findAll()

        // 表示形式の変更
        val log_list = ArrayList<String>()

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
