package co.edu.uniandes.csw.testa.master.persistence.entity;

import co.edu.uniandes.csw.testb.persistence.entity.TestBEntity;
import co.edu.uniandes.csw.testa.persistence.entity.TestAEntity;
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
@IdClass(TestATestBEntityId.class)
@NamedQueries({
    @NamedQuery(name = "TestATestBEntity.getTestBListForTestA", query = "SELECT  u FROM TestATestBEntity u WHERE u.testaId=:testaId"),
    @NamedQuery(name = "TestATestBEntity.deleteTestATestB", query = "DELETE FROM TestATestBEntity u WHERE u.testBId=:testBId and  u.testaId=:testaId")
})
public class TestATestBEntity implements Serializable {

    @Id
    @Column(name = "testaId")
    private Long testaId;
    @Id
    @Column(name = "testBId")
    private Long testBId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "testBId", referencedColumnName = "id")
    @JoinFetch
    private TestBEntity testBEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "testaId", referencedColumnName = "id")
    @JoinFetch
    private TestAEntity testaEntity;

    public TestATestBEntity() {
    }

    public TestATestBEntity(Long testaId, Long testBId) {
        this.testaId = testaId;
        this.testBId = testBId;
    }

    public Long getTestAId() {
        return testaId;
    }

    public void setTestAId(Long testaId) {
        this.testaId = testaId;
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

    public TestAEntity getTestAEntity() {
        return testaEntity;
    }

    public void setTestAEntity(TestAEntity testaEntity) {
        this.testaEntity = testaEntity;
    }

}
