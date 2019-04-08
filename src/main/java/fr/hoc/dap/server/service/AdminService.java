package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**
 * @author house
 *
 */
@Service
public class AdminService extends GoogleService {

    @Override
    public final DataStore<StoredCredential> getCredentialMap() throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> x = getFlow().getCredentialDataStore();
        return x;
    }

}
