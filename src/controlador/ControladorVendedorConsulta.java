/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloVendedor;
import modelo.VendedorDAO;
import vista.FormRegVendedor;
import vista.FormRegVendedorConsulta;

/**
 *
 * @author LATITUDE E6420
 */
public class ControladorVendedorConsulta extends ControladorVendedor {

    private FormRegVendedorConsulta formVC = new FormRegVendedorConsulta();
    private final VendedorDAO dao = new VendedorDAO();
    private ModeloVendedor modelo = new ModeloVendedor();

    public ControladorVendedorConsulta(FormRegVendedorConsulta frvc) {
        super(frvc);
        formVC = frvc;
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
            for (int j = 0; j < numRegistros; j++) {
                obj[0] = dao.consultarVendedor(valor, buscar).get(j).getNom_ven();
                obj[1] = dao.consultarVendedor(valor, buscar).get(j).getApe_ven();
                obj[2] = dao.consultarVendedor(valor, buscar).get(j).getEma_ven();
                miModel.addRow(obj);
            }
        } else {
            limpiarConsulta();
        }
    }

    // LLevar todos los datos del usuario que seleccione a la parte del FormRegVendedor()
    private void ladoAOtro(JTable tabla) {
        //vendedor.txtNombre.setText("asdads");
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Desea que este VENDEDOR se muetre en la otra pantalla?", "Consulta", JOptionPane.YES_NO_OPTION);
        int fila = tabla.getSelectedRow();
        if (pregunta == 0) {
            String nombre = tabla.getValueAt(fila, 0).toString();
            String apellido = tabla.getValueAt(fila, 1).toString();
            String email = tabla.getValueAt(fila, 2).toString();
            if (!nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty()) {
                ControladorVendedor.nombre = nombre;
                ControladorVendedor.apellido = apellido;
                ControladorVendedor.email = email;

                modelo = dao.guardarVendedor(nombre, apellido, email);
                if (modelo != null) {
                    FormRegVendedor form = new FormRegVendedor();
                    ControladorVendedor control = new ControladorVendedor(form);
                    centrarVentanas(form);
                    form.txtNombre.setText(modelo.getNom_ven());
                    form.txtApellido.setText(modelo.getApe_ven());
                    form.txtDireccion.setText(modelo.getDir_ven());
                    form.txtUsuario.setText(modelo.getUsua_ven());
                    form.txtCui.setText(modelo.getCui_ven());
                    form.txtTelefono.setText(modelo.getTel_ven());
                    form.dateIngreso.setDate(Date.valueOf(modelo.getFec_ing()));
                    form.txtEmail.setText(modelo.getEma_ven());
                    form.txtSueldo.setText(String.valueOf(modelo.getSueldo()));
                    switch (modelo.getSex_ven().toLowerCase()) {
                        case "masculino":
                            form.cbSexo.setSelectedIndex(1);
                            break;
                        case "femenino":
                            form.cbSexo.setSelectedIndex(2);
                            break;
                        default:
                            form.cbSexo.setSelectedIndex(0);
                            break;
                    }
                    if (modelo.getFec_nac() != null) {
                        form.dateNacimiento.setDate(Date.valueOf(modelo.getFec_nac()));
                    } else {
                        form.dateNacimiento.setDate(null);
                    }
                    
                    form.txtVentos.setText(String.valueOf(modelo.getVentas()));
                    switch (modelo.getLabora()) {
                        case 0:
                            form.cbLabora.setSelected(false);
                            break;
                        case 1:
                            form.cbLabora.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    formVC.dispose();
                }
            }
        } else {
            limpiarConsulta();
        }
        /*
        RECORDATORIO || TENGO QUE HACER QUE AL SELECCIONAR UN VENDEDOR,
        SE TIENE QUE COLOCAR SUS DATOS EN EL FORMREGVENDEDOR() *Terminar YA
         */

        // Complicacion no me deja settear el texto en el otro FORM - REPARAR*
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
