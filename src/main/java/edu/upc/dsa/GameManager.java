package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;

import java.util.List;

public interface GameManager {

    public List<User> getUserAlphOrdered(); //GET lista usuarios ordenados alfabeticamente
    public int addUser(String id, String name, String surname); //POST add user
    public int updateUser(String id, String name, String surname); //PUT actualizar datos de un usuario
    public User getUser(String id); //GET info de un usuario
    public int getNumberUsers(); //GET numero usuarios que hay en el sistema
    public Objeto getObject(String id); //GET Info objeto
    public List<Objeto> getUserObjects(User u); //GET objetos de un usuario
    public Objeto addObject(Objeto o);
    public int addObjectToUser(String userID, String objectID); //POST añadir objeto a usuario
    public int getNumberObjects(User u); //GET numero de objetos de un usuario




}
