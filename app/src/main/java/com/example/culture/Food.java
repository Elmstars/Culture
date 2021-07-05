package com.example.culture;

public class Food {
  private final String title;
  private final String info;
  private final String detail;
  private final String imageResource;

  public Food( String title, String info, String detail, String imageResource) {
    this.title = title;
    this.info = info;
    this.detail = detail;
    this.imageResource = imageResource;
  }

  public String getTitle() {
    return title;
  }

  public String getInfo() {
    return info;
  }

  public String getDetail() { return detail; }

  public String getImageResource() {
    return imageResource;
  }
}
