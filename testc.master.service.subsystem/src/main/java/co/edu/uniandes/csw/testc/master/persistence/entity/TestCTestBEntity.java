package co.edu.uniandes.csw.testc.master.persistence.entity;

import co.edu.uniandes.csw.testb.persistence.entity.TestBEntity;
import co.edu.uniandes.csw.testc.persistence.entity.TestCEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(TestCTestBEntityId.class)
@NamedQueries({
    @NamedQuery(name = "TestCTestBEntity.getTestBListForTestC", query = "SELECT  u FROM TestCTestBEntity u WHERE u.testcId=:testcId"),
    @NamedQuery(name = "TestCTestBEntity.deleteTestCTestB", query = "DELETE FROM TestCTestBEntity u WHERE u.testBId=:testBId and  u.testcId=:testcId")
})
public class TestCTestBEntity implements Serializable {

    @Id
    @Column(name = "testcId")
    private Long testcId;
    @Id
    @Column(name = "testBId")
    private Long testBId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "testBId", referencedColumnName = "id")
    @JoinFetch
    private TestBEntity testBEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "testcId", referencedColumnName = "id")
    @JoinFetch
    private TestCEntity testcEntity;

    public TestCTestBEntity() {
    }

    public TestCTestBEntity(Long testcId, Long testBId) {
        this.testcId = testcId;
        this.testBId = testBId;
    }

    public Long getTestCId() {
        return testcId;
    }

    public void setTestCId(Long testcId) {
        this.testcId = testcId;
    }

    public Long getTestBId() {
        return testBId;
    }

    public void setTestBId(Long testBId) {
        this.testBId = testBId;
    }

    public TestBEntity getTestBEntity() {
        return testBEntity;
    }

    public void setTestBEntity(TestBEntity testBEntity) {
        this.testBEntity = testBEntity;
    }

    public TestCEntity getTestCEntity() {
        return testcEntity;
    }

    public void setTestCEntity(TestCEntity testcEntity) {
        this.testcEntity = testcEntity;
    }

}
