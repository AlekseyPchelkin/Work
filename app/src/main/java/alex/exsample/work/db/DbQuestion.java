package alex.exsample.work.db;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import alex.exsample.work.db.DbHelper;

public class DbQuestion {
    private SQLiteDatabase db;
    private Cursor userCursor;
    private DbHelper dataHelper;
    private Context myContext;
    String table_name;

    public DbQuestion(String table_name, Context context) {
        this.table_name = table_name;
        this.myContext = context;
        dataHelper = new DbHelper(myContext);
        dataHelper.create_db();
        db = dataHelper.open();
    }

    public int GetCountPosition(String column) {
       String question = "SELECT " + column + " from '" +  table_name + "'";
       userCursor = db.rawQuery("select * from '" + table_name + "'", null);
       userCursor = db.rawQuery(question,null);
       int count = userCursor.getCount();
       return count;
    }

    public String GetField(String field, int position){
        String question = "SELECT " + field + " from '" +  table_name + "'";
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(position);
        @SuppressLint("Range") String item_title = userCursor.getString(userCursor.getColumnIndex(field));
        return  item_title;
    }


}
