package Negocio;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    static Scanner entrada = new Scanner(System.in); //Scanner para entrada por teclado
    static ArrayList<Producto> productosStock = new ArrayList<>();  //ArrayList de los productos del inventario
    static ArrayList<Venta> ventas = new ArrayList<>();  //ArrayList de las ventas realizadas
    static int referenceCode = 0;                       //Variable estática empleada como código de referencia para los códigos de los productos
    static int codigoReferenciaVenta = 0;
      
    
    public static void main(String[] args) {    
        Informe informe;
        int option = -1;
       // verificarLogin();
        //Productos añadidos para testeo rápido del programa
        productosStock.add(new Producto (++referenceCode, 1, "PAN", "SUPER PAN"));
        productosStock.add(new Producto (++referenceCode, 2, "QUESO", "SALINERITO"));
        productosStock.add(new Producto (++referenceCode, 3, "PAPA", "LA FAVORITA"));
        
        if (verificarLogin()== true) {
             while (option != 0 ){
            
            //MENÚ PRINCIPAL DEL PROGRAMA
            System.out.println("MINI MARKET LA VECI DE LA CUADRA");
            System.out.println("1. CONTROL DE MERCANCÍA");
            System.out.println("2. CONTROL DE COMPRA/VENTA DE PRODUCTO");
            System.out.println("3. GENERACIÓN DE INFORME DE VENTAS");
            System.out.println("0. CERRAR SISTEMA");
            System.out.println("QUÉ DESEA REALIZAR?");
            option = entrada.nextInt();
            System.out.println();
            
            switch (option){
                case 1:
                    while(menuControlMercancia());
                    break;
                case 2:
                    while(menuControlCompraVentaProducto());
                    break;
                case 3:
                    informe = new Informe(productosStock, ventas);
                    informe.graficoResumen();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("OPCIÓN INVÁLIDA. TRATE DE NUEVO");
                    break;
            }
            System.out.println();
        }
        }else{
            System.out.println("Credenciales incorrectas");
        }
       
    }  
    // metodo para ingresar con credenciales
    public static boolean verificarLogin(){
        String usuario = " ";
        String contraseña = " " ;
        System.out.println("Ingrese usuario ");
        usuario = entrada.next();
        System.out.println("Ingrese contraseña ");
        contraseña = entrada.next();
        boolean validador = false;
        if (usuario.equals("admin")  && contraseña.equals("admin123") ) {
            return validador = true;
        }else{
           return validador;
        }
    } 
    //MÉTODO PARA EL MENÚ DEL CASO DE USO CONTROL DE MERCANCÍA 
    public static boolean menuControlMercancia(){
        int option;
        boolean validador = true;
        
        // MENÚ DEL CASO DE USO CONTROL DE MERCANCÍA 
        System.out.println("-----CONTROL DE MERCANCÍA-----");
        System.out.println("QUÉ DESEA HACER?");
        System.out.println("1. REVISAR PRODUCTOS");
        System.out.println("2. SUSPENDER CONTRATO DE PRODUCTO");
        System.out.println("3. REGISTRAR NUEVO PRODUCTO");
        System.out.println("0. RETROCEDER");
            
        option = entrada.nextInt();
            
        switch (option){
            case 1:
                generacionInventario();
                break;
            case 2:
                while(suspensionContratoProducto());
                break;
            case 3:
                registrarNuevoProducto();
                break;
            case 0:
                validador = false;
                break;
            default:
                System.out.println("OPCIÓN INVÁLIDA. TRATE DE NUEVO");
                break;
            }
        System.out.println();
        return validador;
    }
    
    //MÉTODO PARA LA GENERACIÓN DE INVENTARIO DEL INFORME
    public static void generacionInventario(){
        System.out.println("| \tCODIGO\t | \tNOMBRE\t | \tPRECIO\t | \tDISTRIBUIDOR\t | \t\tESTADO\t\t | \tUNIDADES DISPONIBLES\t |");
        for (Producto p: productosStock){
           /* System.out.printf("    %d  %s    \t%.2f    %s    %s    \t\t%d", p.getCodigo(),  
                    p.getNombreProducto(), p.getPrecio(), p.getDistribuidor(), 
                    p.getEstadoProducto(), p.getUnidadesDisponibles()); */
            System.out.println("\t" + p.getCodigo() + "\t\t" + p.getNombreProducto() 
                    + "\t\t" + p.getPrecio() + "\t\t" + p.getDistribuidor() + "\t\t" 
                    + p.getEstadoProducto() + "\t\t\t\t" + p.getUnidadesDisponibles());
        System.out.println();
        }  
        System.out.println();
    }
    
    //MÉTODO PARA SUSPENDER EL CONTRATO DE UN PRODUCTO (ASÍ SE INHABILITA LA CONDICIÓN DE COMPRA)
    public static boolean suspensionContratoProducto(){
        boolean validador = true;
        int codigo = 0;
        
        System.out.println("Ingrese el código del producto que desea eliminar");
        codigo = entrada.nextInt();
        
        for (int i = 0; i < productosStock.size(); i++){
            if(codigo == productosStock.get(i).getCodigo()){
                if(productosStock.get(i).getEstadoProducto().equals("ACTIVO")){
                    System.out.println("La compra de: " + productosStock.get(i).getNombreProducto() + " queda suspendida");
                    productosStock.get(i).setEstadoProducto("SUSPENDIDO");
                }
                else{
                    System.out.println("El contrato de este producto ya se encuentra suspendido.");
                }
                return false;               
            }
        }
        System.out.println("Código inválido. Trate de nuevo.");
        System.out.println();
        return true;
    }
    
    //MÉTODO PARA REGISTRAR UN NUEVO PRODUCTO
    public static void registrarNuevoProducto(){ //Agregar la validación del precio del producto
        double precioProducto = -1.0;
        String nombreProducto;
        String distribuidorProducto;
        boolean validador = false;
        
        System.out.println("Ingrese el nombre del nuevo producto a agregar:");
        nombreProducto = entrada.next();
        for(int i = 0; i < productosStock.size(); i++){
            if(productosStock.get(i).equals(nombreProducto))
                validador = true;
        }
        
        if(!validador){
            System.out.println("Ingrese el nombre del distribuidor del producto:");
            distribuidorProducto = entrada.next();
            
            while(precioProducto <= 0){
                System.out.println("Ingrese el precio el del producto:");
                precioProducto = entrada.nextDouble();
                if(precioProducto <= 0){
                    System.out.println("ERROR! EL PRECIO DEL PRODUCTO DEBE SER MAYOR A 0. Trate de nuevo.");
                }
            }
            productosStock.add(new Producto(++referenceCode, precioProducto, 
                                            nombreProducto, distribuidorProducto));
            System.out.println("Producto agregado exitosamente!");
        }
        else{
            System.out.println("ERROR! Este producto ya se encuentra registrado. Trate nuevamente.");
        }
        System.out.println();
    }
    
    //MÉTODO PARA EL MENÚ DEL CASO DE USO COMPRA/VENTA DE PRODUCTOS
    public static boolean menuControlCompraVentaProducto(){
        int option;
        boolean validador = true;
        
        //MENÚ DEL CASO DE USO COMPRA/VENTA DE PRODUCTOS
        System.out.println("-----CONTROL DE COMPRA-VENTA PRODUCTOS-----");
        System.out.println("QUÉ DESEA HACER?");
        System.out.println("1. COMPRAR PRODUCTOS");
        System.out.println("2. REALIZAR VENTA");
        System.out.println("0. RETROCEDER");
        
        option = entrada.nextInt();
        
        switch (option){
            case 1:
                generacionInventario();
                comprarProducto();
                break;
            case 2:
                generacionInventario();
                registrarVenta();
                break;
            case 0:
                validador = false;
                break;
            default:
                System.out.println("OPCIÓN INVÁLIDA. TRATE DE NUEVO");
                break;
            }
        System.out.println();
        return validador;
    }
    
    //COMPRAR UNIDADES DE UN PRODUCTO
    public static boolean comprarProducto(){
        int codigo;
        int unidades = 0;
        System.out.println("Ingrese el código del producto:");
        codigo = entrada.nextInt();
        
        for(Producto p : productosStock){
            if(p.getCodigo() == codigo){
                if(p.getEstadoProducto().equals("SUSPENDIDO")){
                    System.out.println("ERROR! No se puede comprar unidades de un producto con contrado suspendido.");
                    System.out.println();
                    return true;
                }
                
                while(unidades < 1){
                    System.out.print("Ingrese la cantidad de unidades que desea comprar: ");
                    unidades = entrada.nextInt();
                    if(unidades < 1){
                        System.out.println("ERROR! La cantidad a comprar debe ser mayor a 0. Trate de nuevo");
                    }
                }
                p.setUnidadesDisponibles(p.getUnidadesDisponibles() + unidades);
                System.out.println();
                return false;
            }
        }
        
        System.out.println("ERROR! Código inválido. Trate de nuevo.");
        System.out.println();
        return true;
    }
    
    //MÉTODO PARA AÑADIR UNIDADES DE UN PRODUCTO A UN CARRITO DE COMPRAS
    public static boolean aniaDirProductoCarrito(ArrayList<DetallesVentaProducto> productos){
        int codigo;
        int unidades = 0;
        System.out.println("Ingrese el código del producto:");
        codigo = entrada.nextInt();
        
        for(Producto p : productosStock){
            
            if(p.getCodigo() == codigo){
                
                if(p.getUnidadesDisponibles() == 0){
                    System.out.println("No se disponen de más unidades de este producto.");
                    System.out.println();
                    return true;
                }
                
                while(unidades < 1){
                    System.out.print("Ingrese la cantidad de unidades que desea comprar: ");
                    unidades = entrada.nextInt();
                    
                    if(unidades < 1){
                        System.out.println("ERROR! La cantidad a vender debe ser mayor a 0. Trate de nuevo");
                    }
                    
                    else if(unidades > p.getUnidadesDisponibles()){
                        System.out.println("ERROR! La cantidad de unidades solicitadas supera las disponibles. Se venderán todas las disponibles.");
                        productos.add(new DetallesVentaProducto(p.getCodigo(), p.getNombreProducto(), p.getUnidadesDisponibles(), p.getPrecio()));
                        p.setUnidadesDisponibles(0);
                        System.out.println("Producto(s) Agregado(s) Con Éxito.");
                    }
                    
                    else{
                        productos.add(new DetallesVentaProducto(p.getCodigo(), p.getNombreProducto(), unidades, p.getPrecio()));
                        p.setUnidadesDisponibles(p.getUnidadesDisponibles() - unidades);
                        System.out.println("Producto(s) Agregado(s) Con Éxito.");
                    }
                }
                System.out.println();
                return false;
            }
        }
        
        System.out.println("ERROR! Código inválido. Trate de nuevo.");
        System.out.println();
        return true;
    }
    
    //MÉTODO PARA EL REGISTRO DE UNA VENTA
    public static void registrarVenta(){
        boolean validador = true;
        int option = -1;
        String cedula;
        ArrayList<DetallesVentaProducto> detallesCompra = new ArrayList<>();
        
        while (validador){
            System.out.println("-----CARRITO DE COMPRA-----");
            System.out.println("1. Agregar producto");
            System.out.println("2. Finalizar Venta");
            option = entrada.nextInt();
            
            switch(option){
                case 1:
                    aniaDirProductoCarrito(detallesCompra);
                    break;
                case 2:
                    System.out.println("Ingrese la cédula del cliente (DE DEJAR VACÍO ESTE CAMPO SE CONSIDERARÁ COMO CONSUMIDOR FINAL)");
                    cedula = entrada.next();
                    
                    Venta tempVenta = new Venta(++codigoReferenciaVenta, cedula, detallesCompra);
                    tempVenta.generarFacturaVenta();
                    ventas.add(tempVenta);
                    
                    System.out.println("Venta registrada!");
                    validador = false;
                    break;
                default:
                    System.out.println("OPCIÓN INVÁLIDA. TRATE DE NUEVO");
                    break;
            }
            System.out.println();
        }
    }
    
}
