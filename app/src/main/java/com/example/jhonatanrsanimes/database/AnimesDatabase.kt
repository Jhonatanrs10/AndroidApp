package com.example.jhonatanrsanimes.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AnimesDatabase(context: Context):SQLiteOpenHelper(context, "animes.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
            CREATE TABLE ANIMES(
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                NAME TEXT,
                STATUS TEXT,
                RELEASEE TEXT,
                S1 TEXT
            );
        """.trimIndent()

        db?.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ANIMES")
        onCreate(db)
    }



}