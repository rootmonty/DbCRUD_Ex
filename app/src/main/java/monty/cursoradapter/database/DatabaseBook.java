package monty.cursoradapter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monty on 28/10/16.
 */
public class DatabaseBook extends SQLiteOpenHelper {

    public static final String DB_NAME = "books.db";
    public static final String TL_NAME = "books";
    public static final String COL_ID = "id";
    public static final String COL_BOOK = "nameofbook";
    public static final String COL_VOLUME = "volume";
    public static final String COL_PUBLISHER = "publisher";

    public static final int VERSION = 1;


    public DatabaseBook(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createtable = "CREATE TABLE " + TL_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY, " +
                COL_BOOK + " TEXT, " + COL_VOLUME + " INTEGER, " + COL_PUBLISHER + " TEXT " + " );";
        sqLiteDatabase.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TL_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }

    public void addbook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_BOOK, book.getBook_name());
        values.put(COL_PUBLISHER, book.getPublisher());
        values.put(COL_VOLUME, book.getVolume());

        db.insert(TL_NAME, null, values);
        db.close();
    }


    public Book getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TL_NAME, new String[]{COL_ID,COL_BOOK, COL_VOLUME, COL_PUBLISHER}, COL_ID+"=?"
                , new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Book book = new Book(Integer.parseInt(cursor.getString(0)),
                cursor.getString(cursor.getColumnIndex("nameofbook")),
                Integer.parseInt(cursor.getString(cursor.getColumnIndex("volume")))
                ,cursor.getString(cursor.getColumnIndex("publisher")));

        cursor.close();
        return book;
    }

    public List<Book> geteverything() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Book> array = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TL_NAME, null);

        if (cursor.moveToFirst())
            do {
                array.add(new Book(Integer.parseInt(cursor.getString(0)),cursor.getString(1)
                        , Integer.parseInt(cursor.getString(2))
                        , cursor.getString(3)));
            }
            while (cursor.moveToNext());


        cursor.close();
        return array;
    }

    public void deletebook(int rowid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TL_NAME, COL_ID + "=?", new String[]{String.valueOf(rowid)});
        db.close();
    }


    public int updateBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_ID,book.getId());
        cv.put(COL_BOOK,book.getBook_name());
        cv.put(COL_VOLUME,book.getVolume());
        cv.put(COL_PUBLISHER,book.getPublisher());

        return db.update(TL_NAME,cv,COL_ID+"=?",new String[]{String.valueOf(book.getId())});

    }
    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+TL_NAME,null);
        int count = c.getCount();
        c.close();
        return count;

    }
}