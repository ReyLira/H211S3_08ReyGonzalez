package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Producto;

public class ProductoImpl extends Conexion implements ICRUD<Producto> {

    DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
// Metodo para para agregar en la tabla temporal venta

    @Override
    public void guardar(Producto producto) throws Exception {
        String sql = "insert into PRODUCTO"
                + "(NOMPRO,PREPRO,FECVEN,CANPRO,CATPO,DESPRO)"
                + "values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, formato.format(producto.getFecha()));
            ps.setInt(4, producto.getCantidad());
            ps.setString(5, producto.getCategoria());
            ps.setString(6, producto.getDescripcion());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ProductoImpl/registrar: " + e.getMessage());
        }

    }

    @Override
    public void modificar(Producto producto) throws Exception {
        String sql = "update producto set NOMPRO=?,PREPRO=?,FECVEN=?,CANPRO=?,CATPO=?,DESPRO=? where CODPRO=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setDate(3, (Date) producto.getFecha());
            ps.setInt(4, producto.getCantidad());
            ps.setString(5, producto.getCategoria());
            ps.setString(6, producto.getDescripcion());
            ps.setInt(7, producto.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ProductoImpl/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Producto producto) throws Exception {
        try {
            String sql = "delete from Producto where CODPRO=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, producto.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en ProductoImpl/eliminar: " + e.getMessage());
        }
    }

    @Override
    public List<Producto> listarTodos() throws Exception {
        List<Producto> listado = null;
        Producto pro;
        String sql = "SELECT * FROM PRODUCTO ORDER BY CODPRO DESC";
        ResultSet rs;
        try {
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro = new Producto();
                pro.setCodigo(rs.getInt("CODPRO"));
                pro.setNombre(rs.getString("NOMPRO"));
                pro.setPrecio(rs.getDouble("PREPRO"));
                pro.setFecha(rs.getDate("FECVEN"));
                pro.setCantidad(rs.getInt("CANPRO"));
                pro.setCategoria(rs.getString("CATPO"));
                pro.setDescripcion(rs.getString("DESPRO"));
                listado.add(pro);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en listar" + e.getMessage());
        }
        return listado;
    }

}
