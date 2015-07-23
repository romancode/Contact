package com.contact.entities;

public class Category {
	int _id;
	String _name;
	String _image;


	//constructor
	public Category(){		
	}

	//constructor
	public Category(int id, String name, String image){
		this._id=id;
		this._name=name;
		this._image=image;
	}
	//constructor
	public Category(String name, String image){		
		this._name=name;
		this._image=image;
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
	public String getImage(){
		return this._image;
	}
	public void setImage(String image){
		this._image=image;
	}
	
}
