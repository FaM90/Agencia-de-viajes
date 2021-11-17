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
public class Hotel {

    private int idHotel, telefono, numeroDeHabitaciones;
    private String nombre, direccion, ciudad;

    public Hotel() {
    }

    public Hotel(int idHotel, int telefono, int numeroDeHabitaciones, String nombre, String direccion, String ciudad) {
        this.idHotel = idHotel;
        this.telefono = telefono;
        this.numeroDeHabitaciones = numeroDeHabitaciones;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getNumeroDeHabitaciones() {
        return numeroDeHabitaciones;
    }

    public void setNumeroDeHabitaciones(int numeroDeHabitaciones) {
        this.numeroDeHabitaciones = numeroDeHabitaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Hotel{" + "idHotel=" + idHotel + ", telefono=" + telefono + ", numeroDeHabitaciones=" + numeroDeHabitaciones + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad + '}';
    }

    public List<Hotel> cosultarHoteles() {
        List<Hotel> listaHoteles = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Hotel hotel;
            while (rs.next()) {
                hotel = new Hotel();

                listaHoteles.add(hotel);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

        return listaHoteles;
    }

    public Hotel consultarUnHotel() {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM hotel WHERE codigo='" + this.idHotel + "'";
        ResultSet rs = conexion.consultarBD(sql);

        try {
            if (rs.next()) {
                this.nombre = rs.getString("nombre");
                this.direccion = rs.getString("direccion");
                this.ciudad = rs.getString("ciudad");
                this.telefono = rs.getInt("telefono");
                this.numeroDeHabitaciones = rs.getInt("numeroDeHabitaciones");
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

    public boolean guardarHoteles() {
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

    public boolean actualizarHoteles() {
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

    public boolean eliminarHoteles() {
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
