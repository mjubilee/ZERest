package com.ze.news;

public class Article {
    private String source;
    private String author;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private boolean isExtracted;

    public Article() {
        super();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public boolean isExtracted() {
        return isExtracted;
    }

    public void setExtracted(boolean extracted) {
        isExtracted = extracted;
    }
}
