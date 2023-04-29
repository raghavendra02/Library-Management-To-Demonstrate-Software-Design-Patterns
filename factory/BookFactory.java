package factory;

import model.*;

public class BookFactory {
    public static Book createBook(Integer id, String title, String author, String year, boolean isAvailable, String type) {
        switch (type) {
            case "Fiction":
                return new FictionBook(id, title, author, year, isAvailable, type);
            case "Nonfiction":
                return new NonFictionBook(id, title, author, year, isAvailable, type);
            case "Reference":
                return new ReferenceBook(id, title, author, year, isAvailable, type);
            case "Textbook":
                return new TextBook(id, title, author, year, isAvailable, type);
            default:
                return null;
        }
    }

    public static class BookBuilder {
        private Integer id;
        private String title;
        private String author;
        private String year;
        private boolean available;
        private String type;

        public BookBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public BookBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder setYear(String year) {
            this.year = year;
            return this;
        }

        public BookBuilder setAvailable(boolean available) {
            this.available = available;
            return this;
        }

        public BookBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public Book build() {
            return BookFactory.createBook(id, title, author, year, available, type);
        }
    }
}



