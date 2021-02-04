package com.app.UAS201804004

import android.provider.BaseColumns

object DBInfo {
    class UserInput: BaseColumns {
        companion object {
            val TABLE_USER = "users"
            val COL_EMAIL = "email"
            val COL_PASS = "pass"
            val COL_USERNAME = "username"
            val COL_JUMLAH = "jumlah"

            val TABLE_BOOK = "books"
            val B_TITLE = "title"
            val B_PRICE = "price"
            val B_AUTHOR = "author"
            val B_DESC = "description"
        }
    }
}