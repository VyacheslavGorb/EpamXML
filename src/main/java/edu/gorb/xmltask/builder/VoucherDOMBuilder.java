package edu.gorb.xmltask.builder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import edu.gorb.xmltask.entity.*;
import edu.gorb.xmltask.exception.VoucherException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class VoucherDOMBuilder extends AbstractVoucherBuilder {
    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public VoucherDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Configuration failed {}", e.getMessage());
        }
    }

    @Override
    public void buildVouchers(String filePath) throws VoucherException {
        Document doc;
        try {
            doc = docBuilder.parse(filePath);
            Element root = doc.getDocumentElement();
            NodeList voucherList = root.getElementsByTagName(VoucherTag.PILGRIMAGE_VOUCHER.toString());
            for (int i = 0; i < voucherList.getLength(); i++) {
                Element voucherElement = (Element) voucherList.item(i);
                AbstractVoucher voucher = buildVoucher(voucherElement);
                vouchers.add(voucher);
            }
            voucherList = root.getElementsByTagName(VoucherTag.BEACH_VACATION_VOUCHER.toString());
            for (int i = 0; i < voucherList.getLength(); i++) {
                Element voucherElement = (Element) voucherList.item(i);
                AbstractVoucher voucher = buildVoucher(voucherElement);
                vouchers.add(voucher);
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error occurred while reading file {}; message:  {}", filePath, e.getMessage());
            throw new VoucherException("Error occurred while reading file " + filePath + "; message:  " + e.getMessage());
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Error occurred while parsing file {}; message:  {}", filePath, e.getMessage());
            throw new VoucherException("Error occurred while parsing file " + filePath + "; message:  " + e.getMessage());
        }
        logger.log(Level.INFO, "Vouchers were successfully built. File: {}", filePath);
    }

    private AbstractVoucher buildVoucher(Element voucherElement) {
        AbstractVoucher voucher = voucherElement.getTagName().equals(VoucherTag.PILGRIMAGE_VOUCHER.toString()) ?
                new PilgrimageVoucher() :
                new BeachVacationVoucher();

        String websiteAttribute = voucherElement.getAttribute("web-site");
        voucher.setWebSite(Objects.requireNonNullElse(websiteAttribute, AbstractVoucher.DEFAULT_WEBSITE));

        voucher.setId(voucherElement.getAttribute("id"));
        String data = getElementTextContent(voucherElement, VoucherTag.COUNTRY.toString());
        voucher.setCountry(CountryType.valueOf(data.toUpperCase()));
        data = getElementTextContent(voucherElement, VoucherTag.DEPARTURE_DATE_TIME.toString());
        voucher.setDeparture(LocalDateTime.parse(data));
        data = getElementTextContent(voucherElement, VoucherTag.ARRIVAL_DATE_TIME.toString());
        voucher.setArrival(LocalDateTime.parse(data));
        data = getElementTextContent(voucherElement, VoucherTag.TRANSPORT_TYPE.toString());
        voucher.setTransport(TransportType.valueOf(data.toUpperCase()));
        data = getElementTextContent(voucherElement, VoucherTag.COST.toString());
        voucher.setCost(Integer.parseInt(data));

        if (voucher.getClass() == PilgrimageVoucher.class) {
            data = getElementTextContent(voucherElement, VoucherTag.PILGRIMAGE_PASSPORT_REQUIRED.toString());
            ((PilgrimageVoucher) voucher).setPilgrimagePassportRequired(Boolean.parseBoolean(data));
        } else {
            data = getElementTextContent(voucherElement, VoucherTag.DISTANCE_TO_BEACH.toString());
            ((BeachVacationVoucher) voucher).setDistanceToBeach(Integer.parseInt(data));
        }

        Hotel hotel = voucher.getHotel();
        Element hotelElement =
                (Element) voucherElement.getElementsByTagName(VoucherTag.HOTEL.toString()).item(0);

        data = getElementTextContent(hotelElement, VoucherTag.PLACE_COUNT.toString());
        hotel.setPlaceCount(Integer.parseInt(data));
        data = getElementTextContent(hotelElement, VoucherTag.STARS.toString());
        hotel.setStarsCount(Integer.parseInt(data));
        data = getElementTextContent(hotelElement, VoucherTag.FOOD.toString());
        hotel.setFoodType(FoodType.valueOf(data.toUpperCase(Locale.ROOT)));
        data = getElementTextContent(hotelElement, VoucherTag.AIR_CONDITIONING.toString());
        hotel.setAirConditioningPresent(Boolean.parseBoolean(data));
        data = getElementTextContent(hotelElement, VoucherTag.TV.toString());
        hotel.setTvPresent(Boolean.parseBoolean(data));
        return voucher;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}