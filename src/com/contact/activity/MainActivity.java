package com.contact.activity;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText txtString; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.Initialization();
	}

	private void Initialization(){
		txtString=(EditText)findViewById(R.id.txtString);		
	}
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.btnReset:						
			break;
		case R.id.btnSearch:
			if(txtString.getText().length()==0){
				Toast.makeText(this, "Please enter search text",Toast.LENGTH_LONG).show();
				return;
			}
			else{								
				Intent intent=new Intent(getBaseContext(),ContactListActivity.class);
				intent.putExtra("KeyWord", txtString.getText().toString());
				startActivity(intent);
			}
			break;
		case R.id.btnInsert:
			Intent i=new Intent(getBaseContext(),InsertActivity.class);
			startActivity(i);

		default:
			break;
		}
	}

}
