package com.contact.dbhelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.contact.entities.Contact;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DBContactHandler extends SQLiteAssetHelper{

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "contactsManager.sqlite";

	// Contacts table name
	private static final String TABLE_CONTACTS = "address";  
	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PH_NO = "contact";
	private static final String KEY_CAT_ID = "category_id";


	public DBContactHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	// Adding new contact
	public void addContact(Contact contact) {
		SQLiteDatabase db=this.getWritableDatabase();

		ContentValues values=new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PH_NO, contact.getPhoneNumber());
		values.put(KEY_CAT_ID, contact.getCategoryId());

		db.insert(TABLE_CONTACTS, null, values);	
		db.close();
	}

	// Getting single contact
	public Contact getContact(int id) {

		Contact contact=null;
		SQLiteDatabase db=this.getReadableDatabase();
		
		String selectQuery="Select * from "+ TABLE_CONTACTS +" where "+ KEY_ID+"=" +id;
		Cursor cursor=db.rawQuery(selectQuery, null);

		if(cursor!=null){
			cursor.moveToFirst();

			contact=new Contact();
			contact.setID(Integer.parseInt(cursor.getString(0)));
			contact.setName(cursor.getString(1));
			contact.setPhoneNumber(cursor.getString(2));
			contact.setServices(cursor.getString(3));
			contact.setWeekDays(cursor.getString(4));
			contact.setAddress(cursor.getString(5));
			contact.setEmail(cursor.getString(6));
			contact.setLatitude(cursor.getString(7));
			contact.setLongitude(cursor.getString(8));
			contact.setWorkingHour(cursor.getString(9));
			contact.setCategoryId(Integer.parseInt(cursor.getString(10)));
		}

		return contact;
	}


	// Getting contacts Count
	public int getContactsCount() {
		String query="SELECT * FROM" + TABLE_CONTACTS;
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(query, null);
		cursor.close();
		return cursor.getCount();
	}
	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PH_NO, contact.getPhoneNumber());

		return db.update(TABLE_CONTACTS,values, KEY_ID +"=?",new String[]{ String.valueOf(contact.getID())});
	}
	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",new String[] { String.valueOf(contact.getID()) });
		db.close();
	}



	// Getting All Contacts by Category
	public List<Contact> getAllContactsByCategory(String category) {
		SQLiteDatabase db=this.getReadableDatabase();
		List<Contact> contactList=new ArrayList<Contact>();
		
		String selectQuery="Select * from "+ TABLE_CONTACTS +" where "+ KEY_CAT_ID+"=" +category;
		Cursor cursor=db.rawQuery(selectQuery, null);

		if(cursor.moveToFirst()){
			do{

				Contact contact=new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setPhoneNumber(cursor.getString(2));
				contact.setServices(cursor.getString(3));
				contact.setWeekDays(cursor.getString(4));
				contact.setAddress(cursor.getString(5));
				contact.setEmail(cursor.getString(6));
				contact.setLatitude(cursor.getString(7));
				contact.setLongitude(cursor.getString(8));
				contact.setWorkingHour(cursor.getString(9));
				contact.setCategoryId(Integer.parseInt(cursor.getString(10)));
				contactList.add(contact);

			}while(cursor.moveToNext());
		}

		return contactList;
	}


}
