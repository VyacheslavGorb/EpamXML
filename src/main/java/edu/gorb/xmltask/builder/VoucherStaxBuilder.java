package edu.gorb.xmltask.builder;

import edu.gorb.xmltask.entity.*;
import edu.gorb.xmltask.exception.VoucherException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class VoucherStaxBuilder extends AbstractVoucherBuilder {
    private final XMLInputFactory inputFactory;

    public VoucherStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }


    @Override
    public void buildVouchers(String filePath) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filePath))) {
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(VoucherTag.PILGRIMAGE_VOUCHER.toString())
                            || name.equals(VoucherTag.BEACH_VACATION_VOUCHER.toString())) {
                        AbstractVoucher voucher = buildVoucher(reader);
                        vouchers.add(voucher);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    private AbstractVoucher buildVoucher(XMLStreamReader reader) throws XMLStreamException {
        AbstractVoucher voucher = reader.getLocalName().equals(VoucherTag.PILGRIMAGE_VOUCHER.toString()) ?
                new PilgrimageVoucher() :
                new BeachVacationVoucher();
        voucher.setId(reader.getAttributeValue(null, VoucherTag.ID.toString()));
        voucher.setWebSite(
                Objects.requireNonNullElse(reader.getAttributeValue(
                        null, VoucherTag.WEB_SITE.toString()),
                        AbstractVoucher.DEFAULT_WEBSITE)
        );

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    String data = getXMLText(reader);
                    switch (VoucherTag.valueOf(name.toUpperCase().replace('-', '_'))) {
                        case COUNTRY -> voucher.setCountry(CountryType.valueOf(data.toUpperCase()));
                        case DEPARTURE_DATE_TIME -> voucher.setDeparture(LocalDateTime.parse(data));
                        case ARRIVAL_DATE_TIME -> voucher.setArrival(LocalDateTime.parse(data));
                        case TRANSPORT_TYPE -> voucher.setTransport(TransportType.valueOf(data.toUpperCase()));
                        case COST -> voucher.setCost(Integer.parseInt(data));
                        case DISTANCE_TO_BEACH -> {
                            BeachVacationVoucher tempVoucher = (BeachVacationVoucher) voucher;
                            tempVoucher.setDistanceToBeach(Integer.parseInt(data));
                        }
                        case PILGRIMAGE_PASSPORT_REQUIRED -> {
                            PilgrimageVoucher tempVoucher = (PilgrimageVoucher) voucher;
                            tempVoucher.setPilgrimagePassportRequired(Boolean.parseBoolean(data));
                        }
                        case HOTEL -> fillXMLHotel(reader, voucher.getHotel());
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals(VoucherTag.PILGRIMAGE_VOUCHER.toString())
                            || name.equals(VoucherTag.BEACH_VACATION_VOUCHER.toString())) {
                        return voucher;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <student>");
    }

    private void fillXMLHotel(XMLStreamReader reader, AbstractVoucher.Hotel hotel)
            throws XMLStreamException {
        int type;
        String name;

        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    String data = getXMLText(reader);
                    switch (VoucherTag.valueOf(name.toUpperCase().replace('-', '_'))) {
                        case STARS -> hotel.setStarsCount(Integer.parseInt(data));
                        case FOOD -> hotel.setFoodType(FoodType.valueOf(data.toUpperCase()));
                        case AIR_CONDITIONING -> hotel.setAirConditioningPresent(Boolean.parseBoolean(data));
                        case TV -> hotel.setTvPresent(Boolean.parseBoolean(data));
                        case PLACE_COUNT -> hotel.setPlaceCount(Integer.parseInt(data));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (VoucherTag.valueOf(name.toUpperCase().replace('-', '_')) == VoucherTag.HOTEL) {
                        return;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <address>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
