package edu.upc.dsa.models;

public class Objeto {
    public String id;
    public int cantidad;

    public Objeto(){
    }

    public Objeto(String id, int cantidad){
        this();
        this.cantidad=cantidad;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Object [id="+id+", cantidad=" + cantidad + "]";
    }
}
