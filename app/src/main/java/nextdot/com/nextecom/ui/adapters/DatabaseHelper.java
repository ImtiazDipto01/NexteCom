package nextdot.com.nextecom.ui.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by sakib on 3/21/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public Context context ;
    private static final String DATABASE_NAME = "WISHLIST" ;
    private static final int DATABASE_VERSION = 1 ;
    private static final String CREATE_QUERY = "CREATE TABLE "+ Constant.TABLE_NAME+"("+ Constant.PRODUCT_ID+" TEXT,"+
            Constant.PRODUCT_NAME+" TEXT,"+ Constant.PRODUCT_PRICE+" TEXT);" ;

    public DatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION) ;
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL(CREATE_QUERY);
            Message.message(context, "onCreate Called!");
        }
        catch (SQLiteException e){
            Message.message(context, ""+e);
        }
        //Toast Message
    }

    public void AddData(String productid, String productname, String productprice, SQLiteDatabase db){

        try{
            ContentValues contentValues = new ContentValues() ;
            contentValues.put(Constant.PRODUCT_ID, productid);
            contentValues.put(Constant.PRODUCT_NAME, productname);
            contentValues.put(Constant.PRODUCT_PRICE, productprice);
            db.insert(Constant.TABLE_NAME, null, contentValues) ;
            Message.message(context, "Data Added");
        }
        catch (SQLiteException e){
            Message.message(context, ""+e);
        }
    }
    public Cursor getuserinfo(String productid, SQLiteDatabase db){

        Cursor cursor ;
        String[] column_name = {Constant.PRODUCT_ID} ;
        String selection = Constant.PRODUCT_ID+" LIKE ?" ;
        String[] selection_args = {productid} ;
        cursor = db.query(Constant.TABLE_NAME, column_name, selection, selection_args, null, null, null);
        return cursor ;
    }

    public void DeleteData(String productid, SQLiteDatabase db){

        try{
            String selection = Constant.PRODUCT_ID+" LIKE ?" ;
            String[] selection_args = {productid} ;
            db.delete(Constant.TABLE_NAME, selection, selection_args) ;
            Message.message(context, "Data Deleted");
        }
        catch (Exception e){
            Message.message(context, ""+e);
        }
    }


    public Cursor getData(SQLiteDatabase db){

        Cursor cursor ;
        String[] ColumnName ={Constant.PRODUCT_ID, Constant.PRODUCT_NAME, Constant.PRODUCT_PRICE} ;
        cursor = db.query(Constant.TABLE_NAME, ColumnName, null,null,null,null,null) ;
        return cursor ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
