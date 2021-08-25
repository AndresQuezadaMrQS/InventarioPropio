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

    private FormLogin loginf = new FormLogin();
    protected ModeloVendedor modeloV = new ModeloVendedor();
    private final VendedorDAO daoV = new VendedorDAO();
    protected FormRegVendedor vendedorF = new FormRegVendedor();
    private FormRegVendedorConsulta vendedorFC = new FormRegVendedorConsulta();
    private String respuesta, cadena = "";
    private int dato;
    private final int CONT = 1;
    protected static String nombre = "", apellido = "", email = "";

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

    // Programacion Login | Validar Credenciales Login
    private void validarLogin() {
        String user = loginf.txtUser.getText();
        String pass = loginf.txtPass.getText();

        if (!user.isEmpty() && !pass.isEmpty() && !user.contains(" ") && !pass.contains(" ")) {
            modeloV = daoV.loginVendedor(user, pass);
            if (modeloV != null) {
                if (modeloV.getUsua_ven().equals(user) && modeloV.getCui_ven().equals(pass)) {
                    loginf.dispose();
                    FormPrincipal principalF = new FormPrincipal();
                    principalF.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales invalidas, intente de nuevo.", "Credenciales Invalidas", 0);
                limpiarLogin();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hay campos vacíos o campos invalidos.", "Campos Invalidos", 0);
            limpiarLogin();
        }
    }

    // Programacion del FormRegVendedor() + salida
    private void salir() {
        vendedorF.dispose();
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
        vendedorF.dispose();
    }

    // Centrar las ventanas de un JInternalFrame()
    protected void centrarVentanas(JInternalFrame frame) {
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

    // Limpiar Login
    private void limpiarLogin() {
        loginf.txtUser.requestFocus();
        loginf.txtUser.setText("");
        loginf.txtPass.setText("");
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
        //limpiarVendedorF();
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

    // Registra al vendedor en FormRegVendedor
    private void guardarVendedor() {

        if (!vendedorF.txtNombre.getText().isEmpty()
                && !vendedorF.txtApellido.getText().isEmpty()
                && !vendedorF.txtUsuario.getText().isEmpty()
                && !vendedorF.txtCui.getText().isEmpty()
                && vendedorF.dateIngreso.getDate() != null
                && !vendedorF.txtEmail.getText().isEmpty()) {

            modeloV.setId_ven(vendedorF.txtCodigo.getText());
            modeloV.setNom_ven(vendedorF.txtNombre.getText());
            modeloV.setApe_ven(vendedorF.txtApellido.getText());
            if (!vendedorF.txtUsuario.getText().contains(" ")) {
                modeloV.setUsua_ven(vendedorF.txtUsuario.getText());
            } else {
                JOptionPane.showMessageDialog(null, "No debe contener espacio el Usuario.");
            }

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

            if (vendedorF.txtEmail.getText().contains("@gmail.com") || vendedorF.txtEmail.getText().contains("@outlook.com")) {
                modeloV.setEma_ven(vendedorF.txtEmail.getText());
            } else {
                JOptionPane.showMessageDialog(null, "El correo tiene un dominio no asociado.", "Error de Dominio", 0);
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

            if (!vendedorF.txtSueldo.getText().isEmpty()) {
                modeloV.setSueldo(Double.parseDouble(vendedorF.txtSueldo.getText()));
            } else {
                modeloV.setSueldo(0);
            }

            respuesta = daoV.insertarVendedor(modeloV);
            if (respuesta != null) {
                JOptionPane.showMessageDialog(null, respuesta, "Respuesta Registro", 1);
                limpiarVendedorF();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hay campos criticos vacíos o ingresados incorrectamente.", "Error de Campos", 0);
            limpiarVendedorF();
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
