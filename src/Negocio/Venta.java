package Negocio;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;


public class Venta {
    private int codigoVenta;
    private Date fechaVenta; //REVISAR PARA AÑADIR UNA FECHA 
    private String CICliente;
    private double total = 0;
    private ArrayList<DetallesVentaProducto> detallesVenta = new ArrayList<>();   
    
    public Venta (int codigoVenta, String CICliente, ArrayList<DetallesVentaProducto> detallesVenta){
        this.CICliente = CICliente;
        this.detallesVenta = detallesVenta;
        this.codigoVenta = codigoVenta;
        simplificarDetallesVenta();
    }
    
    
    public void calcularTotal (){
        total = 0;
        for (DetallesVentaProducto dC : detallesVenta){
            total += (dC.getPrecioProducto() * dC.getUnidades());
        }
    }
    
    public int getCodigoVenta() {
        return codigoVenta;
    }

    public ArrayList<DetallesVentaProducto> getDetallesVenta() {
        return detallesVenta;
    }
    
    public void simplificarDetallesVenta(){
        ArrayList<DetallesVentaProducto> temp = new ArrayList<>();
        int length = detallesVenta.size();
        
        for(int i = 0; i < length; i++){
            for(int j = i + 1; j < length; j++){ 
                if(detallesVenta.get(i).getCodigo() == detallesVenta.get(j).getCodigo()){
                    detallesVenta.get(i).setUnidades(detallesVenta.get(i).getUnidades() + detallesVenta.get(j).getUnidades());
                    detallesVenta.get(j).setCodigo(-1);
                }
            }
            if (detallesVenta.get(i).getCodigo() != -1){
               temp.add(detallesVenta.get(i));
            }
        }        
        detallesVenta = temp;
    }
        
    public void generarFacturaVenta(){
        calcularTotal();
        System.out.println("CLIENTE: " + CICliente);
        System.out.println("    CODIGO    |    NOMBRE   |    PRECIO    |    UNIDADES    ");
        for(DetallesVentaProducto dv : detallesVenta){
            System.out.println("    " + dv.getCodigo() + " \t" + dv.getNombreProducto() + "   \t   " + dv.getPrecioProducto() + "   \t    " + dv.getUnidades());
        }
        System.out.println("TOTAL: " + total);
        
    }
    
}
