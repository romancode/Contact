package com.example.contact;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


public class CustomizedArrayAdapter extends ArrayAdapter<Contact> implements Filterable {

	Context ctx;
	List<Contact> contactList;
	private List<Contact> origContactList;
	private Filter contactFilter;

	public CustomizedArrayAdapter(Context context,  List<Contact> contacts) {
		super(context, R.layout.contact_list_item,  contacts);

		this.ctx=context;
		this.contactList=contacts;
		this.origContactList=contacts;
	}
	public void resetData() {
		contactList = origContactList;
	}
	public int getCount() {
		return contactList.size();
	}

	public Contact getItem(int position) {
		return contactList.get(position);
	}

	public long getItemId(int position) {
		return contactList.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v=convertView;
		ContactHolder holder=new ContactHolder();
		
		if(convertView==null){

			//generate a view and return
			LayoutInflater inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=inflater.inflate(R.layout.contact_list_item, null);

			TextView tvName=(TextView)v.findViewById(R.id.txt_item_name);
			TextView tvPhone=(TextView)v.findViewById(R.id.txt_item_phone);
			
			holder.nameView=tvName;
			holder.phoneView=tvPhone;
			
			v.setTag(holder);		
		}
		else{
			holder=(ContactHolder) v.getTag();
		}
		
		Contact contact=this.contactList.get(position);						
		holder.nameView.setText(contact.getName());
		holder.phoneView.setText(contact.getPhoneNumber());

		return v;

	}
	private static class ContactHolder {
		public TextView nameView;
		public TextView phoneView;
		
	}


	@Override
	public Filter getFilter() {

		if(contactFilter==null)
			contactFilter=new ContactFilter();

		return contactFilter;
	}

	private class ContactFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				results.values = origContactList;
				results.count = origContactList.size();
			}
			else {
				// We perform filtering operation
				List<Contact> nContactList = new ArrayList<Contact>();

				for (Contact p : contactList) {
					if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
						nContactList.add(p);
				}

				results.values = nContactList;
				results.count = nContactList.size();

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
				contactList = (List<Contact>) results.values;
				notifyDataSetChanged();
			}			

		}

	}	
}





