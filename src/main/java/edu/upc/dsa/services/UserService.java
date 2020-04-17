package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Api(value = "/users", description = "Endpoint to Track Service")
@Path("/users")
public class UserService {

    private GameManager gm;

    public UserService() {
        this.gm = GameManagerImpl.getInstance();
        if (gm.getNumberUsers()==0) {
            //Adding users
            this.gm.addUser("1", "Chuli", "calvo");
            this.gm.addUser("2", "Ivan", "daBest");
            //Adding objects
            this.gm.addObject(new Objeto("1", "Martillo"));
            this.gm.addObject(new Objeto("2", "Espada"));
            this.gm.addObject(new Objeto("3", "Satisfyer"));
            //Add objects to players
            this.gm.addObjectToUser("1", "3");
            this.gm.addObjectToUser("1", "1");
            this.gm.addObjectToUser("2", "2");
        }
    }

    //GET usuarios ordenados alfabeticamente
    @GET
    @ApiOperation(value = "get all Users ordered", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/listUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.gm.getUserAlphOrdered();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }

    //Add a user
    @POST
    @ApiOperation(value = "add a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/addUser/{id}/{name}/{surname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@PathParam("id") String id, @PathParam("name") String name, @PathParam("surname") String surname) {
        int res = this.gm.addUser(id,name,surname);
        if (res == 400) return Response.status(404).build();
        else  return Response.status(201).build();
    }

    //Update a user
    @PUT
    @ApiOperation(value = "update User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/updateUser/{id}/{name}/{surname}")
    public Response updateUser(@PathParam("id") String id, @PathParam("name") String name, @PathParam("surname") String surname) {

        int res = this.gm.updateUser(id,name,surname);
        if (res == 400) return Response.status(404).build();
        return Response.status(201).build();
    }

    //GET numero de usuarios
    @GET
    @ApiOperation(value = "get number Users", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/listUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberUsers() {
        this.gm.getNumberUsers();
        return Response.status(201).build()  ;
    }

    //GET info de un usuario
    @GET
    @ApiOperation(value = "get a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/listUsers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {

        User u = this.gm.getUser(id);
        GenericEntity<User> entity = new GenericEntity<User>(u) {};
        if(u == null) Response.status(404).build();
        return Response.status(201).entity(entity).build()  ;

    }

    //Add object to user
    @PUT
    @ApiOperation(value = "add Object", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/addObject/{userId}/{objectId}")
    public Response addObjectToUser(@PathParam("userId") String userId, @PathParam("objectId") String objectId) {
        User u = this.gm.getUser(userId);
        Objeto objeto = this.gm.getObject(objectId);
        if(u==null || objeto==null) Response.status(404).build();
        else {
            this.gm.addObjectToUser(userId,objectId);
        }
        return Response.status(201).entity(gm.getUser(userId)).build();
    }

    //GET objetos de un usuario
    @GET
    @ApiOperation(value = "get User objects", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/listUsers/{id}/objects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserObjects(@PathParam("id") String id) {

        User u = this.gm.getUser(id);
        List<Objeto> objetos = u.getObjectList();
        if(objetos == null) Response.status(404).build();
        return Response.status(201).entity(objetos).build()  ;

    }

    //GET numero objetos de usuario
    @GET
    @ApiOperation(value = "get User objects", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/listUsers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberObjects(@PathParam("id") String id) {

        User u = this.gm.getUser(id);
        int res = this.gm.getNumberObjects(u);
        if(res == 0) Response.status(404).build();
        return Response.status(201).entity(res).build()  ;

    }
}