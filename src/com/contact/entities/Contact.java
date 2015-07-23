package com.contact.entities;

public class Contact {
	
	//private variables
	int _id;
	String _name;
	String _contact;
	String _services;
	String _week_days;
	String _address;
	String _email;
	String _latitude;
	String _longitude;
	String _working_hour;
	int _category_id;

	// Empty constructor
	public Contact(){

	}
	
	// constructor
	public Contact(String name, String contact,String services,String week_days,String address,String email,String latitude, String longitude,String working_hour,int category_id){
		
		this._name = name;
		this._contact = contact;
		this._services=services;
		this._week_days=week_days;
		this._address=address;
		this._email=email;
		this._latitude=latitude;
		this._longitude=longitude;
		this._working_hour=working_hour;
		this._category_id=category_id;
	}

	// getting ID
	public int getID(){
		return this._id;
	}

	// setting id
	public void setID(int id){
		this._id = id;
	}

	// getting name
	public String getName(){
		return this._name;
	}

	// setting name
	public void setName(String name){
		this._name = name;
	}
	
	// getting phone number
	public String getPhoneNumber(){
		return this._contact;
	}

	// setting phone number
	public void setPhoneNumber(String phone_number){
		this._contact = phone_number;
	}
	
	// getting services
	public String getServices(){
		return this._services;
	}
	// setting services
	public void setServices(String services){
		this._services=services;
	}
	public String getWeekDays(){
		return this._week_days;
	}
	public void setWeekDays(String week_days){
		this._week_days=week_days;
	}
	public String getAddress(){
		return this._address;
	}
	public void setAddress(String address){
		this._address=address;
	}
	public String getLatitude(){
		return this._latitude;
	}
	public void setLatitude(String latitude){
		this._latitude=latitude;
	}
	public String getEmail(){
		return this._email;
	}
	public void setEmail(String email){
		this._email=email;
	}
	public String getLongitude(){
		return this._longitude;
	}
	public void setLongitude(String longitude){
		this._longitude=longitude;
	}
	public String getWorkingHour(){
		return this._working_hour;
	}
	public void setWorkingHour(String working_hour){
		this._working_hour=working_hour;
	}
	//getting category id
	public int getCategoryId(){
		return this._category_id;
	}
	//setting category id
	public void setCategoryId(int category_id){
		this._category_id=category_id;
	}
}
