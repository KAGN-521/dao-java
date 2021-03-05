package DAO;

/**
 * Clase con los mismos atributos que tiene un Producto de la base de datos.
 */
public class Product {

    private String name;
    private int price;
    private String description;
    private int id;

    public Product() {
        this.name = null;
        this.price = 0;
        this.description = null;
        this.id = 0;
    }

    public Product(String name, int price, String description, int id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public void setId(int id) {
        this.id = id;
    }
}
