package com.contact.dbhelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.contact.entities.SubCategory;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBSubCategoryHandler extends SQLiteAssetHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "contactsManager.sqlite";

	// Contacts table name
	private static final String TABLE_SUB_CATEGORY = "SubCategory";  
	// Contacts Table Columns names
	private static final String KEY_ID = "Id";
	private static final String KEY_SUB_CAT_NAME = "Name";
	private static final String KEY_CAT_ID = "CategoryId";


	public DBSubCategoryHandler(Context context) {
		super(context,DATABASE_NAME , null, DATABASE_VERSION);		
	}

	public void addCategory(SubCategory subCategory){
		SQLiteDatabase db=this.getWritableDatabase();

		ContentValues values=new ContentValues();
		values.put(KEY_SUB_CAT_NAME, subCategory.getName());
		values.put(KEY_CAT_ID, subCategory.getCategoryId());

		db.insert(TABLE_SUB_CATEGORY, null, values);
		db.close();
	}

	public List<SubCategory> getAllSubCategory(int categoryid) {

		SQLiteDatabase db=this.getReadableDatabase();
		List<SubCategory> categoryList=new ArrayList<SubCategory>();

		String selectQuery="Select * from "+ TABLE_SUB_CATEGORY +" where CategoryId="+categoryid;
		Cursor cursor=db.rawQuery(selectQuery, null);

		if(cursor.moveToFirst()){
			do{
				SubCategory cat=new SubCategory();
				cat.setId(Integer.parseInt(cursor.getString(0)));
				cat.setName(cursor.getString(1));
				cat.setCategoryId(Integer.parseInt(cursor.getString(2)));				
				categoryList.add(cat);

			}while(cursor.moveToNext());
		}

		return categoryList;
	}		
}
