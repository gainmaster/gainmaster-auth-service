package gainmaster.service.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;

@RestController
public class RevokeTokenController {

    @Autowired
    private AuthorizationServerTokenServices tokenServices;

    @RequestMapping(value = "/oauth/revoke", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestParam("token") String value) throws InvalidClientException {
        /*
        if(tokenServices){
            return new ResponseEntity<String>("{\"status\": \"revoked\", \"status_description\": \"Token successfully revoked\"}", HttpStatus.OK);
        };
        return new ResponseEntity<String>("{\"error\": \"not_found\", \"error_description\": \"Could not find token\"}", HttpStatus.NOT_FOUND);*/

        //TODO: Implement revoke
        return new ResponseEntity<String>("{\"status\": \"revoked\", \"status_description\": \"Token successfully revoked\"}", HttpStatus.OK);
    }
}
