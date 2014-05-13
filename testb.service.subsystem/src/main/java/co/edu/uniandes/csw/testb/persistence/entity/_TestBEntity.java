
package co.edu.uniandes.csw.testb.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _TestBEntity {

	@Id
	@GeneratedValue(generator = "TestB")
	private Long id;
	private String name;
	private String atr2;

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
	public String getAtr2(){
		return atr2;
	}
	
	public void setAtr2(String atr2){
		this.atr2 = atr2;
	}
}