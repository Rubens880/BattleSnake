package nl.hu.bep.setup.setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("nl.hu.bep.setup.game.webservices, nl.hu.bep.setup.security");
        register(RolesAllowedDynamicFeature.class);
    }
}
