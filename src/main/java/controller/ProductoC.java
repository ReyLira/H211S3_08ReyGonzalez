package controller;

import dao.ProductoImpl;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Producto;

@Named(value = "productoC")
@SessionScoped
public class ProductoC implements Serializable {

    private Producto producto;
    private ProductoImpl dao;
    private List<Producto> lstProducto;

    public ProductoC() {
        producto = new Producto();
        dao = new ProductoImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar ProductoC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar ProductoC/modificar: " + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar ProductoC/modificar: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstProducto = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listar EventoC/modificar: " + e.getMessage());
        }
    }

    public void limpiar() throws Exception {
        try {
            producto = new Producto();
        } catch (Exception e) {
            System.out.println("Error en listar ProductoC/modificar: " + e.getMessage());
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoImpl getDao() {
        return dao;
    }

    public void setDao(ProductoImpl dao) {
        this.dao = dao;
    }

    public List<Producto> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<Producto> lstProducto) {
        this.lstProducto = lstProducto;
    }


}
