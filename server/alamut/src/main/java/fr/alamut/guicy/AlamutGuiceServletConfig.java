package fr.alamut.guicy;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class AlamutGuiceServletConfig extends GuiceServletContextListener {
	private static final String BAAS_SERVER_RESOURCES_PACKAGE = "com.perso.baas.server.resources";
    private static final String JERSEY_API_JSON_POJO_MAPPING_FEATURE = "com.sun.jersey.api.json.POJOMappingFeature";
    private static final String JERSEY_CONFIG_PROPERTY_PACKAGES = "com.sun.jersey.config.property.packages";

    
	@Override
	protected Injector getInjector() {
		final Map<String, String> params = new HashMap<String, String>();

        params.put(JERSEY_CONFIG_PROPERTY_PACKAGES, BAAS_SERVER_RESOURCES_PACKAGE);
        params.put(JERSEY_API_JSON_POJO_MAPPING_FEATURE, "true");


        
		return Guice.createInjector(new JerseyServletModule() {
			@Override
			protected void configureServlets() {
				// Must configure at least one JAX-RS resource or the
				// server will fail to start.
				bind(HelloGuice.class);

				// Route all requests through GuiceContainer
				serve("/*").with(GuiceContainer.class,params);
			}
		});
	}
}