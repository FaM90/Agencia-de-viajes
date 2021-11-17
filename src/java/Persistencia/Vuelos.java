/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Logica.ConexionBD;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabit
 */
public class Vuelos {

    private int idVuelos, plazaTotal, plazaTurista;
    private Date fecha, hora;
    private String origen, destino;

    public Vuelos() {
    }

    public Vuelos(int idVuelos, int plazaTotal, int plazaTurista, Date fecha, Date hora, String origen, String destino) {
        this.idVuelos = idVuelos;
        this.plazaTotal = plazaTotal;
        this.plazaTurista = plazaTurista;
        this.fecha = fecha;
        this.hora = hora;
        this.origen = origen;
        this.destino = destino;
    }

    public int getIdVuelos() {
        return idVuelos;
    }

    public void setIdVuelos(int idVuelos) {
        this.idVuelos = idVuelos;
    }

    public int getPlazaTotal() {
        return plazaTotal;
    }

    public void setPlazaTotal(int plazaTotal) {
        this.plazaTotal = plazaTotal;
    }

    public int getPlazaTurista() {
        return plazaTurista;
    }

    public void setPlazaTurista(int plazaTurista) {
        this.plazaTurista = plazaTurista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Vuelos{" + "idVuelos=" + idVuelos + ", plazaTotal=" + plazaTotal + ", plazaTurista=" + plazaTurista + ", fecha=" + fecha + ", hora=" + hora + ", origen=" + origen + ", destino=" + destino + '}';
    }

    public List<Vuelos> cosultarReservas() {
        List<Vuelos> listaVuelos = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Vuelos vuelos;
            while (rs.next()) {
                vuelos = new Vuelos();

                listaVuelos.add(vuelos);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

        return listaVuelos;
    }

    public Vuelos consultarUnosVuelos() {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel WHERE codigo='" + this.idVuelos + "'";
        ResultSet rs = conexion.consultarBD(sql);

        try {
            if (rs.next()) {
                this.fecha = rs.getDate("fecha");
                this.hora = rs.getDate("hora");
                this.origen = rs.getString("origen");
                this.destino = rs.getString("destino");
                this.plazaTotal = rs.getInt("plazaTotal");
                this.plazaTurista = rs.getInt("plazaTurista");

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

    public boolean guardarVuelos() {
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

    public boolean actualizarVuelos() {
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

    public boolean eliminarVuelos() {
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
