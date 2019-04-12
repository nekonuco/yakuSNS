package com.example.shota.unluckysns.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable {


  /** ユーザーID */
  private String userId;

  private String emailAddress;

  private String password;

  private int age;

  private String yaku;

  private String nickname;

  private String imageUrl;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getYaku() {
    return yaku;
  }

  public void setYaku(String yaku) {
    this.yaku = yaku;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

}
