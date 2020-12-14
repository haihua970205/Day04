package com.example.day04.model.tongpao;

import java.io.Serializable;
import java.util.List;

public class TPRobeBean implements Serializable{

    /**
     * status : {"statusCode":100,"message":"请求成功！","serverTime":"2020-08-04 16:39:04"}
     * data : [{"id":232041,"userID":232041,"nickName":"若寒rock","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200402/b071d2d80a96ea0cfeb391f4fb58ca30.jpg","age":29,"sex":"","province":"浙江","city":"杭州","level":2,"isConcat":0,"fileBeanList":[],"socialTitle":"粉丝人人爱"},{"id":102725,"userID":102725,"nickName":"南吕","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20181124/615f9a606619e9f58877d0fe30f34690.jpg","age":0,"sex":"1","province":"湖北","city":"武汉","level":1,"isConcat":0,"fileBeanList":[],"socialTitle":"土豪会撒币"},{"id":182301,"userID":182301,"nickName":"八荒原创汉服-玥","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200501/5c59f34728106bb1d703c25546c104b1.jpg","age":0,"sex":"1","province":"北京","city":"北京","level":3,"isConcat":0,"fileBeanList":[],"socialTitle":"粉丝人人爱"},{"id":106697,"userID":106697,"nickName":"为止汉服社-维蔚","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20190116/72eb63b7c9d1b9b44c0367f5a0f29aea.jpg","age":0,"sex":"1","province":"重庆市","city":"重庆","level":4,"isConcat":0,"fileBeanList":[],"socialTitle":"土豪会撒币"},{"id":103709,"userID":103709,"nickName":"终是庄周梦了蝶","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20181130/6bbdc43ea13ca0e17b02d6f074c7f1e6.jpg","age":0,"sex":"0","province":"四川","city":"成都","level":5,"isConcat":0,"fileBeanList":[],"socialTitle":"土豪会撒币"},{"id":107479,"userID":107479,"nickName":"染柒","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20191219/eadab196db21a4bb8e998330cd0bb561.jpg","age":0,"sex":"1","province":"陕西","city":"西安","level":4,"isConcat":0,"fileBeanList":[],"socialTitle":"级高带我飞"},{"id":106685,"userID":106685,"nickName":"小郎君。","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20190102/15d9c634a9c6a2b9e4ed29af43305e7b.jpg","age":0,"sex":"0","province":"重庆","city":"重庆","level":4,"isConcat":0,"fileBeanList":[],"socialTitle":"级高带我飞"},{"id":102233,"userID":102233,"nickName":"虔章司伯言","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20200417/921aa64685f0ea182779e61f3c116f1b.jpg","age":0,"sex":"0","province":"江西","city":"赣州","level":5,"isConcat":0,"fileBeanList":[],"socialTitle":"粉丝人人爱"},{"id":102437,"userID":102437,"nickName":"陆陆陆染","headUrl":"https://tpcdn.whfpsoft.com:443/File/headPhoto/20181121/4d113d63d4a6959e5f363ce927740ba1.jpg","age":0,"sex":"1","province":"广东","city":"江门","level":4,"isConcat":0,"fileBeanList":[],"socialTitle":"级高带我飞"}]
     */

    private StatusBean status;
    private List<DataBean> data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class StatusBean implements Serializable{
        /**
         * statusCode : 100
         * message : 请求成功！
         * serverTime : 2020-08-04 16:39:04
         */

        private int statusCode;
        private String message;
        private String serverTime;

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getServerTime() {
            return serverTime;
        }

        public void setServerTime(String serverTime) {
            this.serverTime = serverTime;
        }
    }

    public static class DataBean implements Serializable {
        /**
         * id : 232041
         * userID : 232041
         * nickName : 若寒rock
         * headUrl : https://tpcdn.whfpsoft.com:443/File/headPhoto/20200402/b071d2d80a96ea0cfeb391f4fb58ca30.jpg
         * age : 29
         * sex :
         * province : 浙江
         * city : 杭州
         * level : 2
         * isConcat : 0
         * fileBeanList : []
         * socialTitle : 粉丝人人爱
         */

        private int id;
        private int userID;
        private String nickName;
        private String headUrl;
        private int age;
        private String sex;
        private String province;
        private String city;
        private int level;
        private int isConcat;
        private String socialTitle;
        private List<?> fileBeanList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getIsConcat() {
            return isConcat;
        }

        public void setIsConcat(int isConcat) {
            this.isConcat = isConcat;
        }

        public String getSocialTitle() {
            return socialTitle;
        }

        public void setSocialTitle(String socialTitle) {
            this.socialTitle = socialTitle;
        }

        public List<?> getFileBeanList() {
            return fileBeanList;
        }

        public void setFileBeanList(List<?> fileBeanList) {
            this.fileBeanList = fileBeanList;
        }
    }
}
