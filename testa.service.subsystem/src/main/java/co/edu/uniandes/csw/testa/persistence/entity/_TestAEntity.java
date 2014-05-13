
package co.edu.uniandes.csw.testa.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _TestAEntity {

	@Id
	@GeneratedValue(generator = "TestA")
	private Long id;
	private String name;
	private String atr1;

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
	public String getAtr1(){
		return atr1;
	}
	
	public void setAtr1(String atr1){
		this.atr1 = atr1;
	}
}