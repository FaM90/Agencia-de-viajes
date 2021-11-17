/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Logica.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabit
 */
public class Vuelo {

    private int idContratoVuelo, idCliente, idVuelo;
    private String clase;

    public Vuelo() {
    }

    public Vuelo(int idContratoVuelo, int idCliente, int idVuelo, String clase) {
        this.idContratoVuelo = idContratoVuelo;
        this.idCliente = idCliente;
        this.idVuelo = idVuelo;
        this.clase = clase;
    }

    public int getIdContratoVuelo() {
        return idContratoVuelo;
    }

    public void setIdContratoVuelo(int idContratoVuelo) {
        this.idContratoVuelo = idContratoVuelo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "Vuelo{" + "idContratoVuelo=" + idContratoVuelo + ", idCliente=" + idCliente + ", idVuelo=" + idVuelo + ", clase=" + clase + '}';
    }

    public List<Vuelo> cosultarReservas() {
        List<Vuelo> listaVuelo = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Vuelo vuelo;
            while (rs.next()) {
                vuelo = new Vuelo();

                listaVuelo.add(vuelo);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

        return listaVuelo;
    }

    public Vuelo consultarUnVuelo() {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel WHERE codigo='" + this.idVuelo + "'";
        ResultSet rs = conexion.consultarBD(sql);

        try {
            if (rs.next()) {
                this.idCliente = rs.getInt("idCliente");
                this.clase = rs.getString("clase");
                this.idVuelo = rs.getInt("idVuelo");
            } else {
                conexion.cerrarConexion();
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

        return this;
    }

    public boolean guardarVuelo() {
        ConexionBD conexion = new ConexionBD();
        String sql = "";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean actualizarVuelo() {
        ConexionBD conexion = new ConexionBD();
        String sql = "";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean eliminarVuelo() {
        ConexionBD conexion = new ConexionBD();
        String sql = "";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }
}
