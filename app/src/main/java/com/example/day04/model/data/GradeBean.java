package com.example.day04.model.data;

import java.io.Serializable;

public class GradeBean implements Serializable {

    /**
     * success : true
     * data : {"city":"北京市","city_code":"110000","station":"东四","station_code":"1003A","level":"","pollutions":"颗粒物(PM10)","quality":"良","longitude":"116.434","latitude":"39.9522","pubtime":"2020-11-23 19:00:00","AQI":"60.0","SO2":"2.0","NO2":"50.0","CO":"0.4","O3":"13.0","PM2_5":"28.0","PM10":"69.0"}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * city : 北京市
         * city_code : 110000
         * station : 东四
         * station_code : 1003A
         * level :
         * pollutions : 颗粒物(PM10)
         * quality : 良
         * longitude : 116.434
         * latitude : 39.9522
         * pubtime : 2020-11-23 19:00:00
         * AQI : 60.0
         * SO2 : 2.0
         * NO2 : 50.0
         * CO : 0.4
         * O3 : 13.0
         * PM2_5 : 28.0
         * PM10 : 69.0
         */

        private String city;
        private String city_code;
        private String station;
        private String station_code;
        private String level;
        private String pollutions;
        private String quality;
        private String longitude;
        private String latitude;
        private String pubtime;
        private String AQI;
        private String SO2;
        private String NO2;
        private String CO;
        private String O3;
        private String PM2_5;
        private String PM10;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getStation() {
            return station;
        }

        public void setStation(String station) {
            this.station = station;
        }

        public String getStation_code() {
            return station_code;
        }

        public void setStation_code(String station_code) {
            this.station_code = station_code;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getPollutions() {
            return pollutions;
        }

        public void setPollutions(String pollutions) {
            this.pollutions = pollutions;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getPubtime() {
            return pubtime;
        }

        public void setPubtime(String pubtime) {
            this.pubtime = pubtime;
        }

        public String getAQI() {
            return AQI;
        }

        public void setAQI(String AQI) {
            this.AQI = AQI;
        }

        public String getSO2() {
            return SO2;
        }

        public void setSO2(String SO2) {
            this.SO2 = SO2;
        }

        public String getNO2() {
            return NO2;
        }

        public void setNO2(String NO2) {
            this.NO2 = NO2;
        }

        public String getCO() {
            return CO;
        }

        public void setCO(String CO) {
            this.CO = CO;
        }

        public String getO3() {
            return O3;
        }

        public void setO3(String O3) {
            this.O3 = O3;
        }

        public String getPM2_5() {
            return PM2_5;
        }

        public void setPM2_5(String PM2_5) {
            this.PM2_5 = PM2_5;
        }

        public String getPM10() {
            return PM10;
        }

        public void setPM10(String PM10) {
            this.PM10 = PM10;
        }
    }
}
