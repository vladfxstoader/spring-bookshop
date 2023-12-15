package com.example.bookshop.model;

import jakarta.persistence.*;

@Table
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String street;
    @Column
    private String building;
    @Column(name = "building_no")
    private Integer buildingNo;
    @Column
    private String city;
    @Column
    private String county;
    @OneToOne(mappedBy = "address")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(Integer buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
