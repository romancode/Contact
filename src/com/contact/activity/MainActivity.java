package com.contact.activity;

import java.util.List;

import com.contact.adapter.CategoryAdapter;
import com.contact.dbhelper.DBCategoryHandler;
import com.contact.entities.Category;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private EditText txtString; 

	GridView grid;

	//data source
	List<Category> categoryList ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.Initialization();		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.actionmenu, menu);		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int itemId = item.getItemId();
		if (itemId == R.id.action_refresh) {
			// refresh
			Toast.makeText(getApplicationContext(), "Under construction!!", Toast.LENGTH_LONG).show();
			return true;
		} else if (itemId == R.id.action_help) {
			// help action
			Toast.makeText(getApplicationContext(), "Under construction!!", Toast.LENGTH_LONG).show();
			return true;
		} else if (itemId == R.id.action_check_updates) {
			Toast.makeText(getApplicationContext(), "Under construction!!", Toast.LENGTH_LONG).show();
			// check for updates action
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
	
	// ////////////Private methods///////////////////
	private void Initialization(){
		txtString=(EditText)findViewById(R.id.txtString);	
		
		grid=(GridView)findViewById(R.id.gridviewCategory);	
		grid.setOnItemClickListener(onItemClickListener);
		this.LoadCategory();
	}

	private void LoadCategory(){
		
		DBCategoryHandler db=new DBCategoryHandler(this);
		categoryList=db.getAllCategory(); 
		if(categoryList!=null && categoryList.size()>0){			
			CategoryAdapter adapter = new CategoryAdapter(MainActivity.this,categoryList);	
			grid.setAdapter(adapter);
		}
	}
		
	////////////// Events///////////////////////////////
	
	OnItemClickListener onItemClickListener=  new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {			
			
			Category category=categoryList.get(position);
			
			//Toast.makeText(MainActivity.this, "You Clicked at " +category.getName(), Toast.LENGTH_SHORT).show();									
			Intent intent=new Intent(getBaseContext(),SubCategoryListActivity.class);
			intent.putExtra("CategoryId",String.valueOf(category.getId()));
			intent.putExtra("Name", category.getName());
			startActivity(intent);										
		}		
	};

	public void onClick(View view){
		switch (view.getId()) {

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
			/*	
		case R.id.btnInsert:
			Intent i=new Intent(getBaseContext(),InsertActivity.class);
			startActivity(i);
			 */
		default:
			break;
		}
	}



}
