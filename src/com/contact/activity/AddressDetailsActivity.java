package com.contact.activity;

import com.contact.dbhelper.DBContactHandler;
import com.contact.entities.Contact;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class AddressDetailsActivity extends Activity {
	
	
	// Google Map
   private GoogleMap googleMap;
	
	TextView txtName,txtPhone, txtAddress, txtServices, txtWeekDays, txtWorkingHour; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_details);

		//ActionBar actionBar = getActionBar();

		// Enabling Back navigation
		//actionBar.setDisplayHomeAsUpEnabled(true);

		Bundle extras = new Bundle();
		String newString = null;
		if (savedInstanceState == null) {
			extras = getIntent().getExtras();
			if(extras == null) {
				//newString= null;
			} else {
				newString= extras.getString("AddressId");				
			}
		} else {
			newString= (String) savedInstanceState.getSerializable("AddressId");		
		}

		this.LoadData(newString);

	}
	private void LoadData(String contactId){
		// data source
		DBContactHandler db= new DBContactHandler(this);
		Contact contact =db.getContact(Integer.parseInt(contactId));
		if(contact!=null)
		{
			this.setTitle(contact.getName());
			
			txtName=(TextView)findViewById(R.id.txt_details_name);
			txtName.setText(contact.getName());
						
			txtAddress=(TextView)findViewById(R.id.txt_details_address);
			txtAddress.setText(contact.getAddress());
			
			txtServices=(TextView)findViewById(R.id.txt_details_services);
			txtServices.setText(contact.getServices());
			
			txtWeekDays=(TextView)findViewById(R.id.txt_details_WeekDays);
			txtWeekDays.setText(contact.getWeekDays());
			
			txtWorkingHour=(TextView)findViewById(R.id.txt_details_WorkingHour);
			txtWorkingHour.setText(contact.getWorkingHour());
			
			
			this.LoadMap();
								
		}
	}
	
	private void LoadMap(){
		
	}
	 
     @SuppressWarnings("unused")
	private void initilizeMap() {
         if (googleMap == null) {
             googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

             // check if map is created successfully or not
             if (googleMap == null) {
                 Toast.makeText(getApplicationContext(),
                         "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
             }
         }
     }

}
