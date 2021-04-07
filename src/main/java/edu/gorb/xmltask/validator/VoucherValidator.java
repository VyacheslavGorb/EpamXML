package edu.gorb.xmltask.validator;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class VoucherValidator {
    private static final Logger logger = LogManager.getLogger();

    public boolean isValidXmlFile(String filePath, String schemaPath){
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaPath);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(filePath);
            validator.validate(source);
            logger.log(Level.INFO,"File: {} is valid", filePath);
        } catch (SAXException e) {
            logger.log(Level.INFO,"File: {} is not valid; message: {}", filePath, e.getMessage());
            return false;
        } catch (IOException e) {
            logger.log(Level.INFO, "Error while reading file: {}", filePath);
            return false;
        }
        return true;
    }
}
