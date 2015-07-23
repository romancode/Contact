package com.contact.entities;

public class SubCategory {
	int _id;
	String _name;
	int _categoryId;


	//constructor
	public SubCategory(){		
	}

	//constructor
	public SubCategory(int id, String name, int categoryId){
		this._id=id;
		this._name=name;
		this._categoryId=categoryId;		
	}
	//constructor
	public SubCategory(String name, int categoryId){		
		this._name=name;
		this._categoryId=categoryId;
	}
	//getting id
	public int getId(){
		return this._id;
	}
	//setting id
	public void setId(int id){
		this._id=id;
	}
	//getting category name
	public String getName(){
		return this._name;
	}
	//setting category name
	public void setName(String name){
		this._name=name;
	}	
	public int getCategoryId(){
		return this._categoryId;
	}
	public void setCategoryId(int categoryId){
		this._categoryId=categoryId;
	}
	
}
