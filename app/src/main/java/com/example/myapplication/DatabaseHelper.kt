package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.Pantry.Pantrydata
import com.example.myapplication.Recipe.FilterItemdata
import java.io.BufferedReader

class DatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, "user.db", null, 10) {

    private val UTable: String = "USER_TABLE"
    private val UID: String = "ID"
    private val UName: String = "USER_NAME"
    private val UEmail: String = "USER_EMAIL"
    private val UPass: String = "USER_PASSWORD"

    private val ITable: String = "INGREDIENTS_TABLE"
    private val IID: String = "INGREDIENTS_ID"
    private val IName: String = "INGREDIENTS_NAME"

    private val UITable: String = "USER_INGREDIENTS_TABLE"

    private val FTable: String = "FILTER_TABLE"
    private val FName: String = "FILTER_NAME"
    private val FID: String = "FILTER_ID"
    private val FType: String = "FILTER_TYPE"

    private val UFTable: String = "USER_FILTER_TABLE"


    override fun onCreate(p0: SQLiteDatabase?) {
         val createUserTableStatement: String  = "CREATE TABLE " + UTable +" ("+
                 UID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 UName + " TEXT, " +
                 UEmail + " TEXT, " +
                 UPass + " TEXT)"

         val createIngredientsTableStatement: String = "CREATE TABLE " + ITable + " (" +
                 IID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 IName + " TEXT)"

        val createUserIngredientsTableStatement: String = "CREATE TABLE " + UITable + " (" +
                UID + " INTEGER, " +
                IName + " TEXT)"

        val createFilterTableStatement: String = "CREATE TABLE " + FTable + " (" +
                FID + " INTEGER, " +
                FName + " TEXT, " +
                FType + " TEXT)"

        val createUserFilterTableStatement: String = "CREATE TABLE " + UFTable + " (" +
                UID + " INTEGER, " +
                FName + " TEXT, " +
                FType +" TEXT)"

        p0?.execSQL(createFilterTableStatement)
        p0?.execSQL(createUserFilterTableStatement)
        p0?.execSQL(createIngredientsTableStatement)
        p0?.execSQL(createUserIngredientsTableStatement)
        p0?.execSQL(createUserTableStatement)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addUser( name: String, email: String, pass: String ) {
        val db: SQLiteDatabase  = writableDatabase

        val cv = ContentValues()

        cv.put(UName, name)
        cv.put(UEmail, email)
        cv.put(UPass, pass)

        db.insert(UTable, null ,cv)

        db.close()
    }

    fun verify(email: String, pass: String): Boolean {
        val db: SQLiteDatabase = readableDatabase
        val values = "select * from $UTable where $UEmail = '$email' and $UPass = '$pass'"

        val check: Cursor  = db.rawQuery(values,null)

        println(check.count)

        if (check.count<=0){
            check.close()
            return false
        }

        check.close()
        db.close()
        return true
    }



    fun fillIngredients(context: Context){
        val db: SQLiteDatabase  = this.writableDatabase

        db.execSQL("DELETE FROM $ITable")

        val inputStream: BufferedReader = context.assets.open("ingredients.txt").bufferedReader()
        val lineList = mutableListOf<String>()
        var set = false
        val cv = ContentValues()


        inputStream.useLines { lines -> lines.forEach { lineList.add(it) } }
        lineList.forEach {
            when {
                it == "-" -> set = true
                set -> set = false
                else -> {cv.put(IName, it); db.insert(ITable, null, cv)}
            }
        }
        db.close()
    }

    fun getIngredients(): List<Pantrydata>{
        val db: SQLiteDatabase  = this.readableDatabase

        val queryString= "SELECT * FROM $ITable"

        val cursor: Cursor = db.rawQuery(queryString, null)

        val resultList: MutableList<Pantrydata> = mutableListOf()

        if(cursor.moveToFirst()){
            do{
                resultList += Pantrydata(cursor.getString(1), false)
            } while(cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return resultList
    }

    fun fillFilters(context: Context){
        val db: SQLiteDatabase  = this.writableDatabase
        val cv = ContentValues()

        db.execSQL("DELETE FROM $FTable")

        var set = true
        val filenames = arrayOf("difficulty.txt","mealtype.txt","rating.txt","time.txt")

        val inputStream: BufferedReader = context.assets.open("ingredients.txt").bufferedReader()
        val lineList = mutableListOf<String>()

        //loading ingredients into the table
        cv.put(FType,"Main Ingredient")
        inputStream.useLines { lines -> lines.forEach { lineList.add(it)} }
        lineList.forEach{
            when {
                it == "-" -> { set = true }
                set -> { set = false }
                else -> { cv.put(FName, it); db.insert(FTable, null, cv)}
            }
        }

        //loading the other filters into the table
        for (i in filenames){
            textToCV(i, context, db)
        }

        db.close()
    }

    private fun textToCV(filename:String, context: Context, db: SQLiteDatabase){
        val inputStream: BufferedReader = context.assets.open(filename).bufferedReader()
        val lineList = mutableListOf<String>()

        val cv = ContentValues()

        inputStream.useLines { lines -> lines.forEach { lineList.add(it)} }
        lineList.forEach{
            when(filename){
                "difficulty.txt" -> cv.put(FType,"Difficulty")
                "mealtype.txt" -> cv.put(FType,"Meal Type")
                "rating.txt"-> cv.put(FType,"Rating")
                "time.txt"->cv.put(FType,"Time")
            }

            cv.put(FName, it)
            db.insert(FTable,null,cv)
        }

    }

    fun getFilters(type: String): Array<FilterItemdata>{
        val db: SQLiteDatabase  = this.readableDatabase
        var list: Array<FilterItemdata> = emptyArray()

        val queryString = "SELECT * FROM $FTable WHERE $FType = '$type'"
        val cursor: Cursor = db.rawQuery(queryString, null)

        if(cursor.moveToFirst()){
            do{
                list += FilterItemdata(cursor.getString(1), cursor.getString(2))
            }while(cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun updateUserFilters(filters: Array<String>, user: String, type: String) {
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        val ucursor: Cursor = db.rawQuery("SELECT * FROM $UTable WHERE $UName = '$user'", null)
        ucursor.moveToFirst()
        val userID = ucursor.getInt(0)

        db.execSQL("DELETE FROM $UFTable WHERE $UID = '$userID' AND $FType = '$type'")

        cv.put(FType, type)
        for (i in filters) {
            cv.put(UID, ucursor.getString(0))
            cv.put(FName, i)
            db.insert(UFTable, null, cv)
        }
        ucursor.close()
        db.close()
    }

    fun getUserFilters(user:String, type: String): Array<String>{
        val db: SQLiteDatabase = this.writableDatabase
        val Ucursor: Cursor = db.rawQuery("SELECT * FROM $UTable WHERE $UName = '$user'", null)
        val FCursor: Cursor = db.rawQuery("SELECT * FROM $FTable WHERE $FType = '$type'", null)

        Ucursor.moveToFirst()
        val userID = Ucursor.getInt(0)
        val UFcursor: Cursor = db.rawQuery("SELECT * FROM $UFTable WHERE $UID = '$userID'", null)



        var resultset: Array<String> = emptyArray()

        if(UFcursor.moveToFirst() && FCursor.moveToFirst()){
            do{
                FCursor.moveToFirst()
                do {
                    //check if Fname in filter list and Fname in UserFilter list match
                    if(FCursor.getString(1)==UFcursor.getString(1)) {
                        resultset += UFcursor.getString(1)
                    }
                }while(FCursor.moveToNext())
            }while(UFcursor.moveToNext())
        }
        Ucursor.close()
        FCursor.close()
        UFcursor.close()
        db.close()
        return resultset
    }
}