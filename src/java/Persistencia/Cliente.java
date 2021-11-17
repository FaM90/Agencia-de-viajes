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
public class Cliente {

    private int idCliente;
    private String nombre, apellido, correElectronico, numeroDeTelefono;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String apellido, String correElectronico, String numeroDeTelefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correElectronico = correElectronico;
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreElectronico() {
        return correElectronico;
    }

    public void setCorreElectronico(String correElectronico) {
        this.correElectronico = correElectronico;
    }

    public String getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", correElectronico=" + correElectronico + ", numeroDeTelefono=" + numeroDeTelefono + '}';
    }

    public List<Cliente> cosultarCliente() {
        List<Cliente> listaClientes = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM cliente";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCorreElectronico(rs.getString("correElectronico"));
                cliente.setCorreElectronico(rs.getString("numeroDeTelefono"));
                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

        return listaClientes;
    }

    public Cliente consultarUnCliente() {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM cliente WHERE codigo='" + this.idCliente + "'";
        ResultSet rs = conexion.consultarBD(sql);

        try {
            if (rs.next()) {
                this.nombre = rs.getString("nombre");
                this.apellido = rs.getString("apellido");
                this.correElectronico = rs.getString("correoElectronico");
                this.numeroDeTelefono = rs.getString("numeroDeTelefono");
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

    public boolean guardarCliente() {
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

    public boolean actualizarCliente() {
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

    public boolean eliminarCliente() {
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
