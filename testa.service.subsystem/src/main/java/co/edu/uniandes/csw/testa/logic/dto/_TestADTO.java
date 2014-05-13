
package co.edu.uniandes.csw.testa.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _TestADTO {

	private Long id;
	private String name;
	private String atr1;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public String getAtr1() {
		return atr1;
	}
 
	public void setAtr1(String atr1) {
		this.atr1 = atr1;
	}
	
}