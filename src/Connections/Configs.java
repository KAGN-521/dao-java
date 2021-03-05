package Connections;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class Configs {

    private String from = null;

    /**
     * Configuración del paquete donde se encuentra el archivo.
     *
     * @param from String Nombre del paquete donde está el archivo.
     * @return Configs Objeto configs.
     */
    public Configs from(String from) {
        this.from = from;
        return this;
    }

    /**
     * Método que retorna un HashMap con la información almacenada en el
     * archivo.
     *
     * @param file String Nombre del archivo.
     * @return HashMap<String, String> Todas las configuraciones almacenadas en
     * dicho paquete.
     */
    public HashMap file(String file) {
        if (from != null) {
            try (InputStream input = this.getClass().getResourceAsStream("/" + from + "/" + file + ".properties")) {
                Properties properties = new Properties();

                if (input == null) {
                    from = null;
                    return null;
                }

                properties.load(input);
                HashMap<String, String> response = new HashMap<>();

                Iterator<Object> keys = properties.keySet().iterator();
                while (keys.hasNext()) {
                    Object key = keys.next();
                    response.put(key.toString(), properties.getProperty(key.toString()));
                }

                from = null;
                return response;
            } catch (Exception e) {
                System.out.println("Configs - form - error: " + e.getMessage());
                from = null;
                return null;
            }
        }
        return null;
    }

}
