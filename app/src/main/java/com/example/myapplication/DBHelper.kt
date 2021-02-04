package com.app.UAS201804004

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "books.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_BOOK =
            "CREATE TABLE " + DBInfo.UserInput.TABLE_BOOK + " (" + DBInfo.UserInput.B_TITLE +
                    " VARCHAR(200) PRIMARY KEY, " + DBInfo.UserInput.B_AUTHOR + " TEXT, " +
                    DBInfo.UserInput.B_DESC + " TEXT," + DBInfo.UserInput.B_PRICE + " TEXT)"

        private val SQL_CREATE_USER =
            "CREATE TABLE " + DBInfo.UserInput.TABLE_USER + " (" + DBInfo.UserInput.COL_EMAIL +
                    " VARCHAR(200) PRIMARY KEY, " + DBInfo.UserInput.COL_PASS + " TEXT, " +
                    DBInfo.UserInput.COL_USERNAME + " TEXT )"

        private val SQL_INSERT_USER =
            "INSERT INTO " + DBInfo.UserInput.TABLE_USER + "( email, pass, username) " +
                    " VALUES('admin', 'admin', 'admin')"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfo.UserInput.TABLE_USER
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_BOOK)
        db?.execSQL(SQL_CREATE_USER)
        db?.execSQL(SQL_INSERT_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertBook(title: String, author: String, price: String, desc: String): Boolean {
        val db = writableDatabase
        val t_book = DBInfo.UserInput.TABLE_BOOK
        val t_title = DBInfo.UserInput.B_TITLE
        val t_author = DBInfo.UserInput.B_AUTHOR
        val t_desc = DBInfo.UserInput.B_DESC
        val t_price = DBInfo.UserInput.B_PRICE
        var sql = "INSERT INTO " + t_book + "("+ t_title +", "+ t_author +", " + t_price + ", " + t_desc +
                ") VALUES('" + title + "', '" + author + "', '" + price + "', '" + desc + "')"
        db.execSQL(sql)
        return true
    }

    fun fullData(): ArrayList<DBModel> {
        val books = arrayListOf<DBModel>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery("SELECT * FROM " + DBInfo.UserInput.TABLE_BOOK, null)
        } catch (e: SQLException) {
            db.execSQL(SQL_CREATE_BOOK)
            return ArrayList()
        }

        var ttitle: String
        var tauthor: String
        var tprice: String
        var descrip: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                ttitle = cursor.getString(cursor.getColumnIndex(DBInfo.UserInput.B_TITLE))
                tauthor = cursor.getString(cursor.getColumnIndex(DBInfo.UserInput.B_AUTHOR))
                tprice = cursor.getString(cursor.getColumnIndex(DBInfo.UserInput.B_PRICE))
                descrip = cursor.getString(cursor.getColumnIndex(DBInfo.UserInput.B_DESC))

                books.add(DBModel(ttitle, tauthor, tprice, descrip))
                cursor.moveToNext()
            }
        }
        return books
    }

    fun deleteData(in_title: String){
        val db = writableDatabase
        val book_table = DBInfo.UserInput.TABLE_BOOK
        val b_title = DBInfo.UserInput.B_TITLE
        var sql = "DELETE FROM "+ book_table +" WHERE "+ b_title + "='"+ in_title +"'"
        db.execSQL(sql)
    }

    fun updateData(i_title: String, i_auth: String, i_price: String, i_desc: String){
        val db = writableDatabase
        val t_book = DBInfo.UserInput.TABLE_BOOK
        val b_title = DBInfo.UserInput.B_TITLE
        val b_auth = DBInfo.UserInput.B_AUTHOR
        val b_price = DBInfo.UserInput.B_PRICE
        val b_desc = DBInfo.UserInput.B_DESC

        var sql = "UPDATE "+ t_book +" SET "+
                b_desc+"='"+ i_desc +"', " +
                b_auth +"='"+ i_auth +"', " +
                b_title +"='"+ i_title +"', " +
                b_price+"='"+ i_price +"' WHERE "+
                b_title +"='"+ i_title +"'"
        db.execSQL(sql)
    }

    //User Config
    @Throws(SQLiteConstraintException::class)
    fun registerUser(emailin: String, passin: String, usernamein: String): Boolean {
        val db = writableDatabase
        val namatablet = DBInfo.UserInput.TABLE_USER
        val emailt = DBInfo.UserInput.COL_EMAIL
        val passt = DBInfo.UserInput.COL_PASS
        val usernamet = DBInfo.UserInput.COL_USERNAME
        var sql = "INSERT INTO "+ namatablet +"("+emailt+", "+passt+", "+usernamet+" ) " + "VALUES('"+emailin+"', '"+passin+"', '"+usernamein+"')"
        db.execSQL(sql)
        return true
    }

    fun cekUser(emailin: String):String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+DBInfo.UserInput.COL_EMAIL+") as jumlah FROM "+DBInfo.UserInput.TABLE_USER + " WHERE " +
                    DBInfo.UserInput.COL_EMAIL + " =='" + emailin +"'", null)
        } catch (e: SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }

        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserInput.COL_JUMLAH))
        }
        return jumlah
    }

    fun cekLogin(emailin: String, usernamin: String, passin: String) : String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+DBInfo.UserInput.COL_EMAIL+") as jumlah FROM "+DBInfo.UserInput.TABLE_USER + " WHERE " +
                    DBInfo.UserInput.COL_EMAIL + " =='" + emailin +"' OR "+ DBInfo.UserInput.COL_USERNAME + " =='"+ usernamin + "' AND " + DBInfo.UserInput.COL_PASS + "=='" +passin+ "'", null)
        } catch (e: SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }

        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserInput.COL_JUMLAH))
        }
        return jumlah
    }

}

