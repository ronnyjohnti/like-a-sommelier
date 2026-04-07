package dev.ronnyjohnti.likeasommelier.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.ronnyjohnti.likeasommelier.data.model.Beer

class BeerDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "beers.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE beers (id INTEGER PRIMARY KEY, brand TEXT, type TEXT, alcohol TEXT, rating TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun saveBeer(beer: Beer) {
        writableDatabase.insert("beers", null, ContentValues().apply {
            put("brand", beer.brand)
            put("type", beer.type)
            put("alcohol", beer.alcohol)
            put("rating", beer.rating)
        })
    }

    fun getBeers(): List<Beer> {
        val beers = mutableListOf<Beer>()
        val cursor: Cursor = readableDatabase
            .rawQuery("SELECT id, brand, type, alcohol, rating FROM beers", null)
        while (cursor.moveToNext()) {
            beers.add(
                Beer(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4).toFloat(),
                    cursor.getInt(0),
                )
            )
        }
        cursor.close()
        return beers
    }

    fun deleteBeer(id: Int) {
        writableDatabase.delete("beers", "id = :id", arrayOf(id.toString()))
    }
}
