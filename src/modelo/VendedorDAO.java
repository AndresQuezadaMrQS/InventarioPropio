/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LATITUDE E6420
 */
public class VendedorDAO {

    private Connection miConexion;
    private PreparedStatement PS;
    private ResultSet RS;
    private String respuesta, sql;
    private ModeloVendedor modelo = null;
    private final Conexion conexionActual = new Conexion();

    // ControladorVendedor
    public ModeloVendedor loginVendedor(String user, String cui) {
        try {
            miConexion = conexionActual.conectarConexion();
            sql = "SELECT cui_ven, usua_ven FROM vendedores WHERE usua_ven = ? and cui_ven = ?";

            PS = miConexion.prepareStatement(sql);
            PS.setString(1, user);
            PS.setString(2, cui);
            RS = PS.executeQuery();

            while (RS.next()) {
                modelo = new ModeloVendedor();
                modelo.setUsua_ven(RS.getString("usua_ven"));
                modelo.setCui_ven(RS.getString("cui_ven"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            miConexion = conexionActual.cerrarConexion();
        }
        return modelo;
    }

    // ControladorVendedor
    public ModeloVendedor guardarVendedor(String nombre, String apellido, String email) {

        try {
            miConexion = this.conexionActual.conectarConexion();
            sql = "SELECT * FROM vendedores WHERE nom_ven = ? AND ape_ven = ? AND ema_ven = ?;";
            PS = miConexion.prepareStatement(sql);

            PS.setString(1, nombre);
            PS.setString(2, apellido);
            PS.setString(3, email);
            RS = PS.executeQuery();

            while (RS.next()) {
                modelo = new ModeloVendedor();
                modelo.setId_ven(RS.getString("id_ven"));
                modelo.setNom_ven(RS.getString("nom_ven"));
                modelo.setApe_ven(RS.getString("ape_ven"));
                modelo.setDir_ven(RS.getString("dir_ven"));
                modelo.setUsua_ven(RS.getString("usua_ven"));
                modelo.setCui_ven(RS.getString("cui_ven"));
                modelo.setTel_ven(RS.getString("tel_ven"));
                modelo.setSex_ven(RS.getString("sex_ven"));
                modelo.setFec_ing(RS.getString("fec_ing"));
                modelo.setFec_nac(RS.getString("fec_nac"));
                modelo.setEma_ven(RS.getString("ema_ven"));
                modelo.setVentas(RS.getDouble("ventas"));
                modelo.setLabora(RS.getInt("labora"));
                modelo.setSueldo(RS.getDouble("sueldo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            modelo = null;
        } finally {
            miConexion = this.conexionActual.cerrarConexion();
        }
        return modelo;
    }

    public String insertarVendedor(ModeloVendedor mv) {
        try {
            miConexion = this.conexionActual.conectarConexion();
            sql = "INSERT INTO vendedores(id_ven, nom_ven, ape_ven, dir_ven, usua_ven, cui_ven, tel_ven, sex_ven, fec_ing, fec_nac, ema_ven, ventas, labora, sueldo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PS = miConexion.prepareStatement(sql);

            PS.setString(1, mv.getId_ven());
            PS.setString(2, mv.getNom_ven());
            PS.setString(3, mv.getApe_ven());
            PS.setString(4, mv.getDir_ven());
            PS.setString(5, mv.getUsua_ven());
            PS.setString(6, mv.getCui_ven());
            PS.setString(7, mv.getTel_ven());
            PS.setString(8, mv.getSex_ven());
            PS.setString(9, mv.getFec_ing());
            PS.setString(10, mv.getFec_nac());
            PS.setString(11, mv.getEma_ven());
            PS.setDouble(12, mv.getVentas());
            PS.setInt(13, mv.getLabora());
            PS.setDouble(14, mv.getSueldo());
            PS.executeUpdate();

            respuesta = "Vendedor correctamente registrado.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            respuesta = "El vendedor no fue registrado.";
        } finally {
            miConexion = this.conexionActual.cerrarConexion();
        }
        return respuesta;
    }

    // ControladorVendedor
    public String buscarCodigo() {
        String codigo = "";
        try {
            miConexion = this.conexionActual.conectarConexion();
            sql = "select max(id_ven) from vendedores";

            PS = miConexion.prepareStatement(sql);
            RS = PS.executeQuery();

            if (RS.next()) {
                codigo = RS.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            respuesta = "El vendedor no fue registrado.";
        } finally {
            miConexion = this.conexionActual.cerrarConexion();
        }
        return codigo;
    }

    // ControladorVendedorConsulta
    public ArrayList<ModeloVendedor> consultarVendedor(String valor, String campo) {
        ArrayList<ModeloVendedor> lista = null;
        try {
            miConexion = this.conexionActual.conectarConexion();
            sql = "SELECT ape_ven, nom_ven, ema_ven FROM vendedores WHERE " + valor + " LIKE '%" + campo + "%'";

            PS = miConexion.prepareStatement(sql);
            RS = PS.executeQuery();
            lista = new ArrayList();
            while (RS.next()) {
                ModeloVendedor modeloV = new ModeloVendedor();
                modeloV.setApe_ven(RS.getString("ape_ven"));
                modeloV.setNom_ven(RS.getString("nom_ven"));
                modeloV.setEma_ven(RS.getString("ema_ven"));
                lista.add(modeloV);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            miConexion = this.conexionActual.cerrarConexion();
        }
        return lista;
    }

}
