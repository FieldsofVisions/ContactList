package comfieldsofvisions.google.httpssites.contactlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDb";
    private static final String TABLE_NAME = "MyTable";
    private static final int DATABASE_VERSION =1;
    private static final String UID = "_id";
    private static final String NAME = "Name";

    public DataBaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
