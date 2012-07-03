package fr.prozero.linkshare.ws;

import java.util.List;

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

import com.google.common.base.Strings;

import fr.prozero.linkshare.crypto.AESCrypter;
import fr.prozero.linkshare.model.LsLink;
import fr.prozero.linkshare.model.LsUser;
import fr.prozero.linkshare.service.LinkShareService;
import fr.prozero.linkshare.service.UserService;
import fr.prozero.linkshare.util.LinkShareSecurity;

@Component
@Path("/share")
public class LinkShareWS {	

	@Autowired
	private LinkShareService linkshareService;
	
	@Autowired
	private UserService userService;

	@GET
	@Path("/{userEmailEncrypted}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUserLinks(@PathParam("userEmailEncrypted") String userEmailEncrypted) {
		
		if(Strings.isNullOrEmpty(userEmailEncrypted))
			throw new WebApplicationException(Status.BAD_REQUEST);	
		
		AESCrypter crypter = new AESCrypter(LinkShareSecurity.INSTANCE.getSecretLsKey());
		final String userEmail = crypter.decrypt(userEmailEncrypted);
		
		if(Strings.isNullOrEmpty(userEmail))
			throw new WebApplicationException(Status.BAD_REQUEST);	
		
		final LsUser user = userService.getUserByEmail(userEmail);
		if(null == user)
			throw new WebApplicationException(Status.BAD_REQUEST);
		
		final List<LsLink> links = linkshareService.getLastUserLinks(user);
		final GenericEntity<List<LsLink>> linkList = new GenericEntity<List<LsLink>>(links) {};
		return Response.ok().entity(linkList).build();
	}

	@POST
	@Path("/{userEmailEncrypted}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addLink(@PathParam("userEmailEncrypted") String userEmailEncrypted, @FormParam("link") String link) {
		
		AESCrypter crypter = new AESCrypter(LinkShareSecurity.INSTANCE.getSecretLsKey());
		final String userEmail = crypter.decrypt(userEmailEncrypted);
		
		final LsUser user = userService.getUserByEmail(userEmail);
		if(null == user)
			throw new WebApplicationException(Status.BAD_REQUEST);
		
		final LsLink newLink = linkshareService.addLinkToUser(user, link);		
		return Response.ok().entity(newLink).build();
	}
}
