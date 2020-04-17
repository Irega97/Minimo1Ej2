package edu.upc.dsa.models;

public class Objeto {
    public String id;
    public String nombre;

    public Objeto(){
    }

    public Objeto(String id, String nombre){
        this();
        this.id=id;
        this.nombre=nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Object [id="+id+",nombre"+nombre+ "]";
    }
}
