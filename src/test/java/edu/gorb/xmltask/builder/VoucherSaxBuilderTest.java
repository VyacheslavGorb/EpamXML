package edu.gorb.xmltask.builder;

import edu.gorb.xmltask.entity.AbstractVoucher;
import edu.gorb.xmltask.entity.CountryType;
import edu.gorb.xmltask.entity.PilgrimageVoucher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

public class VoucherSaxBuilderTest {
    private AbstractVoucherBuilder builder;

    @BeforeClass
    public void createBuilder(){
        builder = new VoucherSaxBuilder();
    }

    @Test
    public void testBuildVouchers(){

    }

    @DataProvider
    public Object[][] createXmlSetData(){
        return new Object[][]{
                {"src/main/java/resources/files/vouchers.xml",""
                }
        };
    }

}
