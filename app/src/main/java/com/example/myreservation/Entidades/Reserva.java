package com.example.myreservation.Entidades;

public class Reserva {
    private Integer id;
    private String idUser;
    private String idAdmin;
    private String nombreMesa;
    private String fecha;
    private String inicio;
    private String fin;

    public Reserva(Integer id, String idUser, String idAdmin, String nombreMesa, String fecha, String inicio, String fin) {
        this.id = id;
        this.idUser = idUser;
        this.idAdmin = idAdmin;
        this.nombreMesa = nombreMesa;
        this.fecha = fecha;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Reserva() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombreMesa() {
        return nombreMesa;
    }

    public void setNombreMesa(String nombreMesa) {
        this.nombreMesa = nombreMesa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
}
