package edu.gorb.xmltask.main;

import edu.gorb.xmltask.exception.VoucherException;
import edu.gorb.xmltask.builder.AbstractVoucherBuilder;
import edu.gorb.xmltask.builder.BuilderType;
import edu.gorb.xmltask.builder.VoucherBuilderFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;

public class VoucherMain {
    private static final Logger logger = LogManager.getLogger();
    private static final String RELATIVE_PATH = "files/vouchers.xml";
    public static void main(String[] args) {
        VoucherBuilderFactory factory = new VoucherBuilderFactory();
        AbstractVoucherBuilder saxBuilder = factory.createBuilder(BuilderType.SAX);
        AbstractVoucherBuilder domBuilder = factory.createBuilder(BuilderType.DOM);
        try {
            String path = convertToAbsolutePath(RELATIVE_PATH);
            domBuilder.buildVouchers(path);
            saxBuilder.buildVouchers(path);
            logger.log(Level.INFO, "Built successfully");
        } catch (VoucherException e) {
            logger.log(Level.FATAL, e.getMessage());
        }
    }

    private static String convertToAbsolutePath(String relativePath) throws VoucherException {
        if (relativePath == null) {
            logger.log(Level.ERROR, "Relative path is null");
            throw new VoucherException("Relative path is null");
        }
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL resourcePath = classLoader.getResource(relativePath);
        if (resourcePath == null) {
            logger.log(Level.ERROR, "File does not exist");
            throw new VoucherException("File does not exist");
        }
        File file = new File(resourcePath.getFile());
        return file.getAbsolutePath();
    }
}
