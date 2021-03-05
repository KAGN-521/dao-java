package Principal;

import Controllers.ProductController;

public class App {

    public static void main(String[] args) {
        ProductController app = new ProductController();
        app.init();
    }

}

// Autor: Kevin González Navarro
// Año:   2020
// Descripción: Implementación de un CRUD básico de Productos a la Base de Datos MySQL. Utilizando el patrón de diseño DAO y MVC + Observador.
//              El script de la Base de Datos se encuentra en el paquete DB. Y para realizar su configuración ingresar a Properties/MySQL.
