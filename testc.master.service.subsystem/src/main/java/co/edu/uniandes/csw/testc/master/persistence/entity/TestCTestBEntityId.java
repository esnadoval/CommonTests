package co.edu.uniandes.csw.testc.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class TestCTestBEntityId implements Serializable{

    private Long testcId;
    private Long testBId;

    @Override
    public int hashCode() {
        return (int) (testcId + testBId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof TestCTestBEntityId) {
            TestCTestBEntityId otherId = (TestCTestBEntityId) object;
            return (otherId.testcId == this.testcId) && (otherId.testBId == this.testBId);
        }
        return false;
    }

}
