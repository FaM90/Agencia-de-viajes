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
public class Promociones {
    private int idPromo,idHotel,valor;
    private String tipoHabitacion;

    public Promociones() {
    }

    public Promociones(int idPromo, int idHotel, int valor, String tipoHabitacion) {
        this.idPromo = idPromo;
        this.idHotel = idHotel;
        this.valor = valor;
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    @Override
    public String toString() {
        return "Promociones{" + "idPromo=" + idPromo + ", idHotel=" + idHotel + ", valor=" + valor + ", tipoHabitacion=" + tipoHabitacion + '}';
    }
    
    public List<Promociones> cosultarPromociones() {
        List<Promociones> listaPromociones = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Promociones promociones;
            while (rs.next()) {
                promociones = new Promociones();

                listaPromociones.add(promociones);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

        return listaPromociones;
    }

    public Promociones consultarUnHotel() {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel WHERE codigo='" + this.idPromo + "'";
        ResultSet rs = conexion.consultarBD(sql);

        try {
            if (rs.next()) {
                this.idHotel = rs.getInt("idHotel");
                this.tipoHabitacion = rs.getString("tipoHabitacion");
                this.valor = rs.getInt("valor");
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

    public boolean guardarPromociones() {
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

    public boolean actualizarPromociones() {
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
