package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 * Service for the gmail access.
 * @author Mohammed et Thomas
 *
 */
@Service
public class GmailService extends GoogleService {

    /** Log4j logging system. */
    private static final Logger LOG = LogManager.getLogger();

    /** Get gmail service.
     * @param userKey connected.
     * @return gmail service instance.
     * @throws GeneralSecurityException can not connect to the sever.
     * @throws IOException if the credentials.json file can not be found.
     */

    public Gmail buildService(final String userKey) throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(maConf.getApplicationName()).build();

        return service;
    }

    /**
     * @param userKey unique ID for each users
     * @return return the amount of mails with the "unread" label
     * @throws IOException              I/O error
     * @throws GeneralSecurityException Security
     */
    public int displayNumbeOfEmailUnread(final String userKey) throws IOException, GeneralSecurityException {
        LOG.info("void displayNumbeOfEmailUnread() started");
        String user = "me";
        String query = "is:unread";
        ListMessagesResponse response = buildService(userKey).users().messages().list(user).setQ(query).execute();
        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = buildService(userKey).users().messages().list(user).setQ(query).setPageToken(pageToken)
                        .execute();
            } else {
                break;
            }
        }
        return response.getMessages().size();
    }
}
