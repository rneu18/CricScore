package com.example.cricscore

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    val allData: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("Select PLAYER_NAME from $BATTING_1", arrayOf())

        }

    override fun onCreate(db: SQLiteDatabase) {


        val createQuery1 = "CREATE TABLE " +
                BATTING_1 +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLAYER_NAME + " VARCHAR(255), " +
                POSITION + " INTEGER, " +
                RUNS + " INTEGER, " +
                BALLS_FACED + " INTEGER, " +
                SIXES + " INTEGER, " +
                FOURS + " INTEGER, " +
                BATTED + " BOOLEAN, " +
                DISMISSAL + " VARCHAR(255))"
        val createQuery2 = "CREATE TABLE " +
                FEILDING_1 +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLAYER_NAME + " VARCHAR(255), " +
                OVERS + " INTEGER, " +
                RUNS_GIVEN + " INTEGER, " +
                EXTRAS + " INTEGER, " +
                SIXES + " INTEGER, " +
                FOURS + " INTEGER, " +
                MADEINS + " INTEGER, " +
                WICKETS + " INTEGER)"
        val createQuery3 = "CREATE TABLE " +
                BATTING_2 +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLAYER_NAME + " VARCHAR(255), " +
                POSITION + " INTEGER, " +
                RUNS + " INTEGER, " +
                BALLS_FACED + " INTEGER, " +
                SIXES + " INTEGER, " +
                FOURS + " INTEGER, " +
                BATTED + " BOOLEAN, " +
                DISMISSAL + " VARCHAR(255))"
        val createQuery4 = "CREATE TABLE " +
                FEILDING_2 +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLAYER_NAME + " VARCHAR(255), " +
                OVERS + " INTEGER, " +
                RUNS_GIVEN + " INTEGER, " +
                EXTRAS + " INTEGER, " +
                SIXES + " INTEGER, " +
                FOURS + " INTEGER, " +
                MADEINS + " INTEGER, " +
                WICKETS + " INTEGER)"


        db.execSQL(createQuery1)
        db.execSQL(createQuery2)
        db.execSQL(createQuery3)
        db.execSQL(createQuery4)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        onCreate(db)

    }


    fun insertBatting1(player_name: String) {
        println("11111111111111111111111111111111111111111111111 $player_name")
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(PLAYER_NAME, player_name)
        db.insert(BATTING_1, null, contentValues)
    }

    fun insertBatting2(player_name: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(PLAYER_NAME, player_name)
        db.insert(BATTING_2, null, contentValues)
    }


    fun clearTables() {
        val db = this.writableDatabase
        db.execSQL("delete from $BATTING_1")
        db.execSQL("delete from $FEILDING_1")
        db.execSQL("delete from $BATTING_2")
        db.execSQL("delete from $FEILDING_2")
    }

    companion object {
        const val DATABASE_NAME = "score.db"
        const val BATTING_1 = "first_inning_bat"
        private val ID = "ID"
        const val PLAYER_NAME = "Player_name"
        const val POSITION = "Position"
        const val RUNS = "Runs"
        const val BALLS_FACED = "ball_faced"
        const val SIXES = "Sixes"
        const val FOURS = "Fours"
        const val BATTED = "Batted"
        const val DISMISSAL = "Dismissal"
        const val OVERS = "Overs"
        const val WICKETS = "Wickets"
        const val MADEINS = "MADEINS"
        const val RUNS_GIVEN = "Runs_given"
        const val EXTRAS = "Extras"
        const val FEILDING_1 = "first_inning_field"
        const val BATTING_2 = "second_inning_bat"
        const val FEILDING_2 = "second_inning_field"
    }
}
