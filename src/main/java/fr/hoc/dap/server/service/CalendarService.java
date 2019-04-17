package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import fr.hoc.dap.server.Config;

/**
 * Service for Google Calendar access.
 * @author Mohammed et Thomas
 *
 */
@Service
public class CalendarService extends GoogleService {

    /** Logger Log4j declaration. */
    private static final Logger LOG = LogManager.getLogger();

    /**
     *  injection of Dependency with Autowired annotation .
     */
    @Autowired
    private Config maConf;

    /** Calendar service.
     *
     * @param userKey connected.
     * @return calendar service instance.
     * @throws GeneralSecurityException can not connect to google sever.
     * @throws IOException where the credentials.json file cannot be found.
     */
    //TODO moth by Djer |POO| BuildService serait mieux comme nom de méthode
    public Calendar getService(final String userKey) throws GeneralSecurityException, IOException {
        // Build a new authorized API client service.
        final NetHttpTransport httptransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httptransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(maConf.getApplicationName()).build();

        //TODO moth by Djer |Log4J| Contextualise tes messages : "Calendar Service obtained for user " userkey
        //TODO moth by Djer |Log4J| Un niveau debug serait sufisant ? Est-ce que le commanditaire (metier) de l'application comprendrait se message ?
        LOG.info("Service obtained");
        return service;
    }

    /** Display the next events.
     * @return next events. //TODO moth by Djer |JavaDoc| "Textual repesentation of next event(s)" serait mieux
     * @throws GeneralSecurityException can not connect the sever.
     * @throws IOException if the credentials.json file can not be found.
     * @param userKey connected. //TODO moth by Djer |JavaDoc| "DaP user (login)" serait mieux
     * @param nb  of events wanted by user. //TODO moth by Djer |JavaDoc| "nb of events to display" serait mieux
     */
    public String nextEvents(final Integer nb, final String userKey) throws IOException, GeneralSecurityException {
        Calendar service = getService(userKey);
        String results = "";
        //TODO moth by Djer |POO| Se commentaire est devenu FAUX !
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());

        Events events = service.events().list("primary").setMaxResults(nb).setTimeMin(now).setOrderBy("startTime")
                .setSingleEvents(true).execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            LOG.info("No event found");
            results = "No upcoming events";
        } else {
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                results += event.getSummary() + " " + start;
            }
        }

        return results;
    }
}
