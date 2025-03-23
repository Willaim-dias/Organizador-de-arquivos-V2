package model.entities;

public class Document {
    
    private Integer id;
    private String title;
    private String category;
    private String description;
    private byte[] file;
    
    public Document() {
    }

    public Document(Integer id, String title, String category, String description, byte[] file) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.file = file;
    }
    
    public Document(Integer id, String title, String category) {
        this.id = id;
        this.title = title;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    
}
