/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversoap;

import data.Veiculo;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Gustavo
 */
@WebService(serviceName = "ServerSoap")
public class ServerSoap {

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "adiciona")
    public Veiculo adiciona(@WebParam(name = "codigo") int codigo, @WebParam(name = "placa") String placa, @WebParam(name = "tipo") int tipo, @WebParam(name = "capacidade") int capacidade, @WebParam(name = "unCapacidade") String unCapac) {
        
        Veiculo vel = new Veiculo();
        vel.setCodigo(codigo);
        vel.setPlaca(placa);
        vel.setTipo(tipo);
        vel.setCapacidade(capacidade);
        vel.setUnCapac(unCapac);        
        
        return vel;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "listarTodos")
    public Veiculo listarTodos(@WebParam(name = "tipo") int tipo) {
        
        Veiculo vel = new Veiculo();
        vel.setTipo(tipo);
        vel.getTipo();
        
        
        return vel;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "consultar")
    public Veiculo consultar(@WebParam(name = "codigo") int codigo) {
        
        Veiculo vel = new Veiculo();
        vel.setCodigo(codigo);
        vel.getCodigo();
        return vel;
    }


    /**
     * Operação de Web service
     */
<<<<<<< HEAD
    @WebMethod(operationName = "apagar")
    public Veiculo apagar(@WebParam(name = "codigo") int codigo) {
        //TODO write your implementation code here:
=======
    @WebMethod(operationName = "alterar")
    public Veiculo alterar(@WebParam(name = "codigo") int codigo, @WebParam(name = "placa") char placa, @WebParam(name = "tipo") int tipo, @WebParam(name = "capacidade") int capacidade, @WebParam(name = "unCapacidade") char unCapacidade) {
        
        
>>>>>>> 586bf6d0974f6006a3f3b56b07580baacbb866ca
        return null;
    }

    /**
     * Web service operation
     */
<<<<<<< HEAD
    @WebMethod(operationName = "alterar")
    public Veiculo alterar(@WebParam(name = "codigo") int codigo, @WebParam(name = "placa") String placa, @WebParam(name = "tipo") int tipo, @WebParam(name = "capacidade") int capacidade, @WebParam(name = "unCapacidade") String unCapacidade) {
        //TODO write your implementation code here:
=======
    @WebMethod(operationName = "apagar")
    public Veiculo apagar(@WebParam(name = "codigo") int codigo) {
        
        
>>>>>>> 586bf6d0974f6006a3f3b56b07580baacbb866ca
        return null;
    }

}
