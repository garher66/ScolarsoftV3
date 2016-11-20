package com.example.panchito_166.scolarsoftv3;

/**
 * Created by panchito_166 on 14/11/2016.
 */
public class datos_historial {

    String [][] folio;
    String [][] historial;
    String [][] fecha;
    String [][] total;
    String [][]  subtotal;
    String [][] descuento;
    String[][] recargo;
    String[][] status;
    String clave_usuario;
    String clave_ciclo;
    String clave_alumnos;
    String clave_empresa;


    public String getClave_usuario() {
        return clave_usuario;
    }
    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public  String getClave_ciclo() {return  clave_ciclo;}
    public void setClave_ciclo (String clave_ciclo){this.clave_ciclo=clave_ciclo;}

    public String getClave_alumnos(){return  clave_alumnos;}
    public void setClave_alumnos(){this.clave_alumnos = clave_alumnos;}

    public  String getClave_empresa(){return  clave_empresa;}
    public  void  setClave_empresa (){this.clave_empresa = clave_empresa;}

    public String[][] getFolio (){return folio;}
    public void setFolio (String[][] folio) {this.folio = folio;}

    public  String[][] getHistorial(){return historial;}
    public  void  setHistorial(String[][] historial){this.historial = historial;}

    public  String[][] getFecha(){return  fecha;}
    public  void setFecha(String[][] fecha){this.fecha = fecha;}

    public  String [][] getTotal(){return  total;}
    public void setTotal (String[][] total){this.total = total;}

    public String[][] getSubtotal(){return  subtotal;}
    public  void setSubtotal(String[][] subtotal){this.subtotal = subtotal;}

    public  String[][] getDescuento (){return  descuento;}
    public  void setDescuento(String[][] descuento){this.descuento = descuento;}

    public String[][] getRecargo(){return recargo;}
    public  void setRecargo(String[][] recargo){this.recargo= recargo;}

    public  String [][] getStatus(){return  status;}
    public  void setStatus (String[][] status){this.status = status;}

}
