package DAO;

import Connections.Connect;
import Connections.MySQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase encargada de realizar la implementación de la interfaz con las
 * interacciones de los Productos y la base de datos.
 */
public class ProductDAOImp implements ProductDAO {

    private final Connect connect;

    /**
     * Creamos una conexión a la base de datos MySQL.
     */
    public ProductDAOImp() {
        connect = new MySQL();
    }

    @Override
    public boolean insert(Product product) {
        String query = "INSERT INTO PRODUCTS (NAME, DESCRIPTION, PRICE) VALUES (?, ? ,?)";
        PreparedStatement statement = null;
        try {
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getPrice());
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("ProductDAOImp - insert - error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM PRODUCTS WHERE ID = ?";
        PreparedStatement statement = null;
        try {
            statement = connect.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("ProductDAOImp - delete - error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        String query = "UPDATE PRODUCTS SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?";
        PreparedStatement statement = null;
        try {
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getId());
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("ProductDAOImp - update - error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM PRODUCTS WHERE ID = ?";
        PreparedStatement statement = null;
        try {
            statement = connect.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Product product = null;
            if (result.next()) {
                product = new Product();
                product.setId(result.getInt("ID"));
                product.setName(result.getString("NAME"));
                product.setDescription(result.getString("DESCRIPTION"));
                product.setPrice(result.getInt("PRICE"));
            }
            statement.close();
            return product;
        } catch (SQLException e) {
            System.out.println("ProductDAOImp - find - error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> all() {
        String query = "SELECT * FROM PRODUCTS";
        PreparedStatement statement = null;
        try {
            statement = connect.getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            List<Product> list = new LinkedList<>();
            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("ID"));
                product.setName(result.getString("NAME"));
                product.setDescription(result.getString("DESCRIPTION"));
                product.setPrice(result.getInt("PRICE"));
                list.add(product);
            }
            statement.close();
            return list;
        } catch (SQLException e) {
            System.out.println("ProductDAOImp - all - error: " + e.getMessage());
            return null;
        }
    }

}
