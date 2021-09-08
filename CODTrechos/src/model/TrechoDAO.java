package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;

import java.util.*;

import javax.swing.JOptionPane;

import Connection.ConnectionFactory;

public class TrechoDAO {

	public String titu;
	
	public void criartrecho(Trecho t) {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO trechos (T_titulo, T_linguagem, T_codigo)VALUES(?,?,?)");
			stmt.setString(1, t.getTitulo());
			stmt.setString(2, t.getLinguagem());
			stmt.setString(3, t.getConteudo());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
			
		} catch (SQLException e) {

			
			try {
				stmt = con.prepareStatement("UPDATE trechos SET T_codigo = '"+t.getConteudo()+"' where T_titulo = '"+t.getTitulo()+"'");
				stmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
				
			} catch (SQLException es) {

				JOptionPane.showMessageDialog(null, "Erro ao Atualizar / Salvar" + es);
				
			}
			
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Trecho> listartrechos(){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		
		List<Trecho> trechos = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM trechos order by T_titulo");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
			
				Trecho t = new Trecho();
				
				
				t.setId(rs.getInt("T_id"));
				t.setTitulo(rs.getString("T_titulo"));
				t.setLinguagem(rs.getString("T_linguagem"));
				t.setConteudo(rs.getString("T_codigo"));
				
				trechos.add(t);
				
			}
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Listar :" + e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
	
		return trechos;
		
	}
	
	public void pegarcod(String titulo) {
		this.titu = titulo;
	}
	
	public String pegarcodigo(){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		
		String trechos = "";
		
		try {
			stmt = con.prepareStatement("SELECT (T_codigo) FROM trechos where T_titulo = '"+this.titu+"'");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
			
				Trecho t = new Trecho();
				
				t.setConteudo(rs.getString("T_codigo"));
				
				trechos = t.getConteudo();
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Listar :" + e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return trechos;
		
	}
	
	
public void atualizartrecho(String id, String titulo, String linguagem, String codigo) {
	
	Connection con = ConnectionFactory.getConnection();
	PreparedStatement stmt = null;
	
	try {
		stmt = con.prepareStatement("UPDATE trechos SET T_titulo = '" +titulo+"', T_linguagem = '"+linguagem+"', T_codigo = '"+codigo+"' where T_id = '"+id+"'");
				
		stmt.executeUpdate();
		
		JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
		
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Erro ao Atualizar :" + e);
	}finally {
		ConnectionFactory.closeConnection(con, stmt);
	}
}
	
	
}
