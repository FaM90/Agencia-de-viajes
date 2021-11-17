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
public class Reservas {

    private int idReserva, idHotel, idCliente, idPromo;
    private Date fechaLlegada, fechaSalida;

    public Reservas() {
    }

    public Reservas(int idReserva, int idHotel, int idClient, int idPromo, Date fechaLlegada, Date fechaSalida) {
        this.idReserva = idReserva;
        this.idHotel = idHotel;
        this.idCliente = idClient;
        this.idPromo = idPromo;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdClient() {
        return idCliente;
    }

    public void setIdClient(int idClient) {
        this.idCliente = idClient;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public String toString() {
        return "Reservas{" + "idReserva=" + idReserva + ", idHotel=" + idHotel + ", idClient=" + idCliente + ", idPromo=" + idPromo + ", fechaLlegada=" + fechaLlegada + ", fechaSalida=" + fechaSalida + '}';
    }

    public List<Reservas> cosultarReservas() {
        List<Reservas> listaReservas = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Reservas reservas;
            while (rs.next()) {
                reservas = new Reservas();

                listaReservas.add(reservas);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

        return listaReservas;
    }

    public Reservas consultarUnaReserva() {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel WHERE codigo='" + this.idReserva + "'";
        ResultSet rs = conexion.consultarBD(sql);

        try {
            if (rs.next()) {
                this.idHotel = rs.getInt("idHotel");
                this.idCliente = rs.getInt("idClient");
                this.fechaLlegada= rs.getDate("fechaLlegada");
                this.fechaSalida= rs.getDate("fechaSalida");
                this.idPromo =rs.getInt("idPromo");
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

    public boolean guardarReservas() {
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

    public boolean actualizarReservas() {
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

    public boolean eliminarPromociones() {
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
