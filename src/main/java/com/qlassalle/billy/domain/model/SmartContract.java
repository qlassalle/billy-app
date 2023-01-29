package com.qlassalle.billy.domain.model;

import java.util.Objects;

public class SmartContract {

    private String crowdsale;
    private String collection;
    private String multisig;
    private SaleParams saleParams;

    public SmartContract() {
    }

    public SmartContract(String crowdsale, String collection, String multisig, SaleParams saleParams) {
        this.crowdsale = crowdsale;
        this.collection = collection;
        this.multisig = multisig;
        this.saleParams = saleParams;
    }

    public void setCrowdsale(String crowdsale) {
        this.crowdsale = crowdsale;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setMultisig(String multisig) {
        this.multisig = multisig;
    }

    public void setSaleParams(SaleParams saleParams) {
        this.saleParams = saleParams;
    }

    public String getCrowdsale() {
        return crowdsale;
    }

    public String getCollection() {
        return collection;
    }

    public String getMultisig() {
        return multisig;
    }

    public SaleParams getSaleParams() {
        return saleParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmartContract that = (SmartContract) o;
        return Objects.equals(crowdsale, that.crowdsale) && Objects.equals(collection, that.collection) && Objects.equals(multisig, that.multisig) && Objects.equals(saleParams, that.saleParams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crowdsale, collection, multisig, saleParams);
    }
}
