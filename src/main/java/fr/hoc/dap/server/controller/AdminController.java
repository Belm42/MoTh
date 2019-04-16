package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.service.AdminService;

/**
 * Controller who handles the administration panel.
 * @author Mohammed & Thomas
 *
 */
@Controller
public class AdminController {

    /** Logger Log4j declaration. */
    private static final Logger LOG = LogManager.getLogger();

  //TODO moth by Djer |JavaDoc| Tu ne dois pas docummenter l'annotation (déja fait par Spring DANS l'annotation) mais l'attribut
    /**
     * Injection of dependency with Autowired annotation.
     */
    @Autowired
    private AdminService accService;

    /**
     *  //TODO moth by Djer |JavaDoc| Il manque la "description" (première ligne)
     * @param model Map Object loaded with results
     * @return Admin View
     * @throws GeneralSecurityException Security Exception
     * @throws IOException IOException
     */
    @RequestMapping("/admin")
    public String admin(final ModelMap model) throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> usersInfo = accService.getCredentialMap();

        Map<String, StoredCredential> userMap = new HashMap<>();
        Set<String> keys = usersInfo.keySet();

        for (String key : keys) {
            StoredCredential value = usersInfo.get(key);
            userMap.put(key, value);
        }
        model.addAttribute("users", userMap);

        //TODO moth by Djer |Log4J| Contextualise tes messages : userMap.size() + " users generated"
        LOG.info("List of users generated");

        return "admin";

    }

    /**
     * //TODO moth by Djer |JavaDoc| Il manque la "description" (première ligne)
     * @param userKey the username you want to delete.
     * @return redirect to the admin homepage
     * @throws GeneralSecurityException Security Exception
     * @throws IOException IOException
     */
    @RequestMapping("/delete/user")
    public String deleteuser(final String userKey) throws GeneralSecurityException, IOException {
      //TODO moth by Djer |POO| Tu n'es pas obligé de récupére stocker la valeur de retour dans une variable si tu n'en a pas besoin.
        DataStore<StoredCredential> deleteUser = accService.getCredentialMap().delete(userKey);

        LOG.info("User is deleted");
        return "redirect:/admin";

    }
}
