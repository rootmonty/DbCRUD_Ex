package monty.cursoradapter.SugarORm;

import com.orm.SugarRecord;

/**
 * Created by monty on 29/10/16.
 */
public class Bookdb extends SugarRecord{

    //Create objects for the database(basically the columns)
    String bookname;
    String author;

    public Bookdb(String name,String author){
        this.bookname = name;
        this.author = author;
    }

    public Bookdb(){} //simple constructor for the initiation of any object

    public String getBookname(){
        return bookname;
    }
}
