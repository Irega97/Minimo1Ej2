package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;
import java.util.List;

public class User {
    public String id;
    public String name;
    public String surname;
    public List<Objeto> objectList = null;

    public User(){
    }

    public User(String id, String name, String surname){
        this();
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.objectList=new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Objeto> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Objeto> objectList) {
        this.objectList = objectList;
    }

    @Override
    public String toString(){
        return "User : [id="+id+", name="+name+"]";
    }
}
