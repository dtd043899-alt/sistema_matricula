/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.Alumno;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javax.swing.JOptionPane;


public class ControladorAlumno {
    
   public boolean Registrar(Alumno a) {
       String sql = "INSERT INTO Alumno (dni, nombre, apellidos, edad, estado, grado) VALUES(?,?,?,?,?,?)";
      try (
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(sql)) {
            
            pst.setString(1, a.getDni());
            pst.setString(2, a.getNombre());
            pst.setString(3, a.getApellido());
            pst.setInt(4, a.getEdad());
            pst.setString(5, a.getEstado());
            pst.setString(6, a.getGrado());
            
            return pst.executeUpdate()> 0;
      }catch(SQLException e) {
          JOptionPane.showMessageDialog(null, "error al registrar" + e.getMessage());
          return false;
      }
   }
   
   public Alumno consultar(int idAlumno) {
       Alumno a = null;
       String sql = "SELECT * FROM Alumno WHERE idAlumno = ?";
       
       try (
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(sql)) {
           
           pst.setInt(1, idAlumno);
           ResultSet rs = pst.executeQuery();
           
           if(rs.next()) {
            a = new Alumno();
            a.setCodigo(rs.getInt("idAlumno"));
            a.setDni(rs.getString("dni"));
            a.setNombre(rs.getString("nombre"));
            a.setApellido(rs.getString("apellidos"));
            a.setEdad(rs.getInt("edad"));
            a.setEstado(rs.getString("estado"));
            a.setGrado(rs.getString("grado"));
               
           }
       }catch(SQLException e ) {
           JOptionPane.showMessageDialog(null, "Error al consultar: " + e.getMessage());
       }
       return  a;
   }
   public boolean borrar(int idAlumno) {
       String sql = "DELETE FROM Alumno WHERE idAlumno = ?";
        
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setInt(1, idAlumno);
            return pst.executeUpdate() > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar: " + e.getMessage());
            return false;
        }
    }
   
   public boolean actualizar(Alumno a) {
       String sql = "UPDATE Alumno SET dni = ?, nombre = ?, apellidos = ?, edad = ?, estado = ?, grado = ? WHERE idAlumno = ?";
       
       try (
            Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
           
           pst.setString(1, a.getDni());
           pst.setString(2, a.getNombre());
           pst.setString(3, a.getApellido());
           pst.setInt(4, a.getEdad());
           pst.setString(5, a.getEstado());
           pst.setString(6, a.getGrado());
           pst.setInt(7, a.getCodigo());
           
           return pst.executeUpdate() > 0;
           
           
           
       }catch(SQLException e) {
           JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
           return false;
       }
   }
}