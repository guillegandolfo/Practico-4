package accessoBD;

public class Consultas {
	
	public Consultas() {
	}
	
	public String existeFolios(){
		return "select * from Juridico.Folios where codigo = (?)";
	}
	
	public String MaxFolioId(){
		return "select max(numero) from Juridico.Revisiones where codigoFolio = (?)";
	}
	
	//Revisiones (número INT, codigoFolio VARCHAR(60), descripcion VARCHAR(60)) 
	public String InsertarRevision(){
		return "insert into Juridico.Revisiones Value('?','?','?')";
	}
	
	
	public String insertarFolio(){
		return "INSERT INTO Juridico.Folios VALUES (?,?,?)";
	}
	

}
