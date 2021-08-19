/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LATITUDE E6420
 */
public class ModeloVendedor {

    private String id_ven, nom_ven, ape_ven, dir_ven, usua_ven, cui_ven, tel_ven, sex_ven, fec_ing, fec_nac, ema_ven;
    private double ventas, sueldo;
    private int labora;

    public ModeloVendedor() {
        this.id_ven = null;
        this.nom_ven = null;
        this.ape_ven = null;
        this.dir_ven = null;
        this.usua_ven = null;
        this.cui_ven = null;
        this.tel_ven = null;
        this.sex_ven = null;
        this.fec_ing = null;
        this.fec_nac = null;
        this.ema_ven = null;
        this.ventas = 0;
        this.sueldo = 0;
        this.labora = 0;
    }
    
    
    
    public String getId_ven() {
        return id_ven;
    }

    public void setId_ven(String id_ven) {
        this.id_ven = id_ven;
    }

    public String getNom_ven() {
        return nom_ven;
    }

    public void setNom_ven(String nom_ven) {
        this.nom_ven = nom_ven;
    }

    public String getApe_ven() {
        return ape_ven;
    }

    public void setApe_ven(String ape_ven) {
        this.ape_ven = ape_ven;
    }

    public String getDir_ven() {
        return dir_ven;
    }

    public void setDir_ven(String dir_ven) {
        this.dir_ven = dir_ven;
    }

    public String getUsua_ven() {
        return usua_ven;
    }

    public void setUsua_ven(String usua_ven) {
        this.usua_ven = usua_ven;
    }

    public String getCui_ven() {
        return cui_ven;
    }

    public void setCui_ven(String cui_ven) {
        this.cui_ven = cui_ven;
    }

    public String getTel_ven() {
        return tel_ven;
    }

    public void setTel_ven(String tel_ven) {
        this.tel_ven = tel_ven;
    }

    public String getSex_ven() {
        return sex_ven;
    }

    public void setSex_ven(String sex_ven) {
        this.sex_ven = sex_ven;
    }

    public String getFec_ing() {
        return fec_ing;
    }

    public void setFec_ing(String fec_ing) {
        this.fec_ing = fec_ing;
    }

    public String getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(String fec_nac) {
        this.fec_nac = fec_nac;
    }

    public String getEma_ven() {
        return ema_ven;
    }

    public void setEma_ven(String ema_ven) {
        this.ema_ven = ema_ven;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getLabora() {
        return labora;
    }

    public void setLabora(int labora) {
        this.labora = labora;
    }

    
}
