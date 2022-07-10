package Negocio;

import java.util.ArrayList;

public class Informe {
    private ArrayList<Double> porcentajeVentaProductos = new ArrayList<>();
    private ArrayList<DetallesVentaProducto> productosDisponibles = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    private double totalVendido;
    
    public Informe(ArrayList<Producto> productos, ArrayList<Venta> ventas){
        this.ventas = ventas;
        for(Producto p : productos){
            this.productosDisponibles.add(new DetallesVentaProducto(p.getCodigo(), p.getNombreProducto(), 0, p.getPrecio()));
        }
    }
    
    private void unificarInformacionVentas(){
        ArrayList<DetallesVentaProducto> totalProductos = new ArrayList();
        
        for(Venta v : ventas){
            for(DetallesVentaProducto dv : v.getDetallesVenta()){
                totalProductos.add(dv);
            }
        }

        for(int i = 0; i < productosDisponibles.size(); i++){
            for(int j = 0; j < totalProductos.size(); j++){
                if(productosDisponibles.get(i).getCodigo() == totalProductos.get(j).getCodigo()){
                    productosDisponibles.get(i).setUnidades(productosDisponibles.get(i).getUnidades() + totalProductos.get(j).getUnidades());
                }            
            }
        }
    }
    
    private void calcularPorcentajesVenta(){
        unificarInformacionVentas();
        int counter = 0;
        for(DetallesVentaProducto dv : productosDisponibles){
            porcentajeVentaProductos.add(dv.getPrecioProducto() * dv.getUnidades());
            totalVendido += porcentajeVentaProductos.get(counter++);
        }
        
        for(int i = 0; i < porcentajeVentaProductos.size(); i++){
            porcentajeVentaProductos.set(i, porcentajeVentaProductos.get(i) / totalVendido * 100);
        }
    }
    
    public void graficoResumen(){
        calcularPorcentajesVenta();
        System.out.println("REPRESENTACIÓN PORCENTUAL DE LAS VENTAS POR PRODUCTO:");
        
        for(int i = 0; i < productosDisponibles.size(); i++){
            System.out.printf("%s(%.2f):\n", productosDisponibles.get(i).getNombreProducto(), porcentajeVentaProductos.get(i));
            
            for(int j = 1; j <= porcentajeVentaProductos.get(i); j++){
            //    System.out.print("*");
                if(j % 10 == 0){
                    System.out.println();
                }
            }
            System.out.println("\n" + porcentajeVentaProductos.get(i) + "%.\n");
        }
        System.out.println("Total Vendido:" + totalVendido);
    }
}
