package fr.prozero.linkshare.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.prozero.linkshare.crypto.AESCrypter;
import fr.prozero.linkshare.model.LsUser;
import fr.prozero.linkshare.service.UserService;
import fr.prozero.linkshare.util.LinkShareSecurity;

@Component
@Path("/user")
public class UserWS {
	
	@Autowired
	private UserService userService;
	
	@GET
	@Path("/{userEmailEncrypted}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createUserIfNotExist(@PathParam("userEmailEncrypted") String userEmailEncrypted) {
		
		AESCrypter crypter = new AESCrypter(LinkShareSecurity.INSTANCE.getSecretLsKey());
		final String userEmail = crypter.decrypt(userEmailEncrypted);
		
		LsUser user = userService.getUserByEmail(userEmail);
		if(null == user) {
			user = new LsUser();
			user.setUserEmail(userEmail);
			user.setEnabled(true);
			user = userService.createUser(user);
		}		
		return Response.ok().build();
	}
	
}
