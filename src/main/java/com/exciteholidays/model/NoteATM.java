package com.exciteholidays.model;

public class NoteATM implements Comparable<NoteATM>, Cloneable {

	//the name would be display.
	private String name;
	
	//value of this note
	private Integer value;	

	private int numberAvailable = 0;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberAvailable() {
		return numberAvailable;
	}
	public void setNumberAvailable(int numberAvailable) {
		this.numberAvailable = numberAvailable;
	}
	
	@Override
	public int compareTo(NoteATM note1) {
		int compareValue = ((NoteATM) note1).getValue(); 
		return this.value - compareValue;
	}

    @Override
    public Object clone() {
        Object o = null;
        try {
          o = super.clone();
        } catch(CloneNotSupportedException e) {
        	//SILENT
        }
        return o;
     }
	
}
