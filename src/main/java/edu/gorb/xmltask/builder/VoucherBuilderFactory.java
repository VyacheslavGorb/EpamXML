package edu.gorb.xmltask.builder;

import edu.gorb.xmltask.exception.VoucherException;

public class VoucherBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private VoucherBuilderFactory() {
    }

    public static AbstractVoucherBuilder createBuilder(String typeParser) throws VoucherException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM -> {
                return new VoucherDOMBuilder();
            }
            case STAX -> {
                return new VoucherStaxBuilder();
            }
            case SAX -> {
                return new VoucherSaxBuilder();
            }
            default -> {
                throw new VoucherException(String.format("No such constant (%s)", typeParser));
            }
        }
    }
}
