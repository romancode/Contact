package com.example.contact;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


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

		lvContacts=(ListView)findViewById(R.id.lvserchlist);
		txtFilterSearch=(EditText)findViewById(R.id.txtFilterSearch);

		Bundle extras = new Bundle();
		String newString;
		if (savedInstanceState == null) {
			extras = getIntent().getExtras();
			if(extras == null) {
				newString= null;
			} else {
				newString= extras.getString("KeyWord");
			}
		} else {
			newString= (String) savedInstanceState.getSerializable("KeyWord");
		}

		// data source
		DatabaseHandler db= new DatabaseHandler(this);
		contacts =db.getAllContactsByName(newString);
		if(contacts!=null && contacts.size()>0)
		{
			// adapter		                
			adapter = new CustomizedArrayAdapter(this,contacts);
			lvContacts.setAdapter(adapter);  	
		}
		lvContacts.setOnItemClickListener(onItemClickListener);
		lvContacts.setTextFilterEnabled(true);

		if(adapter.getCount()>0)
			txtFilterSearch.addTextChangedListener(textWatcher) ;
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

		Dialog detailsDialog;
		Button btnDialogCancel;

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long item) {

			Contact con=contacts.get(position);

			detailsDialog=new Dialog(ContactListActivity.this);
			detailsDialog.setContentView(R.layout.contact_details_dialog);
			detailsDialog.setTitle(con.getName());

			TextView txtDialogName=(TextView)detailsDialog.findViewById(R.id.txt_dialog_name);
			txtDialogName.setText(con.getName());

			TextView txtDialogPhone=(TextView)detailsDialog.findViewById(R.id.txt_dialog_phone);
			txtDialogPhone.setText(con.getPhoneNumber());

			detailsDialog.show();

			btnDialogCancel=(Button)detailsDialog.findViewById(R.id.btnDailogCancel);
			btnDialogCancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					detailsDialog.dismiss();

				}
			});
		}
	};

}
