package com.contact.dbhelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.contact.entities.Category;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBCategoryHandler extends SQLiteAssetHelper {
	
	// All Static variables
		// Database Version
		private static final int DATABASE_VERSION = 1;

		// Database Name
		private static final String DATABASE_NAME = "contactsManager.sqlite";

		// Contacts table name
		private static final String TABLE_CATEGORY = "category";  
		// Contacts Table Columns names
		private static final String KEY_ID = "id";
		private static final String KEY_CAT_NAME = "name";
		private static final String KEY_CAT_DESC = "phone_number";
		

	public DBCategoryHandler(Context context) {
		super(context,DATABASE_NAME , null, DATABASE_VERSION);		
	}
	
	public void addCategory(Category category){
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		values.put(KEY_CAT_NAME, category.getName());
		values.put(KEY_CAT_DESC, category.getDescription());
		
		db.insert(TABLE_CATEGORY, null, values);
		db.close();
	}

	public List<Category> getAllCategory() {
		SQLiteDatabase db=this.getReadableDatabase();
		List<Category> categoryList=new ArrayList<Category>();
		
		String selectQuery="Select * from "+ TABLE_CATEGORY;
		Cursor cursor=db.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			do{

				Category cat=new Category();
				cat.setId(Integer.parseInt(cursor.getString(0)));
				cat.setName(cursor.getString(1));
				cat.setDescription(cursor.getString(2));
				
				categoryList.add(cat);
				
			}while(cursor.moveToNext());
		}
		
		return categoryList;
	}
	

}
