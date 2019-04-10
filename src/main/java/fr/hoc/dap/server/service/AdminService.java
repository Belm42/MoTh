package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**
 * Service for the administration of the app.
 * @author Mohammed et Thomas.
 *
 */
@Service
public class AdminService extends GoogleService {

    /** Logger Log4j declaration. */
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public final DataStore<StoredCredential> getCredentialMap() throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> userList = getFlow().getCredentialDataStore();

        LOG.info("List of users obtained");

        return userList;
    }

}
