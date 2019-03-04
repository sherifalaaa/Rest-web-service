package sherif.messengerApp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotation")
	public String getParamUsingAnnotation (@MatrixParam("param")String matrixParam)
	{
		return "Matrix param: " + matrixParam;
	}
	
	@GET
	@Path("context")
	public String getParaUsingContext(@Context UriInfo uriInfo , @Context HttpHeaders header)
	{
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = header.getCookies().toString();
		return "path " + path + "cookies " + cookies; 
	}
	

}
