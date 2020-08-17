package com.showtime.authserver.service.impl;

import com.showtime.authserver.domain.Client;
import com.showtime.authserver.domain.CustomClientDetails;
import com.showtime.authserver.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.util.Optional;

/**
 * @author Vengatesan Nagarajan
 */
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        Optional<Client> client = clientRepository.findByClientId(clientId);
        if (client.isPresent()) {
            return new CustomClientDetails(client.get());
        } else {
            throw new ClientRegistrationException(String.format("Client with id %s not found", clientId));
        }
    }
}
