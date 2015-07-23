package com.contact.activity;


import java.util.List;

import com.contact.adapter.SubCategoryListAdapter;
import com.contact.common.CommonMethod;
import com.contact.dbhelper.DBSubCategoryHandler;
import com.contact.entities.SubCategory;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;

public class SubCategoryListActivity extends Activity {
	//view object
	ListView lvScategories;

	//data source
	List<SubCategory> subCategories;

	//adapter object
	SubCategoryListAdapter adapter;	

	EditText txtSubCatFilter;

	boolean isLoading=false; 

	String titleStringId = null, titleString=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_category_list);

		ActionBar actionBar = getActionBar();

		// Enabling Up / Back navigation
		actionBar.setDisplayHomeAsUpEnabled(true);

		lvScategories=(ListView)findViewById(R.id.lvSubCategorylist);
		txtSubCatFilter=(EditText)findViewById(R.id.txtSubCatFilterSearch);
		this.LoadData();			
	}	

	private void LoadData(){

		CommonMethod.titleStringId=getIntent().getStringExtra("CategoryId");           
		CommonMethod.titleString=getIntent().getStringExtra("Name");


		this.setTitle(CommonMethod.titleString);
		// data source
		DBSubCategoryHandler db= new DBSubCategoryHandler(this);
		subCategories =db.getAllSubCategory(Integer.parseInt(CommonMethod.titleStringId));
		if(subCategories!=null && subCategories.size()>0)
		{
			// adapter		                
			adapter = new SubCategoryListAdapter(this,subCategories);
			lvScategories.setAdapter(adapter);  	
		}
		lvScategories.setOnItemClickListener(onItemClickListener);
		lvScategories.setTextFilterEnabled(true);

		if(adapter!=null && adapter.getCount()>0)
			txtSubCatFilter.addTextChangedListener(textSubCatWatcher) ;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sub_category_list, menu);
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
			NavUtils.navigateUpFromSameTask(this);
			//			onBackPressed();
		default:
			return super.onOptionsItemSelected(item);
		}
	}


	TextWatcher textSubCatWatcher=new TextWatcher(){

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
				SubCategoryListActivity.this.adapter.resetData();
			}

			SubCategoryListActivity.this.adapter.getFilter().filter(s.toString());
		}
	};

	/////// Events////////////////
	OnItemClickListener onItemClickListener=  new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long item) {
			// Code for go to Contact list Activity///

			SubCategory subCat=subCategories.get(position);

			//Toast.makeText(SubCategoryListActivity.this, "You Clicked at " +subCat.getName(), Toast.LENGTH_SHORT).show();			

			Intent intent=new Intent(getBaseContext(),ContactListActivity.class);
			intent.putExtra("SubCategoryId",String.valueOf(subCat.getId()));
			intent.putExtra("Name", subCat.getName());
			startActivity(intent);										
		}
	};
}
