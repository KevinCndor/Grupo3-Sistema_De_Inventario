package Negocio;

import java.util.Date;

public class Producto {
    private int codigo = 0;
    private int referenceCode; //ANALIZAR SU ELIMINACIÓN
    private double precio;
    private String nombreProducto;
    private String estadoProducto;
    //private TipoProducto tipoProducto; CREAR UN ENUM PARA ESPECIFICAR LOS TIPOS
    private String distribuidor;
    private int unidadesDisponibles;
    
    public Producto (double precio, String nombreProducto, String distribuidor){ //AÑADIR EN EL CONSTRUCTURO tipoProducto
        codigo = 1;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
        this.distribuidor = distribuidor;
        this.estadoProducto = "ACTIVO";
    }
    
    public Producto (int codigo, double precio, String nombreProducto, String distribuidor){ //AÑADIR EN EL CONSTRUCTURO tipoProducto
        this.codigo = codigo;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
        this.distribuidor = distribuidor;
        this.estadoProducto = "ACTIVO";
    }
        
    public void añadirUnidades (int unidades){ //Revisar si es o no necesario en verdad (eliminar)
        unidadesDisponibles += unidades;
    }
    
    public void eliminarUnidades (int unidades){ //Revisar si es o no necesario en verdad (eliminar)
        if (unidades < unidadesDisponibles){
            unidadesDisponibles -= unidades;
        }
    }

    public double getPrecio() {
        return precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getEstadoProducto() {
        return estadoProducto;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setEstadoProducto(String estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }
    

}
