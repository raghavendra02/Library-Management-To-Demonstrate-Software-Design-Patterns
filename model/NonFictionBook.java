package model;

import java.sql.Date;

public class NonFictionBook implements Book {
    private  Integer id;
    private  String title;
    private  String author;
    private  String year;
    private  boolean isAvailable;
    private  String type;

    public NonFictionBook(Integer id, String title, String author, String year, boolean isAvailable,
                          String type)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = isAvailable;
        this.type = type;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }
    @Override
    public String getType() {
        return type;
    }

}
