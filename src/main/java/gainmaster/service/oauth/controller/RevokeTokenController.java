package gainmaster.service.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

@RestController
public class RevokeTokenController {

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = "/oauth/revoke", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestParam("token") String value) throws InvalidClientException {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(value);
        OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(value);

        if(accessToken != null) {
            tokenStore.removeAccessToken(accessToken);
            return new ResponseEntity<String>("{\"status\": \"revoked\", \"status_description\": \"Token successfully revoked\"}", HttpStatus.OK);
        }

        if(refreshToken != null) {
            tokenStore.removeAccessTokenUsingRefreshToken(refreshToken);
            return new ResponseEntity<String>("{\"status\": \"revoked\", \"status_description\": \"Token successfully revoked\"}", HttpStatus.OK);
        }

        return new ResponseEntity<String>("{\"status\": \"not_found\", \"status_description\": \"Token not found\"}", HttpStatus.NOT_FOUND);
    }
}
