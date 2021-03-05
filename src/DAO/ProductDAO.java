package DAO;

import java.util.List;

/**
 * Interfaz con todas las interacciones con la base de datos que se pueden
 * realizar con un producto.
 */
public interface ProductDAO {

    /**
     * Inserción de un nuevo producto.
     *
     * @param product Producto que queremos ingresar.
     * @return boolean Respuesta de la base de datos.
     */
    public boolean insert(Product product);

    /**
     * Eliminación de un producto por id.
     *
     * @param id Id del producto a eliminar.
     * @return boolean Respuesta de la base de datos.
     */
    public boolean delete(int id);

    /**
     * Actualización de un producto.
     *
     * @param product Producto que queremos actualizar.
     * @return boolean Respuesta de la base de datos.
     */
    public boolean update(Product product);

    /**
     * Busqueda de un producto en la base de datos.
     *
     * @param id Id del producto a buscar.
     * @return Product Producto encontrado.
     */
    public Product find(int id);

    /**
     * Busqueda de todos los registros de la base de datos.
     *
     * @return List<Product> Lista con todos los productos.
     */
    public List<Product> all();
}
