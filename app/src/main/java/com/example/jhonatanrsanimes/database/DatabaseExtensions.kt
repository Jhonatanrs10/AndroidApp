package com.example.jhonatanrsanimes.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf
import com.example.jhonatanrsanimes.Anime

fun AnimesDatabase.insertAnime(item: Anime): Long {
    val idAnime = writableDatabase.insert("ANIMES", null, contentValuesOf().apply {
        //colocar valor
        put("NAME",item.name)
        put("STATUS",item.status)
        put("RELEASEE",item.release)
        put("S1",item.s1)
    })
    return idAnime
}

@SuppressLint("Range")
fun AnimesDatabase.selectAnime(): List<Anime> {

    val sql = "SELECT * FROM ANIMES"

    val cursor = readableDatabase.rawQuery(sql, null)

    val animeList = mutableListOf<Anime>()

    if (cursor.count > 0) {
        while (cursor.moveToNext()){
            val anime = Anime(
                id = cursor.getInt(cursor.getColumnIndex("ID")),
                name = cursor.getString(cursor.getColumnIndex("NAME")),
                status = cursor.getString(cursor.getColumnIndex("STATUS")),
                release = cursor.getString(cursor.getColumnIndex("RELEASEE")),
                s1 = cursor.getString(cursor.getColumnIndex("S1"))
            )
            animeList.add(anime)
        }

        cursor.close()
    }
    return animeList
}

fun AnimesDatabase.updateAnime(item: Anime): Int {

    val values = ContentValues().apply {
        put("NAME",item.name)
        put("STATUS",item.status)
        put("RELEASEE",item.release)
        put("S1",item.s1)
    }

    //update animes set name = .., status = .. WHERE ID = ...
    return writableDatabase.update("ANIMES", values, "ID=${item.id}", null)

}

