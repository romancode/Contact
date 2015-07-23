package com.contact.adapter;


import java.util.List;

import com.contact.activity.R;
import com.contact.common.CommonMethod;
import com.contact.entities.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
	
	private Context mContext;	
	List<Category> categorytList;
	
	public CategoryAdapter(Context c,List<Category> categories ) {
		mContext = c;		
		this.categorytList = categories;
	}
	@Override
	public int getCount() {		
		return this.categorytList.size();
	}
	@Override
	public Object getItem(int position) {
		return null;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View grid;
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			grid = new View(mContext);
			grid = inflater.inflate(R.layout.category_grid_item, null);
			TextView textView = (TextView) grid.findViewById(R.id.grid_text);
			ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
			
			Category category=this.categorytList.get(position);		
			
			textView.setText(category.getName());
			imageView.setImageResource(CommonMethod.getImageId(this.mContext, category.getImage()));			
			
		} else {
			grid = (View) convertView;
		}
		return grid;
	}	
}
