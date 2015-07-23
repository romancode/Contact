package com.contact.activity;


import java.util.List;

import com.contact.dbhelper.DBCategoryHandler;
import com.contact.dbhelper.DBContactHandler;
import com.contact.entities.Contact;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertActivity extends Activity implements OnItemSelectedListener {

	private EditText txtPhone,txtName;
	private Spinner spnCategory;
	List<String> category;
	ArrayAdapter<String> catAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		//initialize UI elements
		txtPhone = (EditText)findViewById(R.id.txtPhone);
		txtName = (EditText)findViewById(R.id.txtName);		
		spnCategory=(Spinner)findViewById(R.id.spnCategory);

		populateData();

	}
	private void populateData(){
		//populated spinner data
		DBCategoryHandler db=new DBCategoryHandler(this);
		category=db.getAllCategoryName();
		if(category!=null && category.size()>0){
			catAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,category); 
			catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
			spnCategory.setAdapter(catAdapter);
		}
	}
	public void onClick(View view){

		switch (view.getId()) {
		case R.id.btnClear:	
			txtName.setText("");
			txtPhone.setText("");
			break;

		case R.id.btnSave:

			if(txtName.getText().length()==0 || txtPhone.getText().length()==0){
				Toast.makeText(this, "Please enter name & phone no",Toast.LENGTH_LONG).show();
				return;
			}
			else{	

				int selectedCategoryId=spnCategory.getSelectedItemPosition();

				DBContactHandler db= new DBContactHandler(this);
				Contact contact=new Contact();
				contact.setName(txtName.getText().toString());
				contact.setPhoneNumber(txtPhone.getText().toString());
				contact.setCategoryId(selectedCategoryId);

				db.addContact(contact);

				Toast.makeText(this, "Saved sucessfully",Toast.LENGTH_LONG).show();

				txtName.setText("");
				txtPhone.setText("");
				spnCategory.setAdapter(null);
				populateData();
				spnCategory.setFocusable(true);
			}	
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();

		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {


	}


}
