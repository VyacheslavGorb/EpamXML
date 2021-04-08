package edu.gorb.xmltask.builder;

public class VoucherBuilderFactory {
    public AbstractVoucherBuilder createBuilder(BuilderType type) {
        AbstractVoucherBuilder builder = null;
        switch (type) {
            case SAX -> builder = new VoucherSaxBuilder();
            case DOM -> builder = new VoucherDOMBuilder();
            case STAX -> builder = new VoucherStaxBuilder();
        }
        return builder;
    }
}
