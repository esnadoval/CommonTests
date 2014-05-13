
package co.edu.uniandes.csw.testc.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _TestCEntity {

	@Id
	@GeneratedValue(generator = "TestC")
	private Long id;
	private String name;
	private String atr3;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getAtr3(){
		return atr3;
	}
	
	public void setAtr3(String atr3){
		this.atr3 = atr3;
	}
}