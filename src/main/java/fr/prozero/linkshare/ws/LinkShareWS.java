package fr.prozero.linkshare.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.prozero.linkshare.model.Link;
import fr.prozero.linkshare.model.User;
import fr.prozero.linkshare.service.LinkShareService;
import fr.prozero.linkshare.service.UserService;

@Component
@Path("/share")
public class LinkShareWS {	

	@Autowired
	private LinkShareService linkshareService;
	
	@Autowired
	private UserService userService;

	@GET
	@Path("/{userId}")
	@Produces({ MediaType.APPLICATION_JSON/*, MediaType.APPLICATION_XML*/ })
	public Response getUserLinks(@PathParam("userId") Long userId) {
		if(null == userId)
			throw new WebApplicationException(Status.BAD_REQUEST);	
		final User user = userService.getUserById(userId);
		if(null == user)
			throw new WebApplicationException(Status.BAD_REQUEST);
		final List<Link> links = linkshareService.getLastUserLinks(user);
		final GenericEntity<List<Link>> linkList = new GenericEntity<List<Link>>(links) {};
		return Response.ok().entity(linkList).build();
	}

	@POST
	@Path("/{userId}")
	@Produces({ MediaType.APPLICATION_JSON/*, MediaType.APPLICATION_XML*/ })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addLink(@PathParam("userId") Long userId, @FormParam("link") String link) {
		final User user = userService.getUserById(userId);
		if(null == user)
			throw new WebApplicationException(Status.BAD_REQUEST);
		final Link newLink = linkshareService.addLinkToUser(user, link);
		
		return Response.ok().entity(newLink).build();
	}
}
