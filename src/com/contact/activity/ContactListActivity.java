package com.contact.activity;

import java.util.List;

import com.contact.dbhelper.DBContactHandler;
import com.contact.entities.Contact;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;



public class ContactListActivity extends Activity{

	//view object
	ListView lvContacts;

	//data source
	List<Contact> contacts;

	//adapter object
	CustomizedArrayAdapter adapter;	

	EditText txtFilterSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list);

		ActionBar actionBar = getActionBar();

		// Enabling Up / Back navigation
		//actionBar.setDisplayHomeAsUpEnabled(true);

		lvContacts=(ListView)findViewById(R.id.lvserchlist);
		txtFilterSearch=(EditText)findViewById(R.id.txtFilterSearch);

		Bundle extras = new Bundle();
		String newString, titleString;
		if (savedInstanceState == null) {
			extras = getIntent().getExtras();
			if(extras == null) {
				newString= null;
				titleString=null;
			} else {
				newString= extras.getString("SubCategoryId");
				titleString=extras.getString("Name");						
			}
		} else {
			newString= (String) savedInstanceState.getSerializable("SubCategoryId");
			titleString= (String) savedInstanceState.getSerializable("Name");
		}

		this.setTitle(titleString);

		this.LoadData(newString);
	}


	private void LoadData(String newString){
		// data source
		DBContactHandler db= new DBContactHandler(this);
		contacts =db.getAllContactsByCategory(newString);
		if(contacts!=null && contacts.size()>0)
		{
			// adapter		                
			adapter = new CustomizedArrayAdapter(this,contacts);
			lvContacts.setAdapter(adapter);  	
		}
		lvContacts.setOnItemClickListener(onItemClickListener);
		lvContacts.setTextFilterEnabled(true);

		if(adapter!=null && adapter.getCount()>0)
			txtFilterSearch.addTextChangedListener(textWatcher) ;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_contact_list,menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Take appropriate action for each action item click
		switch (item.getItemId()) {		
		case R.id.action_scat_refresh:
			// refresh
			Toast.makeText(getApplicationContext(), "Under construction!!", Toast.LENGTH_LONG).show();
			return true;
		case android.R.id.home: 
			//NavUtils.navigateUpFromSameTask(this);			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	TextWatcher textWatcher=new TextWatcher(){

		@Override
		public void afterTextChanged(Editable s) {				
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {			

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// When user changed the Text

			if (count < before) {
				// We're deleting char so we need to reset the adapter data
				ContactListActivity.this.adapter.resetData();
			}

			ContactListActivity.this.adapter.getFilter().filter(s.toString());

		}
	};
	OnItemClickListener onItemClickListener=  new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {			

			Contact con=contacts.get(position);

			Intent intent=new Intent(getBaseContext(),AddressDetailsActivity.class);
			intent.putExtra("AddressId",String.valueOf(con.getID()));			
			startActivity(intent);								
		}	
	};

}
