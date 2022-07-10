package Negocio;

public class DetallesVentaProducto {
    private String nombreProducto;
    private int unidades;
    private double precioProducto;
    private int codigo;
    
    public DetallesVentaProducto(int codigo, String nombreProducto, int unidades, double precioProducto){
        this.nombreProducto = nombreProducto;
        this.unidades = unidades;
        this.precioProducto = precioProducto;
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
