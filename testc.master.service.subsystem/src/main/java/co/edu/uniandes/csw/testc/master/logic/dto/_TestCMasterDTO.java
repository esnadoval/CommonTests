package co.edu.uniandes.csw.testc.master.logic.dto;

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public abstract class _TestCMasterDTO {

 
    @XmlAttribute(name = "testcEntity")
    protected TestCDTO testcEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public TestCDTO getTestCEntity() {
        return testcEntity;
    }

    public void setTestCEntity(TestCDTO testcEntity) {
        this.testcEntity = testcEntity;
    }
    
    public List<TestBDTO> createTestB;
    public List<TestBDTO> updateTestB;
    public List<TestBDTO> deleteTestB;
    public List<TestBDTO> listTestB;	
	
	
	
    public List<TestBDTO> getCreateTestB(){ return createTestB; };
    public void setCreateTestB(List<TestBDTO> createTestB){ this.createTestB=createTestB; };
    public List<TestBDTO> getUpdateTestB(){ return updateTestB; };
    public void setUpdateTestB(List<TestBDTO> updateTestB){ this.updateTestB=updateTestB; };
    public List<TestBDTO> getDeleteTestB(){ return deleteTestB; };
    public void setDeleteTestB(List<TestBDTO> deleteTestB){ this.deleteTestB=deleteTestB; };
    public List<TestBDTO> getListTestB(){ return listTestB; };
    public void setListTestB(List<TestBDTO> listTestB){ this.listTestB=listTestB; };	
	
	
}

