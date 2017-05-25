package car.tp2;


import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import javax.ws.rs.Consumes;
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
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

@Path("/ftp/file")
public class RessourceFile {

	@Produces({MediaType.APPLICATION_OCTET_STREAM})
	@Path("/{path:.*}")
	@GET
	public InputStream getFile(@PathParam("path") String path) throws SocketException, IOException {
		FTPClient client = new FTPClient();
		
		client.connect("127.0.0.1", 2121);
		client.login("admin","admin");
		
		InputStream fileInputStream = client.retrieveFileStream(path);
		return fileInputStream;
	}
	
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@Path("/{path:.*}")
	@POST
	public void createFile(@PathParam("path") String path, MultipartBody body) throws SocketException, IOException{
		FTPClient client = new FTPClient();
		
		client.connect("127.0.0.1", 2121);
		client.login("admin","admin");
		
		Attachment root = body.getRootAttachment();
		String name = root.getContentDisposition().getParameter("fileName");
		
		client.storeFile(path+name, root.getDataHandler().getInputStream());
	}
	
	@Path("/{path:.*}")
	@DELETE
	public void deleteFile(@PathParam("path") String path) throws SocketException, IOException{
		FTPClient client = new FTPClient();
		
		client.connect("127.0.0.1", 2121);
		client.login("admin","admin");
		
		client.deleteFile(path);
	}
	
	@Path("/{path:.*}")
	@PUT
	public void renameFile(@PathParam("path") String path, @FormParam("newName") String newFileName) throws SocketException, IOException{
		FTPClient client = new FTPClient();
		
		client.connect("127.0.0.1", 2121);
		client.login("admin","admin");
		
		String ancienNom = path;
		if(path.endsWith("/")){
			ancienNom = ancienNom.substring(0, ancienNom.length()-1);
		}
		
		ancienNom = ancienNom.substring(ancienNom.lastIndexOf("/")+1);
		
		client.rename(ancienNom, newFileName);
	}
}
