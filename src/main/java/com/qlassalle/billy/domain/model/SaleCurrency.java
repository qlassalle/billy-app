package com.qlassalle.billy.domain.model;

import java.util.Objects;

public class SaleCurrency {
    private String xtz;

    public SaleCurrency(String xtz) {
        this.xtz = xtz;
    }

    public SaleCurrency() {
    }

    public void setXtz(String xtz) {
        this.xtz = xtz;
    }

    public String getXtz() {
        return xtz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SaleCurrency that = (SaleCurrency) o;
        return Objects.equals(xtz, that.xtz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xtz);
    }
}
