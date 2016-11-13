package monty.cursoradapter.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import monty.cursoradapter.R;

/**
 * Created by monty on 28/10/16.
 */
public class CustomAdapter extends android.widget.CursorAdapter {


    public CustomAdapter(Context context, Cursor c) {
        super(context,c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View rootview = LayoutInflater.from(context).inflate(R.layout.adapterlayout,viewGroup,false);

        return rootview;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
         TextView tv2 = (TextView) view.findViewById(R.id.tv2);
        TextView tv3 = (TextView) view.findViewById(R.id.tv3);
        TextView tv4 = (TextView) view.findViewById(R.id.tv4);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        String name = cursor.getString(cursor.getColumnIndexOrThrow("nameofbook"));
        int volume = cursor.getInt(cursor.getColumnIndexOrThrow("volume"));
        String publisher = cursor.getString(cursor.getColumnIndexOrThrow("publisher"));
        tv1.setText(String.valueOf(id));
        tv2.setText(name);
        tv3.setText(String.valueOf(volume));
        tv4.setText(publisher);
    }
}
