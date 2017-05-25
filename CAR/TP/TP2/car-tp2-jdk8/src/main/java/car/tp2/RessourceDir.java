package car.tp2;


import java.io.IOException;
import java.net.SocketException;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

@Path("/ftp")
public class RessourceDir {

	@Produces({MediaType.TEXT_HTML})
	@Path("/{path:.*}")
	@GET
	public String getDirectory(@PathParam("path") String path) throws SocketException, IOException {
		FTPClient client = new FTPClient();
		
		client.connect("127.0.0.1", 2121);
		client.login("admin","admin");
		
		FTPFile[] files = client.listFiles(path);
		
		return ConstructeurHTML.getInstance().build(path, files);
	}
	
	@Path("/{path:.*}")
	@POST
	public void createDirectory(@PathParam("path") String path, @FormParam("dirName") String directoryName) throws SocketException, IOException{
		FTPClient client = new FTPClient();
		
		client.connect("127.0.0.1", 2121);
		client.login("admin","admin");
		
		client.makeDirectory(path+directoryName);
	}
	
	@Path("/{path:.*}")
	@DELETE
	public void deleteDirectory(@PathParam("path") String path) throws SocketException, IOException{
		FTPClient client = new FTPClient();
		
		client.connect("127.0.0.1", 2121);
		client.login("admin","admin");
		
		client.removeDirectory(path);
	}
	
	@Path("/{path:.*}")
	@PUT
	public void renameDirectory(@PathParam("path") String path, @FormParam("newName") String newDirectoryName) throws SocketException, IOException{
		FTPClient client = new FTPClient();
		
		client.connect("127.0.0.1", 2121);
		client.login("admin","admin");
		
		String ancienNom = path;
		if(path.endsWith("/")){
			ancienNom = ancienNom.substring(0, ancienNom.length()-1);
		}
		
		ancienNom = ancienNom.substring(ancienNom.lastIndexOf("/")+1);
		
		client.rename(ancienNom, newDirectoryName);
	}
	
}
