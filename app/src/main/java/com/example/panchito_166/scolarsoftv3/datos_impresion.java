package com.example.panchito_166.scolarsoftv3;

/**
 * Created by panchito_166 on 24/11/2016.
 */
public class datos_impresion {
    String clave_folio;
    String clave_empresa;
    String historial;
    String fecha;
    String total;
    String precio;
    String subtotal;
    String descuento;
    String recargo;
    String cantidad;
    //String logo;
    String  colegio;
    String direccion;
    String correo;
    String contacto;


    public  String getClave_empresa  (){return  clave_empresa;}
    public  void setClave_empresa (String clave_empresa){this.clave_empresa = clave_empresa;}

    public String getClave_folio() {
        return clave_folio;
    }
    public void setClave_folio(String clave_folio) {
        this.clave_folio = clave_folio;
    }

    public  String getHistorial(){return  historial;}
    public  void setHistorial(String historial){this.historial = historial;}

    public  String getFecha(){return  fecha;}
    public void  setFecha(String fecha){this.fecha = fecha;}

    public  String getTotal (){return  total;}
    public  void  setTotal(String total){this.total = total;}

    public  String getPrecio () {return  precio;}
    public void setPrecio(String precio){this.precio = precio;}

    public  String getSubtotal (){return  subtotal;}
    public void setSubtotal (String subtotal){this.subtotal = subtotal;}

    public  String getDescuento(){return  descuento;}
    public void setDescuento (String descuento){this.descuento = descuento;}

    public String getRecargo () {return  recargo;}
    public void setRecargo (String recargo){this.recargo = recargo;}

    public String getCantidad (){return cantidad;}
    public void setCantidad (String cantidad){this.cantidad= cantidad;}

    public  String getColegio (){return  colegio;}
    public void setColegio(String colegio){this.colegio = colegio;}

    public String getDireccion(){return direccion;}
    public void setDireccion(String direccion){this.direccion = direccion;}

    public  String getCorreo () {return  correo;}
    public void  setCorreo (String correo){this.correo = correo;}

    public  String getContacto (){return  contacto;}
    public void  setContacto (String contacto){this.contacto = contacto;}

}
