/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import data.Veiculo;
import database.DatabaseException;
import database.DatabaseHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("ws")
public class wsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of wsResource
     */
    public wsResource() {
    }

    /**
     * Retrieves representation of an instance of ws.wsResource
     * @param codigo
     * @return an instance of java.lang.String
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/veiculo/{codigo}")
    public String getVeiculo(@PathParam("codigo") Integer codigo) throws SQLException {
        Gson g = new Gson();
        try {
            System.out.println("passou");
           // JSONObject obj = new JSONObject();
            Veiculo v = new Veiculo();
            v = DatabaseHelper.consultarVeiculo(codigo);
        /*    obj.put("codigo", v.getCodigo());
            obj.put("placa", v.getPlaca());
            obj.put("tipo", v.getTipo());
            obj.put("capacidade", v.getCapacidade());
            obj.put("uncapac", v.getUnCapac()); 
            */
            System.out.println(g.toJson(v));
            return g.toJson(v);
            
        } catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Veiculo.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson(1);
        }

        //TODO return proper representation object    
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/listaVeiculo/{tipo}")
    public String getVeiculoTipo(@PathParam("tipo") Integer tipo){
        List<Veiculo> VeicLista = new ArrayList();
        Gson g = new Gson();
        try{
            // Veiculo v = new Veiculo();
            VeicLista = DatabaseHelper.veiculosPorTipo(tipo);
            return g.toJson(VeicLista);
        }
        catch(DatabaseException ex){
            System.out.println(ex.getMessage());
            return null;
        }
       
        
    }

            
    /**
     * PUT method for updating or creating an instance of wsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @POST
    @Path("/adicionaveiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String adicionaVeiculo(String Veiculo) {
        JSONObject parJsonVeiculo = new JSONObject(Veiculo);
        System.out.println("passou adicionar");
        Veiculo v = new Veiculo();        
       // v.setCodigo(Integer.parseInt((String) parJsonVeiculo.get("codigo")));
        v.setPlaca(parJsonVeiculo.get("placa").toString());
        v.setTipo((Integer) parJsonVeiculo.get("tipo"));
        v.setCapacidade((Integer) parJsonVeiculo.get("capacidade"));
        v.setUnCapac(parJsonVeiculo.get("unCapac").toString());        
        Gson r = new Gson();
        
        try {
            System.out.println("passou adicionar 2");
            DatabaseHelper.adicionarVeiculo(v);
            System.out.println(r.toJson(v));
            return r.toJson(v);
        
        } catch (DatabaseException ex) {
            Logger.getLogger(Veiculo.class.getName()).log(Level.SEVERE, null, ex);
           // System.out.println("erro " + ex.getMessage());
            return r.toJson(0);
        }
    }
}
