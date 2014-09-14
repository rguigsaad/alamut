package fr.alamut.module;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import fr.alamut.controler.UserControler;
import fr.alamut.dao.UserDao;
import fr.alamut.dao.impl.UserDaoImpl;
import fr.alamut.rest.UserService;

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
				Properties properties = loadProperties("services.properties");
				Names.bindProperties(binder(), properties);
				//bind the data source
				bind(DataSource.class).toProvider(H2DataSourceProvider.class).in(Scopes.SINGLETON);

				bind(UserDaoImpl.class).in(Singleton.class);
				bind(UserDao.class).to(UserDaoImpl.class);
				bind(UserControler.class);
				// Must configure at least one JAX-RS resource or the
				// server will fail to start.
				bind(UserService.class);

				// Route all requests through GuiceContainer
				serve("/*").with(GuiceContainer.class,params);
			}
		});
	}
	
	private Properties loadProperties(String name) {
		Properties properties = new Properties();

		try{
			properties.load(getClass().getClassLoader().getResourceAsStream(name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return properties;
	}
	
	static class H2DataSourceProvider implements Provider<DataSource> {

		private final String url;
		private final String username;
		private final String password;

		@Inject
		public H2DataSourceProvider(@Named("url") final String url,
				@Named("username") final String username,
				@Named("password") final String password) {
			this.url = url;
			this.username = username;
			this.password = password;
		}

		public DataSource get() {
			final JdbcDataSource dataSource = new JdbcDataSource();
			dataSource.setURL(url);
			dataSource.setUser(username);
			dataSource.setPassword(password);
			return dataSource;
		}
	}
}