package org.kelvin.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.kelvin.messenger.model.Message;
import org.kelvin.messenger.resources.beans.MessageFilterBean;
import org.kelvin.messenger.services.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET 
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0)
			return messageService.getAllMessagesForYear(filterBean.getYear());
		if (filterBean.getStart() >=0 && filterBean.getSize() > 0)
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		return messageService.getAllMessages(); 
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
		//return messageService.addMessage(message);
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void  deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}

	@Path("/{messageId}/comments")
	public CommentResource comments() {
		return new CommentResource();
	}

}
