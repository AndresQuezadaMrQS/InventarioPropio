/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloVendedor;
import modelo.VendedorDAO;
import vista.FormRegVendedorConsulta;

/**
 *
 * @author LATITUDE E6420
 */
public class ControladorVendedorConsulta extends ControladorVendedor {

    FormRegVendedorConsulta formVC = new FormRegVendedorConsulta();
    ModeloVendedor modelo = new ModeloVendedor();
    VendedorDAO dao = new VendedorDAO();

    public ControladorVendedorConsulta(FormRegVendedorConsulta frvc) {
        super(frvc);
        this.formVC = frvc;
        formVC.btnCancelarConsulta.addActionListener(this);
        formVC.txtBuscarVendedor.addKeyListener(this);
        formVC.jrbUsuario.addActionListener(this);
        formVC.jrbNombre.addActionListener(this);
        formVC.jrbFec_ing.addActionListener(this);
        formVC.TablaVendedores.addMouseListener(this);
    }

    // Consulta del Vendedor con una Tabla
    private void consultarVendedorTabla(JTable tabla) {
        String buscar = formVC.txtBuscarVendedor.getText();
        String valor = "";
        boolean formJRB1 = formVC.jrbUsuario.isSelected();
        boolean formJRB2 = formVC.jrbNombre.isSelected();
        boolean formJRB3 = formVC.jrbFec_ing.isSelected();

        if (!buscar.equals("") && !buscar.equals("-") && !buscar.equals(" ")) {
            if (formJRB1) {
                valor = "usua_ven";
            } else if (formJRB2) {
                valor = "nom_ven";
            } else if (formJRB3) {
                valor = "fec_ing";
            } else {
                cerrarPrograma();
            }

            DefaultTableModel miModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int i, int columnas) {
                    return columnas == 0 && columnas == 1 && columnas == 2;
                }
            };
            tabla.setModel(miModel);
            miModel.addColumn("Nombre(s)");
            miModel.addColumn("Apellido(s)");
            miModel.addColumn("Email");
            Object[] obj = new Object[3];
            int numRegistros = dao.consultarVendedor(valor, buscar).size();
            for (int i = 0; i < numRegistros; i++) {
                obj[0] = dao.consultarVendedor(valor, buscar).get(i).getNom_ven();
                obj[1] = dao.consultarVendedor(valor, buscar).get(i).getApe_ven();
                obj[2] = dao.consultarVendedor(valor, buscar).get(i).getEma_ven();
                miModel.addRow(obj);
            }
        } else {
            limpiarConsulta();
        }
    }

    // LLevar todos los datos del usuario que seleccione a la parte del FormRegVendedor()
    private void ladoAOtro(JTable tabla) {
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Desea que este VENDEDOR se muetre en la otra pantalla?", "Consulta", JOptionPane.YES_NO_OPTION);

        if (pregunta == 0) {
            String nombre = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            String apellido = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
            String email = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
            modelo = dao.guardarVendedor(nombre, apellido, email);
            vendedorF.txtNombre.setText("adadasdasdas");
            System.out.println(modelo.getNom_ven());
            //this.vendedorFC.dispose();
        } else {
            limpiarConsulta();
        }
 /*
        RECORDATORIO || TENGO QUE HACER QUE AL SELECCIONAR UN VENDEDOR,
        SE TIENE QUE COLOCAR SUS DATOS EN EL FORMREGVENDEDOR() *Terminar YA
         */
 
 // Complicacion no me deja settear el texto en el otro FORM REPARAR*
    }

    // Cerrar el programa si hay algún problema critico
    private void cerrarPrograma() {
        Object[] opciones = {"Ok"};
        int eleccion = JOptionPane.showOptionDialog(null, "Fatal error, reinicie el programa.", "Fatal Error",
                JOptionPane.YES_OPTION,
                JOptionPane.ERROR_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Limpia toda ls consulta del formulario
    private void limpiarConsulta() {
        formVC.txtBuscarVendedor.setText("");
        formVC.txtBuscarVendedor.requestFocus();
        DefaultTableModel model = (DefaultTableModel) formVC.TablaVendedores.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    // Implemntaciones de los métodos abstractos
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == formVC.txtBuscarVendedor) {
            consultarVendedorTabla(formVC.TablaVendedores);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formVC.jrbUsuario || e.getSource() == formVC.jrbNombre || e.getSource() == formVC.jrbFec_ing) {
            consultarVendedorTabla(formVC.TablaVendedores);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            if (e.getSource() == formVC.TablaVendedores) {
                ladoAOtro(formVC.TablaVendedores);
            }
        }
    }

}
