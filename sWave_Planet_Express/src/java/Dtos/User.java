package Dtos;

import java.util.Objects;

/**
 *
 * @author Phillix
 */
public class User {
    private int     userId;
    private String  email;
    private String  password;
    private String  username;
    private String  fname;
    private String  lname;
    private String  add1;
    private String  add2;
    private String  city;
    private String  county;
    private String  skin;
    private boolean isAdmin;

    public User() {
        email    = "email";
        password = "password";
        username = "username";
        fname    = "fname";
        lname    = "lname";
        add1     = "add1";
        add2     = "add2";
        city     = "city";
        county   = "county";
        skin     = "flat";
        isAdmin  = false;
    }

    public User(String email, String password, String username, String fname, String lname, String add1, String add2, String city, String county, String skin, boolean isAdmin) {
        this.email    = email;
        this.password = password;
        this.username = username;
        this.fname    = fname;
        this.lname    = lname;
        this.add1     = add1;
        this.add2     = add2;
        this.city     = city;
        this.county   = county;
        this.skin     = skin;
        this.isAdmin  = isAdmin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
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

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", email=" + email + ", password=" + password + ", username=" + username + ", fname=" + fname + ", lname=" + lname + ", add1=" + add1 + ", add2=" + add2 + ", city=" + city + ", county=" + county + ", skin=" + skin + ", isAdmin=" + isAdmin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)  return true;
        if (obj  == null) return false;
        final User other = (User) obj;
        if (getClass()  != obj.getClass())            return false;
        if (!Objects.equals(this.email, other.email)) return false;
        return Objects.equals(this.username, other.username);
    }
}
