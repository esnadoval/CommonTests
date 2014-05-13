package co.edu.uniandes.csw.testa.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class TestATestBEntityId implements Serializable{

    private Long testaId;
    private Long testBId;

    @Override
    public int hashCode() {
        return (int) (testaId + testBId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof TestATestBEntityId) {
            TestATestBEntityId otherId = (TestATestBEntityId) object;
            return (otherId.testaId == this.testaId) && (otherId.testBId == this.testBId);
        }
        return false;
    }

}
