package by.bsu.ivanyukovich.entertainment.io;

import by.bsu.ivanyukovich.entertainment.criteria.*;
import by.bsu.ivanyukovich.entertainment.place.CompanionType;
import by.bsu.ivanyukovich.entertainment.place.WorkTimeLimited;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hope on 4/20/14.
 */
public class CriteriaReaderTest {
    @Test
    public void testGoodFile() throws IOException, InvalidCriteriaException {
        CriteriaReader criteriaReader = new CriteriaReader("src/test/resources/request1.csv");
        List<Criteria> criteriaList = criteriaReader.read();
        Assert.assertEquals(3, criteriaList.size());

        Assert.assertTrue(criteriaList.get(0) instanceof AgeLimitCriteria);
        AgeLimitCriteria ageLimitCriteria = (AgeLimitCriteria) criteriaList.get(0);
        Assert.assertEquals(0, ageLimitCriteria.getAgeLimit());

        Assert.assertTrue(criteriaList.get(1) instanceof CompanionsCriteria);
        CompanionsCriteria companionsCriteria = (CompanionsCriteria) criteriaList.get(1);
        Assert.assertArrayEquals(new CompanionType[] {CompanionType.CHILDREN}, companionsCriteria.getCompanions().toArray());

        Assert.assertTrue(criteriaList.get(2) instanceof WorkTimeLimitCriteria);
        WorkTimeLimitCriteria workTimeLimitCriteria = (WorkTimeLimitCriteria) criteriaList.get(2);
        Assert.assertEquals(12, workTimeLimitCriteria.getOpeningTime());
        Assert.assertEquals(14, workTimeLimitCriteria.getClosingTime());
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testUnknownCriteria() throws IOException, InvalidCriteriaException {
        CriteriaReader criteriaReader = new CriteriaReader("src/test/resources/request2.csv");
        criteriaReader.read();
        Assert.fail("No InvalidCriteriaException was thrown.");
    }

    @Test(expected = AssertionError.class)
    public void testEmptyFile() throws IOException, InvalidCriteriaException {
        CriteriaReader criteriaReader = new CriteriaReader("src/test/resources/request3.csv");
        criteriaReader.read();
        Assert.fail("No AssertionError was thrown.");
    }


}
