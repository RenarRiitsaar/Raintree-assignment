package raintree.testAssignment.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InsuranceTest {

    @Test
    void isValidDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
        Insurance insurance = new Insurance();
        insurance.setFromDate(sdf.parse("07-25-24"));
        insurance.setToDate(sdf.parse("07-25-24"));

       assertTrue(insurance.isValidDate("07-25-24" ));
       assertFalse(insurance.isValidDate("07-24-24"));
       assertFalse(insurance.isValidDate("07-26-24"));
    }
}