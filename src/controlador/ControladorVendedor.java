/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author LATITUDE E6420
 */
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import modelo.ModeloVendedor;
import modelo.VendedorDAO;
import vista.FormLogin;
import vista.FormPrincipal;
import vista.FormRegVendedor;
import vista.FormRegVendedorConsulta;

/**
 *
 * @author LATITUDE E6420
 */
public class ControladorVendedor extends ControladorAbstraccion {

    FormLogin loginf = new FormLogin();
    ModeloVendedor modeloV = new ModeloVendedor();
    VendedorDAO daoV = new VendedorDAO();
    FormRegVendedor vendedorF = new FormRegVendedor();
    FormRegVendedorConsulta vendedorFC = new FormRegVendedorConsulta();
    private String respuesta, cadena = "";
    private int dato;
    private final int CONT = 1;

    public ControladorVendedor(FormLogin loginf) {
        this.loginf = loginf;
        this.loginf.btnLogin.addActionListener(this);
    }

    public ControladorVendedor(FormRegVendedor frv) {
        this.vendedorF = frv;
        this.vendedorF.btnGuardar.addActionListener(this);
        this.vendedorF.btnNuevo.addActionListener(this);
        this.vendedorF.btnCancelar.addActionListener(this);
        this.vendedorF.btnLimpiar.addActionListener(this);
        this.vendedorF.btnSalir.addActionListener(this);
        this.vendedorF.btnBuscar.addActionListener(this);
    }

    public ControladorVendedor(FormRegVendedorConsulta frvc) {
        this.vendedorFC = frvc;
    }

    // Programacion Login
    private void validarLogin() {
        String user = loginf.txtUser.getText();
        String pass = loginf.txtPass.getText();

        if (user.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Los campos están vacíos", "Error", 0);
            loginf.txtUser.requestFocus();
        } else {
            modeloV = daoV.loginVendedor(user, pass);
            if (modeloV.getUsua_ven() != null || modeloV.getCui_ven() != null) {
                FormPrincipal principal = new FormPrincipal();
                principal.setVisible(true);
                loginf.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Los credenciales no coinciden.", "Error", 0);
                loginf.txtUser.setText("");
                loginf.txtUser.requestFocus();
                loginf.txtPass.setText("");
            }
        }
    }

    // Programacion del FormRegVendedor() + salida
    private void salir() {
        this.vendedorF.dispose();
    }

    //Retornar la fecha de un JDateChooser
    private String getFecha(JDateChooser date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(date.getDate());
        return fecha;
    }

    // Programacion para Abrir el formulario FormRegVendedorConsulta()
    private void abrirFormConsulta() {
        FormRegVendedorConsulta formReg = new FormRegVendedorConsulta();
        ControladorVendedorConsulta control = new ControladorVendedorConsulta(formReg);
        centrarVentanas(formReg);
    }

    // Centrar las ventanas de un JInternalFrame()
    private void centrarVentanas(JInternalFrame frame) {
        FormPrincipal.desktopPane.add(frame);
        Dimension dimPrincipal = FormPrincipal.desktopPane.getSize();
        Dimension dimVentanas = frame.getSize();
        int x = (dimPrincipal.width - dimVentanas.width) / 2;
        int y = (dimPrincipal.height - dimVentanas.height) / 2;
        frame.setLocation(x, y);
        frame.show();
    }

    // Genera el ID del empleado
    private void getGenerarCodigo(int dato) {
        this.dato = dato;

        if ((this.dato >= 1000) || (this.dato < 10000)) {
            int can = CONT + this.dato;
            cadena = "" + can;
        }
        if ((this.dato >= 100) || (this.dato < 1000)) {
            int can = CONT + this.dato;
            cadena = "0" + can;
        }
        if ((this.dato >= 10) || (this.dato < 100)) {
            int can = CONT + this.dato;
            cadena = "00" + can;
        }
        if (this.dato < 10) {
            int can = CONT + this.dato;
            cadena = "000" + can;
        }
    }

    // Devuelve el serial del codigo 
    private String serie() {
        return this.cadena;
    }

    // Carga el codigo en los Frames
    private void cargarCodigo() {
        String c = daoV.buscarCodigo();
        int j;
        if (c == null) {
            vendedorF.txtCodigo.setText("VE0001");
        } else {
            char r1 = c.charAt(2);
            char r2 = c.charAt(3);
            char r3 = c.charAt(4);
            char r4 = c.charAt(5);
            String r = "";
            r = "" + r1 + r2 + r3 + r4;
            j = Integer.parseInt(r);

            getGenerarCodigo(j);
            vendedorF.txtCodigo.setText("VE" + serie());
        }
    }

    // Habilita, Deshabilita y limpia algunos botones y txts en el FormRegVendedor()
    private void habilitarTodoVendedorF() {
        vendedorF.txtNombre.requestFocus();
        vendedorF.btnGuardar.setEnabled(true);
        vendedorF.btnModificar.setEnabled(true);
        vendedorF.btnCancelar.setEnabled(true);
        vendedorF.btnNuevo.setEnabled(false);
        vendedorF.txtNombre.setEnabled(true);
        vendedorF.txtUsuario.setEnabled(true);
        vendedorF.txtApellido.setEnabled(true);
        vendedorF.txtDireccion.setEnabled(true);
        vendedorF.txtCui.setEnabled(true);
        vendedorF.txtTelefono.setEnabled(true);
        vendedorF.dateIngreso.setEnabled(true);
        vendedorF.txtEmail.setEnabled(true);
        vendedorF.txtSueldo.setEnabled(true);
        vendedorF.cbSexo.setEnabled(true);
        vendedorF.dateNacimiento.setEnabled(true);
        vendedorF.txtVentos.setEnabled(true);
        vendedorF.cbLabora.setEnabled(true);
        limpiarVendedorF();
    }

    private void deshabilitarTodoVendedorF() {
        vendedorF.btnGuardar.setEnabled(false);
        vendedorF.btnModificar.setEnabled(false);
        vendedorF.btnCancelar.setEnabled(false);
        vendedorF.btnNuevo.setEnabled(true);
        vendedorF.txtNombre.setEnabled(false);
        vendedorF.txtApellido.setEnabled(false);
        vendedorF.txtUsuario.setEnabled(false);
        vendedorF.txtDireccion.setEnabled(false);
        vendedorF.txtCui.setEnabled(false);
        vendedorF.txtTelefono.setEnabled(false);
        vendedorF.dateIngreso.setEnabled(false);
        vendedorF.txtEmail.setEnabled(false);
        vendedorF.txtSueldo.setEnabled(false);
        vendedorF.cbSexo.setEnabled(false);
        vendedorF.dateNacimiento.setEnabled(false);
        vendedorF.txtVentos.setEnabled(false);
        vendedorF.cbLabora.setEnabled(false);
        limpiarVendedorF();
    }

    private void limpiarVendedorF() {
        vendedorF.txtNombre.requestFocus();
        vendedorF.txtNombre.setText("");
        vendedorF.txtApellido.setText("");
        vendedorF.txtDireccion.setText("");
        vendedorF.txtUsuario.setText("");
        vendedorF.txtCui.setText("");
        vendedorF.txtTelefono.setText("");
        vendedorF.dateIngreso.setDate(null);
        vendedorF.txtEmail.setText("");
        vendedorF.txtSueldo.setText("");
        vendedorF.cbSexo.setSelectedIndex(0);
        vendedorF.dateNacimiento.setDate(null);
        vendedorF.txtVentos.setText("");
        vendedorF.cbLabora.setSelected(false);
    }
    int i = 0;

    // Registra al vendedor en FormRegVendedor
    private void guardarVendedor() {

        if (!vendedorF.txtNombre.getText().equals("")
                && !vendedorF.txtApellido.getText().equals("")
                && !vendedorF.txtUsuario.getText().equals("")
                && !vendedorF.txtCui.getText().equals("")
                && vendedorF.dateIngreso.getDate() != null) {

            modeloV.setId_ven(vendedorF.txtCodigo.getText());
            modeloV.setNom_ven(vendedorF.txtNombre.getText());
            modeloV.setApe_ven(vendedorF.txtApellido.getText());
            modeloV.setUsua_ven(vendedorF.txtUsuario.getText());
            modeloV.setDir_ven(vendedorF.txtDireccion.getText());
            modeloV.setCui_ven(vendedorF.txtCui.getText());
            modeloV.setTel_ven(vendedorF.txtTelefono.getText());
            if (vendedorF.cbSexo.getItemAt(1).equals("Masculino")) {
                modeloV.setSex_ven(vendedorF.cbSexo.getItemAt(1));
            } else if (vendedorF.cbSexo.getItemAt(2).equals("Femenino")) {
                modeloV.setSex_ven(vendedorF.cbSexo.getItemAt(2));
            } else {
                modeloV.setSex_ven("");
            }
            modeloV.setFec_ing(getFecha(vendedorF.dateIngreso));

            if (vendedorF.dateNacimiento.getDate() != null) {
                modeloV.setFec_nac(getFecha(vendedorF.dateNacimiento));
            } else {
                modeloV.setFec_nac(null);
            }

            if (!vendedorF.txtEmail.getText().isEmpty()) {
                if (vendedorF.txtEmail.getText().contains("@gmail.com") || vendedorF.txtEmail.getText().contains("@outlook.com")) {
                    modeloV.setEma_ven(vendedorF.txtEmail.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "El correo tiene un dominio no asociado.", "Error de Dominio", 0);
                }
            }

            if (!vendedorF.txtVentos.getText().equals("")) {
                modeloV.setVentas(Double.parseDouble(vendedorF.txtVentos.getText()));
            } else {
                modeloV.setVentas(0);
            }

            if (vendedorF.cbLabora.isSelected()) {
                modeloV.setLabora(1);
            } else if (!vendedorF.cbLabora.isSelected()) {
                modeloV.setLabora(0);
            } else {
                modeloV.setLabora(0);
            }

            if (!vendedorF.txtSueldo.getText().equals("")) {
                modeloV.setSueldo(Double.parseDouble(vendedorF.txtSueldo.getText()));
            } else {
                modeloV.setSueldo(0);
            }

            respuesta = daoV.insertarVendedor(modeloV);
            if (respuesta != null) {
                JOptionPane.showMessageDialog(null, respuesta, "Respuesta Vendedor", 1);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hay campos criticos vacíos o ingresados incorrectamente.", "Error de Campos", 0);
            this.limpiarVendedorF();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginf.btnLogin) {
            validarLogin();
        } else if (e.getSource() == vendedorF.btnGuardar) {
            guardarVendedor();
        } else if (e.getSource() == vendedorF.btnNuevo) {
            cargarCodigo();
            habilitarTodoVendedorF();
        } else if (e.getSource() == vendedorF.btnCancelar) {
            deshabilitarTodoVendedorF();
        } else if (e.getSource() == vendedorF.btnLimpiar) {
            limpiarVendedorF();
        } else if (e.getSource() == vendedorF.btnSalir) {
            salir();
        } else if (e.getSource() == vendedorF.btnBuscar) {
            abrirFormConsulta();
        }
    }
}
