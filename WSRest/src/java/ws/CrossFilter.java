package ws;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CrossFilter implements ClientRequestFilter, ClientResponseFilter, ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext) {
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
    }

    /*
    * Habilitar CORS para funcionar com o AngularJS
    */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
       responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
       responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
       responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
       responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
       responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
   
    
}