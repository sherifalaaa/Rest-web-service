package sherif.messengerApp.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sherif.messengerApp.model.Comment;
import sherif.messengerApp.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CommentResource {
	
	CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getComments(@PathParam("messageId") Long messageId)
	{
		
		return commentService.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam("commentId") Long messageId,Comment comment)
	{
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment (@PathParam("messageId") Long messageId,@PathParam("commentId") Long id,Comment comment)
	{
		comment.setId(id);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void removeComment(@PathParam("messageId") Long messageId,@PathParam("commentId") Long commentId)
	{
		 commentService.removeComment(messageId, commentId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment (@PathParam("messageId") Long messageId,@PathParam("commentId") Long commentId)
	{
		return commentService.getComment(messageId, commentId);
	}

}
