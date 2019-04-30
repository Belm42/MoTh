package fr.hoc.dap.server.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Contient tout les attributs pour gérer un utilisateur DAP.
 * @author Mohammed.
 *
 */
@Entity
public class DapUser {
    /** Dap user Id. */
    @Id
    @GeneratedValue
    private Long id;
    /** Dap user Id to stock in google credential.*/
    @Column(nullable = false, unique = true)
    private String userKey;
    /** Dap user Login Name.*/
    private String loginName;

    /**
     * @return the iD
     */
    public Long getId() {
        return id;
    }

    /**
     * @param iD the iD to set
     */
    public void setId(final Long iD) {
        id = iD;
    }

    /**
     * @param newUserKey blabla.
     * @return the userKey.
     */
    public String getUserKey(final String newUserKey) {
        return newUserKey;
    }

    /**
     * @param newUserKey the userKey to set
     */
    public void setUserKey(final String newUserKey) {
        this.userKey = newUserKey;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param newLoginName the loginName to set
     */
    public void setLoginName(final String newLoginName) {
        this.loginName = newLoginName;
    }

}
