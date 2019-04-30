package fr.hoc.dap.server.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;

import fr.hoc.dap.server.Config;

/**
 * Mother class for all Google's API services.
 * @author Mohammed & Thomas
 */

@Service
public class GoogleService {

    /** Injecter une dépendence de la classe Config dans GoogleService.*/
    @Autowired
    protected Config maConf;

    /** Log4j logging system. */
    public static final Logger LOG = LogManager.getLogger();
    /**
     * Json factory Implementation.
     */
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /**Create list of scopes for google's services. */
    private static List<String> scopes;

    /** Create google Flow.
     * @return a configured Google flow.
     * @throws GeneralSecurityException can not connect to the sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    public GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        //TODO moth by Djer |POO| Il serait plus élégant d'alimenter les "scopes" dans le constructeur de la classe (à chaque "getFlow" la liste est (re)créée est ca n'est pas très utile)
        scopes = new ArrayList<String>();
        scopes.add(CalendarScopes.CALENDAR_READONLY);
        scopes.add(GmailScopes.GMAIL_READONLY);
        scopes.add(GmailScopes.GMAIL_LABELS);
        // Load client secrets.
        File in = new java.io.File(maConf.getClientSecretFile());
        Reader targetReader = new FileReader(in);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, targetReader);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(maConf.getCredentialFolder())))
                        .setAccessType("offline").build();

        return flow;
    }

    /**
     * Manage user's credentials.
     * @param userKey unique identification for each users
     * @return return the credentials of the user with the userid
     * @throws IOException              IO Error
     * @throws GeneralSecurityException Security exception
     */
    public Credential getCredentials(final String userKey) throws IOException, GeneralSecurityException {

        GoogleAuthorizationCodeFlow flow = getFlow();
        LOG.info("Authorization Flow obtenue for user \" + userid");

        return flow.loadCredential(userKey);
    }

    /**
     * Allow the ability to "read" the credentials.
     * @return User's credentials info stored in DataStore
     * @throws GeneralSecurityException SecurityException
     * @throws IOException IOException
     */
    public DataStore<StoredCredential> getCredentialMap() throws GeneralSecurityException, IOException {
        LOG.info("Création de la liste des credentials");
        GoogleAuthorizationCodeFlow flow = getFlow();

        DataStore<StoredCredential> datas = flow.getCredentialDataStore();

        return datas;
    }

}
