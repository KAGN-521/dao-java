package Controllers;

import Models.ProductModel;
import Views.Add;
import Views.Edit;
import Views.Index;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 * Clase encargada de gestionar las vistas Index, Add, Edit del programa.
 */
public class ProductController implements ActionListener, MouseListener {

    private ProductModel model;
    private Index index;
    private Add add;
    private Edit edit;
    private String rowSelected;

    public ProductController() {
        /**
         * Iniciamos el modelo de productos.
         */
        model = new ProductModel();

        /**
         * Iniciamos todas las vistas.
         */
        index = new Index(this, model);
        add = new Add(this);
        edit = new Edit(this);

        /**
         * Iniciamos variables de ayuda.
         */
        rowSelected = null;
        index.updateProducts();
    }

    /**
     * Método que inicia la pantalla inicial.
     */
    public void init() {
        index.open();
    }

    /**
     * Método que revisa si la tabla ha sido seleccionada.
     *
     * @return boolean True = Seleccionado. / False = No Seleccionado.
     */
    public boolean checkSelection() {
        if (rowSelected == null) {
            JOptionPane.showMessageDialog(null, "¡Debe seleccionar una fila!", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Método que limpia la selección de la tabla.
     */
    public void clearSelection() {
        rowSelected = null;
        index.getProducts().clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * Cuando presionamos el botón de Refrescar del Index.
         */
        if (e.getSource() == index.getRefresh()) {
            index.updateProducts();
        }

        /**
         * Cuando presionamos el botón de Editar del Index.
         */
        if (e.getSource() == index.getEdit()) {
            if (checkSelection()) {
                edit.setProduct(model.find(rowSelected));
                edit.open();
            }
        }

        /**
         * Cuando presionamos el botón de Eliminar del Index.
         */
        if (e.getSource() == index.getDelete()) {
            if (checkSelection()) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el producto?", "Eliminar Producto", JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    if (model.delete(rowSelected)) {
                        JOptionPane.showMessageDialog(null, "¡Producto eliminado éxitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Error al eliminar el producto!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                clearSelection();
            }
        }

        /**
         * Cuando presionamos el botón de Agregar del Index.
         */
        if (e.getSource() == index.getAdd()) {
            add.open();
        }

        /**
         * Cuando presionamos el botón de Cancelar del Add.
         */
        if (e.getSource() == add.getCancel()) {
            add.close();
        }

        /**
         * Cuando presionamos el botón de Agregar del Add.
         */
        if (e.getSource() == add.getAdd()) {
            if (add.check()) {
                if (model.insert(add.getProduct())) {
                    add.close();
                    JOptionPane.showMessageDialog(null, "¡Producto agregado éxitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "¡Error al agregar el producto!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        /**
         * Cuando presionamos el botón de Cancelar del Edit.
         */
        if (e.getSource() == edit.getCancel()) {
            edit.close();
        }

        /**
         * Cuando presionamos el botón de Editar del Edit.
         */
        if (e.getSource() == edit.getEdit()) {
            if (checkSelection()) {
                if (edit.check()) {
                    edit.updateChanges();
                    if (model.edit(edit.getProduct())) {
                        edit.close();
                        JOptionPane.showMessageDialog(null, "¡Producto editado éxitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        clearSelection();
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Error al editar el producto!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /**
         * Caso cuando presionamos la Tabla del Index.
         */
        if (e.getSource() == index.getProducts()) {
            int row = index.getProducts().getSelectedRow();
            rowSelected = index.getProducts().getValueAt(row, 0).toString();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
