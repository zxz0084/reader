package com.swufestu.reader.pramater;

public class Constrant {
    public final static String DB_NAME = "coolreader.db";
    public final static int DB_VERSION = 2;
    public final static String CREATE_TABLE_BOOK = "create table IF NOT EXISTS book(bid integer primary key autoincrement,bpath varchar(255) not null);";
    public final static String CREATE_TABLE_BOOK_MARK = "create table IF NOT EXISTS bookmark(bmid integer primary key autoincrement,"
            + "bid integer not null,bmname varchar(50) not null,bmoffset integer not null,bmsavetime varchar(20) not null);";
    public final static String BOOK_TABLE_NAME = "book";
    public final static String BOOK_ID = "bid";
    public final static String BOOK_PATH = "bpath";
    public final static String BOOK = "book";
    public static int BOOK_ID_IN_DATABASE=0;
}
