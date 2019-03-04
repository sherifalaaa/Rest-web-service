package sherif.messengerApp.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;




import sherif.messengerApp.model.Message;
import sherif.messengerApp.resources.beans.MessageFilterBean;
import sherif.messengerApp.service.MessageService;


@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean)
	{
		if (filterBean.getYear() > 0)
		{
			return messageService.getAllMessageForYear(filterBean.getYear());
		}
		
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0)
		{
			return messageService.getAllMessagePagination(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response addMessage(Message message ,@Context UriInfo uriInfo) 
	{
		Message newMessage = messageService.addMessage(message);
	    return Response.created(uriInfo.getAbsolutePathBuilder().build()).entity(newMessage).build();
		
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Message updateMessage(@PathParam("messageId") Long id, Message message)
	{
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public void deleteMessage (@PathParam("messageId") Long id)
	{
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Message getMessage(@PathParam("messageId" )Long id, @Context UriInfo uriInfo)
	{
		 Message message = messageService.getMessage(id);
		 message.addLink(getUriForSelf(uriInfo, message), "self");
		 message.addLink(getUriForProfile(uriInfo, message), "profile");
		 message.addLink(getUriForComment(uriInfo, message), "comment");
		 
		return message;
		
	}
	



	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}
	
	private String getUriForComment(UriInfo uriInfo, Message message) {
		String url =  uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.resolveTemplate("messageId", message.getId())
				.path(CommentResource.class)
				.build()
				.toString();
				return url;
	}
	
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String url =  uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
				return url;
	}
	
	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String url =  uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(Long.toString(message.getId()))
		.build()
		.toString();
		return url;
	}
	
	

}
