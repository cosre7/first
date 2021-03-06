package com.cosre7.pms.domain;

import java.sql.Date;

public class Board {
  private int no;
  private String name;
  private String category;
  private String title;
  private String content;
  private Date registeredDate;
  private int likeCount;

  public Board() {}

  public Board(String csv) {
    String[] fields = csv.split(",");
    this.setNo(Integer.parseInt(fields[0]));
    this.setName(fields[1]);
    this.setCategory(fields[2]);
    this.setTitle(fields[3]);
    this.setContent(fields[4]);
    this.setRegisteredDate(Date.valueOf(fields[5]));
    this.setLikeCount(Integer.parseInt(fields[6]));
  }

  @Override
  public String toString() {
    return "Board [no=" + no + ", name=" + name + ", category=" + category + ", title=" + title
        + ", content=" + content + ", registeredDate=" + registeredDate + ", likeCount=" + likeCount
        + "]";
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%d\n", 
        this.getNo(),
        this.getName(),
        this.getCategory(),
        this.getTitle(),
        this.getContent(),
        this.getRegisteredDate(),
        this.getLikeCount());
  }

  public static Board valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Board board = new Board();
    board.setNo(Integer.parseInt(fields[0]));
    board.setName(fields[1]);
    board.setCategory(fields[2]);
    board.setTitle(fields[3]);
    board.setContent(fields[4]);
    board.setRegisteredDate(Date.valueOf(fields[5]));
    board.setLikeCount(Integer.parseInt(fields[6]));
    return board;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + no;
    result = prime * result + ((registeredDate == null) ? 0 : registeredDate.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Board other = (Board) obj;
    if (category == null) {
      if (other.category != null)
        return false;
    } else if (!category.equals(other.category))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (no != other.no)
      return false;
    if (registeredDate == null) {
      if (other.registeredDate != null)
        return false;
    } else if (!registeredDate.equals(other.registeredDate))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public int getLikeCount() {
    return likeCount;
  }
  public void setLikeCount(int likeCount) {
    this.likeCount = likeCount;
  }

  public String getBoardLabel(String category) {
    switch (category) {
      case "1":
        return "추천 식재료";
      case "2":
        return "추천 레시피";
      default:
        return "추천 외식메뉴";
    }
  }
}
