package com.example.panchito_166.scolarsoftv3;

public class datos_personales {
    String[][] materia;
    String nombre_completo;
    String matricula;
    String clave_usuario;
    String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave_usuario() {
        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public String[][] getMateria() {
        return materia;
    }

    public void setMateria(String[][] materia) {
        this.materia = materia;
    }

    public String getNombre_nivel() {
        return nombre_nivel;
    }

    public void setNombre_nivel(String nombre_nivel) {
        this.nombre_nivel = nombre_nivel;
    }

    String grado;
    String grupo;
    String ciclo_escolar;
    String nombre_nivel;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCiclo_escolar() {
        return ciclo_escolar;
    }

    public void setCiclo_escolar(String ciclo_escolar) {
        this.ciclo_escolar = ciclo_escolar;
    }
}
