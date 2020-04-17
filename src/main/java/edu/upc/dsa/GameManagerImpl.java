package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;

import java.util.*;

import org.apache.log4j.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    protected HashMap<String, User> users;
    protected List<Objeto> objects;
    final static Logger logger = Logger.getLogger(TracksManagerImpl.class);

    private GameManagerImpl() {
        this.users = new HashMap<>();
        this.objects = new LinkedList<>();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    @Override
    public List<User> getUserAlphOrdered() {
        if(this.users != null){
            List<User> result = new LinkedList<>(users.values());

            Collections.sort(result, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    return u1.getName().compareToIgnoreCase(u2.getName());
                }
            });
            logger.info("Lista usuarios ordenada: " + result.toString());
            return result; //200 OK
        }
        else
            return null; //404 NOT FOUND
    }

    @Override
    public int addUser(String id, String name, String surname) {
        User user = new User(id,name,surname);
        try{
            users.put(id,user);
            logger.info("User added: "+user);
            return 201; //OK
        } catch (IndexOutOfBoundsException e) {
            logger.error("Error");
            return 400; //ERROR
        }
    }

    @Override
    public int updateUser(String id, String name, String surname) {
        User user = this.users.get(id);
        if(user!=null) {
            try {
                user.setName(name);
                user.setSurname(surname);
                logger.info("User updated: " + user);
                return 201; //OK
            } catch (IndexOutOfBoundsException e) {
                logger.error("Error");
                return 400; //ERROR
            }
        }
        return 404; //NOT FOUND
    }

    @Override
    public User getUser(String id) {
        User user = users.get(id);
        if(user!=null){
            logger.info("User found: "+user);
        }else{
            logger.info("User not found for ID "+id);
        }
        return user;
    }

    @Override
    public int getNumberUsers() {
        int ret = this.users.size();
        logger.info("size " + ret);
        return ret;
    }

    @Override
    public Objeto getObject(String id) {
        Objeto o = null;
        try{
            for(Objeto objeto : this.objects){
                if (objeto.getId().compareTo(id) == 0){
                    o = objeto;
                    logger.info("302: Object found: " + objeto.getNombre());
                }
            }
        }catch(ExceptionInInitializerError e){
            logger.error("400: Object list not initialized");
            return null; //400 ERROR List of Objects not initialized
        }
        return o;
    }

    @Override
    public List<Objeto> getUserObjects(User u) {
        return this.users.get(u.getId()).getObjectList();
    }

    @Override
    public Objeto addObject(Objeto o) {
        logger.info("new Track " + o);
        this.objects.add(o);
        logger.info("new Track added");
        return o;
    }

    public int addObjectToUser(String userID, String objectID){
        User u = getUser(userID);
        Objeto o = getObject(objectID);
        if(u!=null){
            try{
                for(Objeto objeto : this.objects){
                    if (objeto.getId().compareTo(o.getId()) == 0){
                        o = objeto;
                        logger.info("302: Object found: " + objeto.getNombre());
                        objects.add(o);
                        return 200; //OK
                    }
                }
            }catch(ExceptionInInitializerError e){
                logger.error("400: Object list not initialized");
                return 400; //400 ERROR List of Objects not initialized
            }
        }
        return 404;//USER NOT FOUND
    }

    @Override
    public int getNumberObjects(User u) {
        return this.users.get(u.getId()).getObjectList().size();
    }

    /*public Track addTrack(Track t) {
        logger.info("new Track " + t);

        this.tracks.add (t);
        logger.info("new Track added");
        return t;
    }

    public Track addTrack(String title, String singer) {
        return this.addTrack(new Track(title, singer));
    }

    public Track getTrack(String id) {
        logger.info("getTrack("+id+")");

        for (Track t: this.tracks) {
            if (t.getId().equals(id)) {
                logger.info("getTrack("+id+"): "+t);

                return t;
            }
        }

        logger.warn("not found " + id);
        return null;
    }

    public List<Track> findAll() {
        return this.tracks;
    }

    @Override
    public void deleteTrack(String id) {

        Track t = this.getTrack(id);
        if (t==null) {
            logger.warn("not found " + t);
        }
        else logger.info(t+" deleted ");

        this.tracks.remove(t);

    }

    @Override
    public Track updateTrack(Track p) {
        Track t = this.getTrack(p.getId());

        if (t!=null) {
            logger.info(p+" rebut!!!! ");

            t.setSinger(p.getSinger());
            t.setTitle(p.getTitle());

            logger.info(t+" updated ");
        }
        else {
            logger.warn("not found "+p);
        }

        return t;
    }*/
}
