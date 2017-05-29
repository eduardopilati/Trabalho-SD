package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author eduardo.pilati
 */
public class Utilidades {
    public static byte[] serializar(Object objeto) throws IOException{
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream()) {
            try (ObjectOutputStream ous = new ObjectOutputStream(bao)) {
                ous.writeObject(objeto);
                return bao.toByteArray();
            }
        }
    }
    
    public static <T> T desserializar(byte[] object) throws IOException, ClassNotFoundException{
        try (ByteArrayInputStream bai = new ByteArrayInputStream(object)){
            try (ObjectInputStream ois = new ObjectInputStream(bai)) {
                return (T) ois.readObject();
            }
        }
    }
}
