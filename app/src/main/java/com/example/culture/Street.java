package com.example.culture;

public class Street {
  private final String street_title;
  private final String street_detail;
  private final int imageResource;

  public Street( String street_title, String street_detail, int imageResource) {
    this.street_title = street_title;
    this.street_detail = street_detail;
    this.imageResource = imageResource;
  }

  public String getTitle() {
    return street_title;
  }

  public String getDetail() { return street_detail; }

  public int getImageResource() {
    return imageResource;
  }
}
