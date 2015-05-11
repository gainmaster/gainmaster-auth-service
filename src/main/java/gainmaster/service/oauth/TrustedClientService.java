package gainmaster.service.oauth;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrustedClientService implements ClientDetailsService {

    private static final String CLIENT_ID = "client";
    private static final String CLIENT_SECRET = "secret";

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (clientId.equals(CLIENT_ID)) {
            BaseClientDetails clientDetails = new BaseClientDetails();
            clientDetails.setClientId(CLIENT_ID);
            clientDetails.setClientSecret(CLIENT_SECRET);

            List<String> authorizedGrantTypes = new ArrayList<String>();
            authorizedGrantTypes.add("password");
            authorizedGrantTypes.add("refresh_token");
            authorizedGrantTypes.add("client_credentials");

            clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);

            return clientDetails;
        }

        throw new NoSuchClientException("No client with id: " + clientId );
    }

}

