package com.bullimore.usertestapp.models;

public class User {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String ip_address;
    private Double latitude;
    private Double longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    private User(Builder builder){
        this.id = builder.id;
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.email = builder.email;
        this.ip_address = builder.email;
        this.ip_address = builder.ip_address;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public static class Builder {
        private Integer id;
        private String first_name;
        private String last_name;
        private String email;
        private String ip_address;
        private Double latitude;
        private Double longitude;

        public User build(){
            return new User(this);
        }

        public Builder id(final Integer id){
            this.id = id;
            return this;
        }

        public Builder first_name(final String first_name){
            this.first_name = first_name;
            return this;
        }

        public Builder last_name(final String last_name){
            this.last_name = last_name;
            return this;
        }

        public Builder email(final String email){
            this.email = email;
            return this;
        }

        public Builder ip_address(final String ip_Address){
            this.ip_address = ip_Address;
            return this;
        }

        public Builder latitude(final Double latitude){
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(final Double longitude){
            this.longitude = longitude;
            return this;
        }

    }
}
