package com.swufestu.reader.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.swufestu.reader.domain.Book;
import com.swufestu.reader.pramater.Constrant;

public class DB extends SQLiteOpenHelper {
    private static final String TAG = "DB";
    private SQLiteDatabase sql = null;
    private boolean isopen = false;

    public DB(Context c) {
        super(c, Constrant.DB_NAME, null, Constrant.DB_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "Create Book Table");
        sqLiteDatabase.execSQL(Constrant.CREATE_TABLE_BOOK);
        //sqLiteDatabase.execSQL(Constrant.CREATE_TABLE_BOOK_MARK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
//在数据库中保存书籍信息
    public int saveBook(Book book) {
        Log.i(TAG, "saveBook:select book from database");
        Log.i(TAG, "saveBook:select book from database" + book.getBookPath());
        if (!isopen) {
            isopen = true;
            sql = getWritableDatabase();
        }
        String[] col = new String[]{Constrant.BOOK_ID, Constrant.BOOK_PATH};
        Cursor cursor = sql.query(Constrant.BOOK, col, Constrant.BOOK_PATH + "=\""
                + book.getBookPath() + "\"", null, null, null, null);
        int count = cursor.getCount();
        if (count == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constrant.BOOK_PATH, book.getBookPath());
            cursor.close();
            return (int) sql.insert(Constrant.BOOK, null, contentValues);
        } else {
            cursor.moveToLast();
            int i = cursor.getInt(0);
            return i;
        }
    }

//从数据库中删除书籍信息
    public void deleteBook(String bookName) {
        if (!isopen) {
            isopen = true;
            sql = getWritableDatabase();
        }
        String[] col = new String[]{Constrant.BOOK_ID, Constrant.BOOK_PATH};
        Cursor cursor = sql.query(Constrant.BOOK, col, Constrant.BOOK_PATH + "=\""
                + bookName + "\"", null, null, null, null);
        Log.i(TAG,"select deletebook data from database……");
        int ID=0;
        while (cursor.moveToNext()){
            ID=cursor.getInt(0);
        }
        String str="delete from"+Constrant.BOOK_TABLE_NAME+"where"+Constrant.BOOK_ID+"="+ID;
        Log.i(TAG,""+str);
        sql.execSQL(str);
        cursor.close();
    }
//关闭数据库
    public void close() {
        String tag = "close";
        if (isopen) {
            sql.close();
            isopen = false;
        }
    }

}