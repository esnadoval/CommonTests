
package co.edu.uniandes.csw.testc.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _TestCDTO {

	private Long id;
	private String name;
	private String atr3;

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
	public String getAtr3() {
		return atr3;
	}
 
	public void setAtr3(String atr3) {
		this.atr3 = atr3;
	}
	
}