package com.qlassalle.billy.domain.model.input;

import java.util.List;

public class UpdateEventRequest {
    private int id;
    private String title;
    private List<String> lineUp;
    private String mediaUrl;
    private SmartContractData smartContractData;

    public UpdateEventRequest() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLineUp() {
        return lineUp;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public SmartContractData getSmartContractData() {
        return smartContractData;
    }

    public static class SmartContractData {
        private int id;
        private String collectionName;

        public int getId() {
            return id;
        }

        public String getCollectionName() {
            return collectionName;
        }

        public SmartContractData() {}
    }
}
