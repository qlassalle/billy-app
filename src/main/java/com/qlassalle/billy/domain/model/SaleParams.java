package com.qlassalle.billy.domain.model;


import java.util.List;
import java.util.Objects;

public class SaleParams {

    private boolean presale;
    private List<String> metadataList;
    private int pricePerToken;
    private int maxMintPerUser;
    private int saleSize;
    private SaleCurrency saleCurrency;
    private long startTime;
    private long endTime;

    public SaleParams(boolean presale, List<String> metadataList, int pricePerToken, int maxMintPerUser, int saleSize, SaleCurrency saleCurrency, long startTime, long endTime) {
        this.presale = presale;
        this.metadataList = metadataList;
        this.pricePerToken = pricePerToken;
        this.maxMintPerUser = maxMintPerUser;
        this.saleSize = saleSize;
        this.saleCurrency = saleCurrency;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SaleParams() {
    }

    public void setIsPresale(boolean presale) {
        this.presale = presale;
    }

    public boolean isPresale() {
        return presale;
    }

    public List<String> getMetadataList() {
        return metadataList;
    }

    public int getPricePerToken() {
        return pricePerToken;
    }

    public int getMaxMintPerUser() {
        return maxMintPerUser;
    }

    public int getSaleSize() {
        return saleSize;
    }

    public SaleCurrency getSaleCurrency() {
        return saleCurrency;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SaleParams that = (SaleParams) o;
        return presale == that.presale && pricePerToken == that.pricePerToken && maxMintPerUser == that.maxMintPerUser && saleSize == that.saleSize && startTime == that.startTime && endTime == that.endTime && Objects.equals(metadataList, that.metadataList) && Objects.equals(saleCurrency, that.saleCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presale, metadataList, pricePerToken, maxMintPerUser, saleSize, saleCurrency, startTime, endTime);
    }
}