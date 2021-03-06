package com.example.usermobile.Storage;

public class Product {
    private static int idCodeGen = 100;
    private String name;
    private int quantity;
    private String unitOfMeasure;
    private String expirationDate;
    private String category;
    private String packages;
    private String idCode;
    private String photoLink;


    public Product() {

    }

    /**
     * @param name
     * @param quantity
     * @param expirationDate
     * @param category
     * @param packages
     */
    public Product(final String name, final int quantity, final String unitOfMeasure, final String expirationDate, final String category, final String packages, final String photoLink) {
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.expirationDate = expirationDate;
        this.category = category;
        this.photoLink = photoLink;
        this.packages = packages;
        this.idCode = getUniqueProductId();
    }

    public Product(final String name, final int quantity, final String unitOfMeasure, final String expirationDate, final String category, final String packages, final String photoLink, final String idCode) {
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.expirationDate = expirationDate;
        this.category = category;
        this.packages = packages;
        this.photoLink = photoLink;
        this.idCode = idCode;
    }

    private String getUniqueProductId() {
        idCodeGen++;
        return Integer.toString(idCodeGen);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public boolean equals(Object other) {
        if(other == this) {
            return true;
        }

        if(other instanceof Product) {
            Product that = (Product) other;
            return (this.name.equals(that.name) && this.name.equals(that.name) && this.category.equals(that.category) && this.getUnitOfMeasure().equals(that.getUnitOfMeasure()));
        }

        return false;
    }
}
