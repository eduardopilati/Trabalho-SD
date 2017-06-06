package database;

import data.Posicao;
import data.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper {
    
    public static void adicionarVeiculo(Veiculo veiculo) throws DatabaseException {
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO veiculo(placa, tipo, capacidade, un_capac) values(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, veiculo.getPlaca());
            stmt.setInt(2, veiculo.getTipo());
            stmt.setInt(3, veiculo.getCapacidade());
            stmt.setString(4, veiculo.getUnCapac());
            stmt.execute();
            
            stmt.close();
            conn.close();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static void alterarVeiculo(Veiculo veiculo) throws DatabaseException {
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "UPDATE veiculo SET placa=?, tipo=?, capacidade=?, un_capac=? where codigo=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, veiculo.getPlaca());
            stmt.setInt(2, veiculo.getTipo());
            stmt.setInt(3, veiculo.getCapacidade());
            stmt.setString(4, veiculo.getUnCapac());
            stmt.setInt(5, veiculo.getCodigo());
            stmt.execute();
            
            stmt.close();
            conn.close();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static void removerVeiculo(Veiculo veiculo) throws DatabaseException {
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "delete from veiculo where codigo=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, veiculo.getCodigo());
            stmt.execute();
            
            stmt.close();
            conn.close();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static void removerVeiculo(Integer codigo) throws DatabaseException {
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "delete from veiculo where codigo=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, codigo);
            stmt.execute();
            
            stmt.close();
            conn.close();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static Veiculo consultarVeiculo(Integer codigo) throws DatabaseException {
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from veiculo where codigo=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            
            Veiculo veiculo;
            if(rs.next()){
                veiculo = converterVeiculo(rs);
            }else{
                veiculo = null;
            }
            
            rs.close();
            stmt.close();
            conn.close();
            return veiculo;
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static Veiculo consultarVeiculo(String placa) throws DatabaseException {
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from veiculo where placa=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            
            Veiculo veiculo;
            if(rs.next()){
                veiculo = converterVeiculo(rs);
            }else{
                veiculo = null;
            }
            
            rs.close();
            stmt.close();
            conn.close();
            return veiculo;
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static List<Veiculo> veiculosPorTipo(Integer tipo) throws DatabaseException{
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from veiculo where tipo=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, tipo);
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Veiculo> veiculos = new ArrayList<>();
            while(rs.next()){
                veiculos.add(converterVeiculo(rs));
            }
            
            rs.close();
            stmt.close();
            conn.close();
            return veiculos;
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static List<Integer> codigoTodosVeiculos() throws DatabaseException{
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select codigo from veiculo";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Integer> codigos = new ArrayList<>();
            while(rs.next()){
                codigos.add(rs.getInt("codigo"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
            return codigos;
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static List<Posicao> consultarPosicoes(Veiculo veiculo) throws DatabaseException{
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from posicao where codigo=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, veiculo.getCodigo());
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Posicao> posicoes = new ArrayList<>();
            while(rs.next()){
                posicoes.add(converterPosicao(rs));
            }
            
            rs.close();
            stmt.close();
            conn.close();
            return posicoes;
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static List<Posicao> consultarPosicoes(Veiculo veiculo, Date data) throws DatabaseException{
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from posicao where codigo=? and data_hora >= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, veiculo.getCodigo());
            stmt.setDate(2, new java.sql.Date(data.getTime()));
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Posicao> posicoes = new ArrayList<>();
            while(rs.next()){
                posicoes.add(converterPosicao(rs));
            }
            
            rs.close();
            stmt.close();
            conn.close();
            return posicoes;
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }
    
    public static void adicionarPosicao(Posicao posicao) throws DatabaseException {
        try{
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO posicao(codigo, data_hora, latitude, longitude) values(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, posicao.getCodigo());
            stmt.setDate(2, new java.sql.Date(posicao.getData().getTime()));
            stmt.setFloat(3, posicao.getLatitude());
            stmt.setFloat(4, posicao.getLongitude());
            stmt.execute();
            
            stmt.close();
            conn.close();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    private static Veiculo converterVeiculo(ResultSet rs) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setCodigo(rs.getInt("codigo"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setTipo(rs.getInt("tipo"));
        veiculo.setCapacidade(rs.getInt("capacidade"));
        veiculo.setUnCapac(rs.getString("un_capac"));
        return veiculo;
    }

    private static Posicao converterPosicao(ResultSet rs) throws SQLException {
        Posicao posicao = new Posicao();
        posicao.setCodigo(rs.getInt("codigo"));
        posicao.setSeq(rs.getInt("seq"));
        posicao.setData(rs.getDate("data_hora"));
        posicao.setLatitude(rs.getFloat("latitude"));
        posicao.setLongitude(rs.getFloat("longitude"));
        return posicao;
    }
}
