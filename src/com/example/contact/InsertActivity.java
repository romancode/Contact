package com.example.contact;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends Activity {

	private EditText txtPhone,txtName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		
		txtPhone = (EditText)findViewById(R.id.txtPhone);
		txtName = (EditText)findViewById(R.id.txtName);		
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
				
				DatabaseHandler db= new DatabaseHandler(this);
				Contact contact=new Contact();
				contact._name= txtName.getText().toString();
				contact._phone_number=txtPhone.getText().toString();
				
				db.addContact(contact);
				
				Toast.makeText(this, "Saved sucessfully",Toast.LENGTH_LONG).show();
				
				txtName.setText("");
				txtPhone.setText("");
				txtName.setFocusable(true);
			}	
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insert, menu);
		return true;
	}

}
