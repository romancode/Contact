package com.contact.adapter;


import java.util.ArrayList;
import java.util.List;

import com.contact.activity.R;
import com.contact.entities.SubCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


public class SubCategoryListAdapter extends ArrayAdapter<SubCategory> implements Filterable {

	Context ctx;
	List<SubCategory> categorytList;
	private List<SubCategory> origCategorytList;
	private Filter categoryFilter;

	public SubCategoryListAdapter(Context context,List<SubCategory> categories ) {
		super(context, R.layout.subcategory_list_item,  categories);

		this.ctx=context;
		this.categorytList=categories;
		this.origCategorytList=categories;
	}

	public void resetData() {
		categorytList = origCategorytList;
	}
	public int getCount() {
		return categorytList.size();
	}

	public SubCategory getItem(int position) {
		return categorytList.get(position);
	}

	public long getItemId(int position) {
		return categorytList.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v=convertView;
		SubCategoryHolder holder=new SubCategoryHolder();

		if(convertView==null){
			LayoutInflater inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=inflater.inflate(R.layout.subcategory_list_item, null);

			TextView tvName=(TextView)v.findViewById(R.id.txt_scat_item_name);

			holder.nameView=tvName;			

			v.setTag(holder);		
		}
		else{
			holder=(SubCategoryHolder) v.getTag();
		}

		SubCategory category=this.categorytList.get(position);
		if(category.getName()!=null)
			holder.nameView.setText(category.getName());

		return v;

	}
	private static class SubCategoryHolder {
		public TextView nameView;		
	}


	@Override
	public Filter getFilter() {

		if(categoryFilter==null)
			categoryFilter=new CategoryFilter();

		return categoryFilter;
	}

	private class CategoryFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				results.values = origCategorytList;
				results.count = origCategorytList.size();
			}
			else {
				// We perform filtering operation
				List<SubCategory> nCategoryList = new ArrayList<SubCategory>();

				for (SubCategory p : categorytList) {
					if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
						nCategoryList.add(p);
				}

				results.values = nCategoryList;
				results.count = nCategoryList.size();
			}
			return results;			
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			// Now we have to inform the adapter about the new list filtered
			if (results.count == 0)
				notifyDataSetInvalidated();
			else {
				categorytList = (List<SubCategory>) results.values;
				notifyDataSetChanged();
			}			
		}
	}

}
