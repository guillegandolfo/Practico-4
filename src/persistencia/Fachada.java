package persistencia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import VO.VoFolio;
import Excepciones.Exc_Persistencia;
import accessoBD.Consultas;


public class Fachada extends UnicastRemoteObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Fachada instancia;
	private String driver;
	private String url;
	private String usuario;
	private String password;

	
	public static Fachada getInstancia() throws Exc_Persistencia, ClassNotFoundException{
		if(instancia == null){
			try {
				instancia = new Fachada();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	private Fachada() throws RemoteException, Exc_Persistencia, ClassNotFoundException{
		Propiedades p = new Propiedades();
		String driver = p.buscar("driver");
		String url = p.buscar("url");
		String usuario = p.buscar("usuario");
		String password = p.buscar("password");
		Class.forName(this.driver);
	}


	public String getDriver() {
		return driver;
	}


	public String getUrl() {
		return url;
	}


	public String getUsuario() {
		return usuario;
	}


	public String getPassword() {
		return password;
	}


	public void agregarFolio(VoFolio VoF) throws SQLException{

		Connection con = DriverManager.getConnection(url, usuario, password);
		Consultas consulta = new Consultas();
		String query = consulta.existeFolios();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, VoF.getCodigo());
		ResultSet rs = pstmt.executeQuery();
		//Consulto si existe 
		if (rs.next()){
			//no hago nada por que existe el Folio
		}else{
			query = consulta.insertarFolio();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, VoF.getCodigo());
			pstmt.setString(2, VoF.getCaratula());
			pstmt.setInt(3, VoF.getPaginas());
			pstmt.executeUpdate();
		}
	
		rs.close();
		pstmt.close();
		con.close();
		
	}
	
	public void agregarRevision( String CodFolio, String Desc ) throws SQLException{

		Connection con = DriverManager.getConnection(url, usuario, password);
		Consultas consulta = new Consultas();
		String query = consulta.existeFolios();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, CodFolio);
		ResultSet rs = pstmt.executeQuery();
		//Consulto si existe 
		if (rs.next()){
			//no hago nada por que existe el Folio
		}else
		{
			int maxrevisionid;
			query = consulta.MaxFolioId();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, CodFolio);
			rs = pstmt.executeQuery();
			if (rs.next()){
				 maxrevisionid = rs.getInt(1);
			}else
			{
				maxrevisionid = 0;
			}
			maxrevisionid ++;
			query = consulta.InsertarRevision();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,maxrevisionid );
			pstmt.setString(2, CodFolio);
			pstmt.setString(3, Desc);
			pstmt.executeUpdate();
			rs.close();
			pstmt.close();
			con.close();
			
		}	
			}

	public void borrarFolioRevisiones(){
		
	}
	
	public String darDescripcion(){
		return "";
	}


}

