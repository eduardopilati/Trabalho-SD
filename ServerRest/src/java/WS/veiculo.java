/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import com.google.gson.Gson;
import data.Posicao;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import data.Veiculo;
import database.DatabaseException;
import database.DatabaseHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import org.json.JSONObject;


/**
 * REST Web Service
 *
 * @author user
 */
@Path("WS")
public class veiculo {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of veiculo
     */
    public veiculo() {
    }

    @POST
    @Path("/adicionaveiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String adicionaVeiculo(String Veiculo) {
        JSONObject parJsonVeiculo = new JSONObject(Veiculo);
        Veiculo v = new Veiculo();        
        v.setCodigo(Integer.parseInt((String) parJsonVeiculo.get("codigo")));
        v.setPlaca(parJsonVeiculo.get("placa").toString());
        v.setTipo(Integer.parseInt((String) parJsonVeiculo.get("tipo")));
        v.setCapacidade(Integer.parseInt((String) parJsonVeiculo.get("capacidade")));
     //   v.setUnpac(parJsonVeiculo.get("unpac").toString());
        Gson r = new Gson();
        
        try {
            DatabaseHelper.adicionarVeiculo(v);
           /* LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> Adiciona Veiculo", "ServidorREST");
            System.out.println(getDataHora()+" ServidorREST -> Adiciona Veiculo");*/
            return r.toJson(1);
        
        } catch (DatabaseException ex) {
            Logger.getLogger(veiculo.class.getName()).log(Level.SEVERE, null, ex);
            return r.toJson(0);
        }
    }
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("veiculo/{codigo}")
    public String getVeiculo(@PathParam("codigo") Integer codigo) throws SQLException {
        try {
            Veiculo a = new Veiculo();
            a = DatabaseHelper.consultarVeiculo(codigo);
            Gson g = new Gson();
            return g.toJson(a);
        } catch (DatabaseException ex) {
            Logger.getLogger(veiculo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //TODO return proper representation object    
        return null;
    }
/*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/veiculoLista/{tipo}")
    public String getVeiculosPorTipo(@PathParam("tipo") Integer tipo) throws DatabaseException {
        List<Veiculo> listaVeiculo = new ArrayList();
        listaVeiculo = DatabaseHelper.veiculosPorTipo(tipo);
        Gson g = new Gson();
        return g.toJson(listaVeiculo);
    }

   /* @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultarPosicoes/{data}/{veiculo}")
    public String consultarPosicoes(@PathParam("veiculo") Integer veiculo, @PathParam("data") Date data) throws SQLException {
        List<Posicao> lista = new ArrayList();
        lista = DatabaseHelper.consultarPosicoes(veiculo, data);
        Gson g = new Gson();
        return g.toJson(lista);
    }

    @DELETE
    @Path("/removerVeiculo/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteUser(@PathParam("codigo") Integer codigo) {
        try {

            DatabaseHelper.removerVeiculo(codigo);

        } catch (DatabaseException ex) {
            Logger.getLogger(veiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * PUT method for updating or creating an instance of veiculo
     *
     * @param content representation for the resource
     */
    /*
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /*PUT
    @Path("/put/alterar-veiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean alterarVeiculo(RequestBodyREST requestBodyREST) throws Exception {
        try {
            Veiculo v = new Veiculo(requestBodyREST.cdVeiculo,
                    requestBodyREST.dsPlaca,
                    requestBodyREST.idTipo,
                    requestBodyREST.vlCapacidade,
                    requestBodyREST.dsUnidade);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.alterar(v);
            Logger.logMethod("Rest", "");
            return true;
        } catch (SQLException e) {
            Logger.logMethod("Rest", e.getMessage());
            throw new Exception("Não foi possivel alterar o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("Rest", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }*/ /*
    @POST
    @Path("/postVeiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    public String postJson(String parVeiculo) throws Exception {
     /*   JSONObject parJsonVeiculo = new JSONObject(parVeiculo);
        Veiculo v = new Veiculo();
        v.setCodigo((Integer) parJsonVeiculo.get("codigo"));
        v.setPlaca(parJsonVeiculo.get("placa").toString());
        v.setTipo((Integer) parJsonVeiculo.get("tipo"));
        v.setCapacidade((Integer) parJsonVeiculo.get("capacidade"));
        Operacoes.beginReplica();
        Operacoes.adicionaVeiculo(v);
        Operacoes.endReplica();
        Gson r = new Gson();
 } */
}    
