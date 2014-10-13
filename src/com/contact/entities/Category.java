package com.contact.entities;

public class Category {
	int _id;
	String _name;
	String _description;
	//constructor
	public Category(){
		
	}

	//constructor
	public Category(int id, String name, String desc){
		this._id=id;
		this._name=name;
		this._description=desc;
	}
	//constructor
	public Category(String name, String desc){		
		this._name=name;
		this._description=desc;
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
	//getting description
	public String getDescription(){
		return this._description;
	}
	//setting description
	public void setDescription(String desc){
		this._description=desc;
	}
}
