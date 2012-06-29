package fr.prozero.linkshare.ws;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

import fr.prozero.linkshare.model.Link;

/**
 * Enables the "natural" json generator.
 * natural generator generates json arrays always properly, 
 * instead of only when the array has more than one element.
 */
@Provider
public class CustomJAXBContextResolver implements ContextResolver<JAXBContext> {

	private JAXBContext context;
	private Class<?>[] types = { Link.class };	
	
	public CustomJAXBContextResolver() throws JAXBException {
		this.context = new JSONJAXBContext(			
				JSONConfiguration.natural().build(), types);
	}

	@Override
	public JAXBContext getContext(Class<?> objectType) {
		for (Class<?> type : types) {
			if (type.equals(objectType)) {
				return context;
			}
		}
		return null;
	}

}
