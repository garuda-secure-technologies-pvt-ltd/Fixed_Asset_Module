/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.customers;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;

/**
 *
 * @author swathi//
 */
public class CustomerInfoExt2 extends CustomerInfoExt {
    protected String firstname;
    protected String lastname;
    protected String email;
    protected String phone;
    protected String phone2;
    protected String fax;
    protected String address;
    protected String address2;
    protected String postal;
    protected String city;
    protected String region;
    protected String country;
    protected BufferedImage pic;
    protected BufferedImage sign;
    protected String fingerprdate;
    protected Timestamp terdate;
    protected String sponsor1;
    protected String sponsor2;
    protected String sponsor3;

    public CustomerInfoExt2(String id){
      super(id);
    }
     public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
      public void setImage(BufferedImage pic)
    {
        this.pic=pic;
    }
    public BufferedImage getImage()
    {
        return pic;
    }
    public void setSign(BufferedImage pic)
    {
        this.sign=pic;
    }
    public BufferedImage getSign()
    {
        return sign;
    }
     public String getFingerPrintData(){
     return fingerprdate;
    }
    public void setFingerPrintData(String fdata){
     fingerprdate=fdata;
    }
     public Timestamp gettDate(){
     return terdate;
    }
    public void settDate(Timestamp date){
      terdate=date;
    }
     public String getSponsor1(){
     return sponsor1;
    }
    public void setSponsor1(String name){
     sponsor1=name;
    }
    public String getSponsor2(){
     return sponsor2;
    }
    public void setSponsor2(String name){
     sponsor2=name;
    }
    public String getSponsor3(){
     return sponsor3;
    }
    public void setSponsor3(String name){
     sponsor3=name;
    }
}
