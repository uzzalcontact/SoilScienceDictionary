/*
 * Developed By Shudipto Trafder
 *  on 8/24/18 8:15 PM
 *  Copyright (c)2018  Shudipto Trafder.
 */

package com.iamsdt.pssd.ui.color

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import com.iamsdt.pssd.R
import com.iamsdt.pssd.ui.callback.ClickListener
import com.iamsdt.pssd.utils.Constants
import kotlinx.android.synthetic.main.activity_color.*
import kotlinx.android.synthetic.main.content_color.*

class ColorActivity : AppCompatActivity(), ClickListener {

    private val themes = ArrayList<MyTheme>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initialize theme
        ThemeUtils.initialize(this)

        setContentView(R.layout.activity_color)
        setSupportActionBar(toolbar)

        fillThemeIds()

        colorRcv.layoutManager = LinearLayoutManager(this)
        colorRcv.adapter = ColorAdapter(themes, this, this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * This method is for add new theme in arrayList
     * array list contain theme name and it's id
     */
    private fun fillThemeIds() {
        //fill array with styles ids
        val map = mapOf(
                Pair("Default", R.style.AppTheme_NoActionBar),
                Pair("Amber", R.style.amber_dark),
                Pair("Purple", R.style.purple_dark),
                Pair("Orange", R.style.orange),
                Pair("Cyan", R.style.cyan),
                Pair("Deep Orange", R.style.deeporange),
                Pair("Green", R.style.green)
        )

        for ((n, id) in map) {
            themes.add(MyTheme(n, id))
        }
    }

    override fun onItemClick(id: Int) {
        val themeCont = themes[id]

        val sp = getSharedPreferences(Constants.COLOR.colorSp, Context.MODE_PRIVATE)

        sp.edit {
            putInt(Constants.COLOR.themeKey, themeCont.id)
        }

        restartActivity()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun restartActivity() {
        val restartIntent = Intent(this@ColorActivity,
                ColorActivity::class.java)
        setResult(Activity.RESULT_OK)

        finish()

        startActivity(restartIntent)
        overridePendingTransition(0, 0)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ColorActivity::class.java)
        }
    }
}
