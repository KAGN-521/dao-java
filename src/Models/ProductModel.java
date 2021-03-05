package Models;

import DAO.Product;
import DAO.ProductDAOImp;
import java.util.List;
import java.util.Observable;

/**
 * Clase encargada de la lógica relacionada con los Productos.
 */
public class ProductModel extends Observable {

    private ProductDAOImp builder;

    public ProductModel() {
        builder = new ProductDAOImp();
    }

    /**
     * Método para insertar una nuevo producto a la base de datos.
     *
     * @param product Producto que vamos a ingresar.
     * @return boolean Respuesta de la base de datos.
     */
    public boolean insert(Product product) {
        boolean response = builder.insert(product);
        setChanged();
        notifyObservers();
        return response;
    }

    /**
     * Método para editar un producto de la base de datos.
     *
     * @param product Producto que vamos a editar.
     * @return boolean Respuesta de la base de datos.
     */
    public boolean edit(Product product) {
        boolean response = builder.update(product);
        setChanged();
        notifyObservers();
        return response;
    }

    /**
     * Método para eliminar un producto de la base de datos.
     *
     * @param id String Id del producto a eliminar.
     * @return boolean Respuesta de la base de datos.
     */
    public boolean delete(String id) {
        boolean response = builder.delete(Integer.parseInt(id));
        setChanged();
        notifyObservers();
        return response;
    }

    /**
     * Método que retorna una lista con todos los productos.
     *
     * @return List<Product> Lista con todos los productos.
     */
    public List<Product> all() {
        return builder.all();
    }

    /**
     * Método que retorna un producto por id.
     *
     * @param id String Id del producto a buscar.
     * @return Product Producto encontrado.
     */
    public Product find(String id) {
        return builder.find(Integer.parseInt(id));
    }

}
