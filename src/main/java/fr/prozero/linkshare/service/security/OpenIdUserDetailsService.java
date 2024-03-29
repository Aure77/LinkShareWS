package fr.prozero.linkshare.service.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import com.google.common.base.Strings;

import fr.prozero.linkshare.model.LsUser;
import fr.prozero.linkshare.service.UserService;

/**
* @author aurelien
*/
public class OpenIdUserDetailsService implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

    @Autowired
    UserService userService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenIdUserDetailsService.class);

    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        LsUser user = null;
        String email = null;
        String firstName = null;
        String lastName = null;
        String fullName = null;
        String urlId = token.getIdentityUrl();

        if (Strings.isNullOrEmpty(urlId)) {
            throw new UsernameNotFoundException("User OpenID is null");
        }

        List<OpenIDAttribute> attributes = token.getAttributes();

        for (OpenIDAttribute attribute : attributes) {
            if ("email".equals(attribute.getName())) {
                email = attribute.getValues().get(0);
            }
            if ("firstname".equals(attribute.getName())) {
                firstName = attribute.getValues().get(0);
            }
            if ("lastname".equals(attribute.getName())) {
                lastName = attribute.getValues().get(0);
            }
            if ("fullname".equals(attribute.getName())) {
                fullName = attribute.getValues().get(0);
            }
        }

        if (Strings.isNullOrEmpty(fullName) && (!Strings.isNullOrEmpty(firstName) || !Strings.isNullOrEmpty(lastName))) {
            fullName = firstName + " " + lastName;
        }
        
        try {
            user = userService.getUserByEmail(email);
        } catch (RuntimeException e) {
            user = null;
            // TODO une exception plus explicite dans le catch
            LOGGER.debug("User email not found, create new user !", e);
        }
        
        if(null == user) {
            user = new LsUser();
            user.setOpenIdUrl(urlId);
            user.setFullname(fullName);
            user.setUserEmail(email);
            user.setEnabled(true);
            userService.createUser(user);
        } else {
            user.setOpenIdUrl(urlId);
            user.setFullname(fullName);
            userService.updateUser(user);
        }
        
        return user;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return userService.getUserByEmail(email);
        } catch (RuntimeException e) {
            LOGGER.debug("user (" + email + ") not found", e);
            throw new UsernameNotFoundException("user (" + email + ") not found", e);
        }
    }

}
