package monty.cursoradapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import monty.cursoradapter.SugarORm.Bookdb;
import monty.cursoradapter.database.Book;
import monty.cursoradapter.database.CustomAdapter;
import monty.cursoradapter.database.DatabaseBook;


public class MainActivity extends AppCompatActivity {

    DatabaseBook bookdb ;
    /*
    Try sugarorm object
     */

    int counter = 0;
    List<Bookdb> lb = new ArrayList<>();
  //  Bookdb book = new Bookdb();
    ListView listview;
    CustomAdapter cadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        bookdb = new DatabaseBook(this);

        listview = (ListView) findViewById(R.id.booklist);


        bookdb.addbook(new Book("The Last Juror",1,"John Grisham"));
        bookdb.addbook(new Book("The Curse of the Black Pearl",2,"Rob Kidd"));
        bookdb.addbook(new Book("Sorcerors Stone",1,"J.K.Rowling"));
        bookdb.addbook(new Book("Chamber of Secrets",1,"JK Rowling"));
        bookdb.addbook(new Book("Prisoner of Azkaban",1,"JK Rowling"));
        bookdb.addbook(new Book("Goblet Of Fire",1,"JK Rowling"));
        bookdb.addbook(new Book("Order Of the Phoenix",1,"JK Rowling"));
        bookdb.addbook(new Book("Half Blood Prince",1,"JK Rowling"));
        bookdb.addbook(new Book("the Deathly Hallows",1,"JK Rowling"));
        bookdb.addbook(new Book("sharon stones toddler",1,"Sharon Stone"));

        bookdb.deletebook(3);
        bookdb.updateBook(new Book(5,"the toddler",1,"sharon stone"));



        /**
         * Usage of customadapter here.Cursor created



        SQLiteDatabase database = bookdb.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+DatabaseBook.TL_NAME,null);

        cadapter = new CustomAdapter(this,cursor);
        listview.setAdapter(cadapter);

        cursor.close();

        */



        List<Book> temp = new ArrayList<>();
        temp = bookdb.geteverything();

        for (Book b : temp) {
            Log.i("Details:", b.getId()+" "+b.getBook_name()+" "+b.getVolume()+" "+b.getPublisher());
        }


/*
        for(int i =0 ; i< books.length;i++)
        {
            Bookdb book = new Bookdb(books[i],"JK Rowling");
            book.save();
            lb.add(i,book);
            Log.i("The book added to list:",lb.get(i).getBookname());
        }


            */



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
