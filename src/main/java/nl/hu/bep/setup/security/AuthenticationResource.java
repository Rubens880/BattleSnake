package nl.hu.bep.setup.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Calendar;
import java.util.Map;

@Path("authentication")
public class AuthenticationResource {
    public static final Key key = MacProvider.generateKey();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LogonRequest logonRequest) {
        System.out.println(logonRequest.username + " " + logonRequest.password);
        try {
            String role = MyUser.validateLogin(logonRequest.username, logonRequest.password);
            if (role==null) throw new IllegalAccessException("validation failed");
            String token = createToken(logonRequest.username, role);
            return Response.ok(Map.of("JWT", token)).build();
        }catch (IllegalAccessException e) {
            e.printStackTrace();

        }
        return Response.status(Response.Status.UNAUTHORIZED).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("validate")
    public Response validateUser(ValidateRequest validateRequest) {




        return Response.ok(validateRequest).build();
    }

    private static class ValidateRequest{
        public String JWT;
    }

    private String createToken(String username, String role) throws JwtException {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);


        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

    }

    private static class LogonRequest{
        public String username;
        public String password;
    }


}
