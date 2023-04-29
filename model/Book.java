package model;

import java.sql.Date;

public interface Book {
    Integer getId();

    String getTitle();

    String getAuthor();

    String getYear();

    boolean isAvailable();

    String getType();
}
