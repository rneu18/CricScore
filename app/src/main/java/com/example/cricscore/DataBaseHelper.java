package com.example.cricscore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public  static  final  String DATABASE_NAME = "score.db";
    public  static  final  String BATTING_1 = "first_inning_bat";
    private static String ID = "ID";
    public  static  final  String PLAYER_NAME = "Player_name";
    public  static  final  String POSITION = "Position";
    public  static  final  String RUNS = "Runs";
    public  static  final  String BALLS_FACED = "ball_faced";
    public  static  final  String SIXES = "Sixes";
    public  static  final  String FOURS = "Fours";
    public  static  final  String BATTED = "Batted";
    public  static  final  String DISMISSAL = "Dismissal";
    public  static  final  String OVERS = "Overs";
    public  static  final  String WICKETS = "Wickets";
    public  static  final  String MADEINS = "MADEINS";
    public  static  final  String RUNS_GIVEN = "Runs_given";
    public  static  final  String EXTRAS = "Extras";
    public  static  final  String FEILDING_1 = "first_inning_field";
    public  static  final  String BATTING_2 = "second_inning_bat";
    public  static  final  String FEILDING_2 = "second_inning_field";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        try{
//            //db.execSQL("delete from " + BATTING_1);
//            db.execSQL("delete from " + FEILDING_1);
//            db.execSQL("delete from " + BATTING_2);
//            db.execSQL("delete from " + FEILDING_2);
//        }catch (Exception e){
//
//        }

        String createQuery1 =
                "CREATE TABLE " +
                        BATTING_1 +
                        "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PLAYER_NAME+ " VARCHAR(255), " +
                        POSITION+ " INTEGER, " +
                        RUNS+ " INTEGER, " +
                        BALLS_FACED+ " INTEGER, " +
                        SIXES+ " INTEGER, " +
                        FOURS+ " INTEGER, " +
                        BATTED+ " BOOLEAN, " +
                        DISMISSAL+ " VARCHAR(255))";
        String createQuery2 =
                "CREATE TABLE " +
                        FEILDING_1 +
                        "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PLAYER_NAME+ " VARCHAR(255), " +
                        OVERS+ " INTEGER, " +
                        RUNS_GIVEN+ " INTEGER, " +
                        EXTRAS+ " INTEGER, " +
                        SIXES+ " INTEGER, " +
                        FOURS+ " INTEGER, " +
                        MADEINS+ " INTEGER, " +
                        WICKETS+ " INTEGER)";
        String createQuery3 =
                "CREATE TABLE " +
                        BATTING_2 +
                        "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PLAYER_NAME+ " VARCHAR(255), " +
                        POSITION+ " INTEGER, " +
                        RUNS+ " INTEGER, " +
                        BALLS_FACED+ " INTEGER, " +
                        SIXES+ " INTEGER, " +
                        FOURS+ " INTEGER, " +
                        BATTED+ " BOOLEAN, " +
                        DISMISSAL+ " VARCHAR(255))";
        String createQuery4 =
                "CREATE TABLE " +
                        FEILDING_2 +
                        "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PLAYER_NAME+ " VARCHAR(255), " +
                        OVERS+ " INTEGER, " +
                        RUNS_GIVEN+ " INTEGER, " +
                        EXTRAS+ " INTEGER, " +
                        SIXES+ " INTEGER, " +
                        FOURS+ " INTEGER, " +
                        MADEINS+ " INTEGER, " +
                        WICKETS+ " INTEGER)";


        db.execSQL(createQuery1);
        db.execSQL(createQuery2);
        db.execSQL(createQuery3);
        db.execSQL(createQuery4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + BATTING_1);
//        db.execSQL("DROP TABLE IF EXISTS " + FEILDING_1);
//        db.execSQL("DROP TABLE IF EXISTS " + BATTING_2);
//        db.execSQL("DROP TABLE IF EXISTS " + FEILDING_2);
        onCreate(db);

    }



    public void insertBatting1 (String player_name) {
        System.out.println("11111111111111111111111111111111111111111111111 "+ player_name);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLAYER_NAME, player_name);
        db.insert(BATTING_1, null, contentValues);
    }
    public void insertBatting2 (String player_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLAYER_NAME, player_name);
        db.insert(BATTING_2, null, contentValues);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select PLAYER_NAME from "+BATTING_1, null);
        return res;

    }

//    public void clearTables() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("delete from " + BATTING_1);
//        db.execSQL("delete from " + FEILDING_1);
//        db.execSQL("delete from " + BATTING_2);
//        db.execSQL("delete from " + FEILDING_2);
//    }
}
