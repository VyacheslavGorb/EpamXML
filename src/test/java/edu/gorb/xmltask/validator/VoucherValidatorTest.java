package edu.gorb.xmltask.validator;

import org.testng.annotations.Test;

import javax.xml.validation.Validator;
import java.io.File;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class VoucherValidatorTest {
    private VoucherValidator validator = new VoucherValidator();

    @Test
    public void isValidXmlFileTestValid() {
        File schemaFile = new File(getClass().getClassLoader().getResource("files/vouchersTest.xsd").getFile());
        File dataFile = new File(getClass().getClassLoader().getResource("files/vouchersTest.xml").getFile());
        assertTrue(validator.isValidXmlFile(dataFile.getAbsolutePath(), schemaFile.getAbsolutePath()));
    }

    @Test
    public void isValidXmlFileTestInvalid() {
        File schemaFile = new File(getClass().getClassLoader().getResource("files/vouchersTest.xsd").getFile());
        File dataFile = new File(getClass().getClassLoader().getResource("files/vouchersInvalidTest.xml").getFile());
        assertFalse(validator.isValidXmlFile(dataFile.getAbsolutePath(), schemaFile.getAbsolutePath()));
    }
}
