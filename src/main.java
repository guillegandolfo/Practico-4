import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Excepciones.Exc_Persistencia;
import persistencia.Propiedades;




public class main
{
	public static void main (final String[] args) throws Exc_Persistencia, IOException
	{
		try
			{

				Propiedades p = new Propiedades();
				String driver = p.buscar("driver");
				String url = p.buscar("url");
				String usuario = p.buscar("usuario");
				String password = p.buscar("password");

				// 1. cargo dinamicamente el driver de MySQL 
				Class.forName(driver);

				// 2. una vez cargado el driver, me conecto con la base de datos 
				Connection con = DriverManager.getConnection(url, usuario, password);
				
				PreparedStatement pstmt = con.prepareStatement("CREATE DataBase Juridico");
				pstmt.execute();
				System.out.println("Creo la base de datos");
				
				pstmt = con.prepareStatement("CREATE Table Juridico.Folios(Codigo Varchar(60) not null, caratula Varchar(60) not null, paginas int not null, primary key (Codigo))");
				pstmt.execute();
				System.out.println("Creo la tabla Folios");
				
				pstmt = con.prepareStatement("CREATE Table Juridico.Revisiones(\r\n" + 
						"numero int not null,\r\n" + 
						"codigoFolio Varchar(60) not null,\r\n" + 
						"descripcion Varchar(60) not null,\r\n" + 
						"primary key (numero, codigoFolio),\r\n" + 
						"CONSTRAINT fk_codigoFolio FOREIGN KEY (codigoFolio) REFERENCES Juridico.Folios (Codigo)\r\n" + 
						");");
				pstmt.execute();
				System.out.println("Creo la tabla Revisiones");
				
				String insert = "INSERT INTO Juridico.Folios VALUES (?,?,?)";
				pstmt = con.prepareStatement(insert);
				pstmt.setString(1, "FGH-0015");
				pstmt.setString(2, "La comuna contra la se�ora con 38 gatos");
				pstmt.setInt(3, 5);
				pstmt.executeUpdate();
				System.out.println("Inserte primer tupla");	

				insert = "INSERT INTO Juridico.Folios VALUES (?,?,?)";
				pstmt = con.prepareStatement(insert);
				pstmt.setString(1, "BBD-1278");
				pstmt.setString(2, "Adolescentes descontrolados hasta las 5 AM");
				pstmt.setInt(3, 2);
				pstmt.executeUpdate();
				System.out.println("Inserte segunda tupla");					
				
				insert = "INSERT INTO Juridico.Folios VALUES (?,?,?)";
				pstmt = con.prepareStatement(insert);
				pstmt.setString(1, "JJ-202 ");
				pstmt.setString(2, "Vecinos reclaman por heces de perro en el hall");
				pstmt.setInt(3, 9);
				pstmt.executeUpdate();
				System.out.println("Inserte tercer tupla");		
				
				insert = "INSERT INTO Juridico.Folios VALUES (?,?,?)";
				pstmt = con.prepareStatement(insert);
				pstmt.setString(1, "CEFJ-63");
				pstmt.setString(2, "Vecinas rivales se tiran macetas con frecuencia");
				pstmt.setInt(3, 463);
				pstmt.executeUpdate();
				System.out.println("Inserte cuarta tupla");			

				pstmt.close();
				con.close();
				
				
				System.out.println("EXITO!!!");	
				
			}
			catch (SQLException  e){	
				e.printStackTrace();
			}
			catch (ClassNotFoundException  e){	
				e.printStackTrace();
			}

	}

}
