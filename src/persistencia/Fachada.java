package persistencia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import Excepciones.Exc_Persistencia;



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

	
	public static Fachada getInstancia() throws Exc_Persistencia{
		if(instancia == null){
			try {
				instancia = new Fachada();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	private Fachada() throws RemoteException, Exc_Persistencia{
		Propiedades p = new Propiedades();
		String driver = p.buscar("driver");
		String url = p.buscar("url");
		String usuario = p.buscar("usuario");
		String password = p.buscar("password");

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


	public void agregarFolio(){
		
	}
	
	public void agregarRevision(){
		
	}
	
	public void borrarFolioRevisiones(){
		
	}
	
	public String darDescripcion(){
		return "";
	}


}

