
package co.edu.uniandes.csw.testb.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _TestBDTO {

	private Long id;
	private String name;
	private String atr2;

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
	public String getAtr2() {
		return atr2;
	}
 
	public void setAtr2(String atr2) {
		this.atr2 = atr2;
	}
	
}