package edu.gorb.xmltask.builder;

import edu.gorb.xmltask.entity.AbstractVoucher;
import edu.gorb.xmltask.exception.VoucherException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractVoucherBuilder {
    protected Set<AbstractVoucher> vouchers;

    protected AbstractVoucherBuilder() {
        vouchers = new HashSet<>();
    }

    public Set<AbstractVoucher> getVouchers() {
        return vouchers;
    }

    public abstract void buildVouchers(String filePath) throws VoucherException;
}
