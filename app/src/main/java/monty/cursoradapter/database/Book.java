package monty.cursoradapter.database;

/**
 * Created by monty on 28/10/16.
 */
public class Book {

    int id;
    String book_name;
    String publisher;
    int volume;

    public Book(int id,String book_name,int volume,String publisher){
        this.id = id;
        this.book_name = book_name;
        this.volume = volume;
        this.publisher = publisher ;
    }

    public Book(String book_name,int volume,String publisher){
        this.book_name = book_name;
        this.volume = volume;
        this.publisher= publisher;
    }
    //no need for any other getter methods here
    //only setting constructor is required

    public int getId()
    {return id; }
    public String getBook_name(){
        return book_name;
    }
    public String getPublisher(){
        return publisher;
    }
    public int getVolume(){
        return volume;
    }
}
