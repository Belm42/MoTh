package fr.hoc.dap.server;

/**
 * @author Mohammed et Thomas
 *
 */
public class Config {

    /** File path of the credential folder (Path to the json file).*/
    private static final String CREDENTIAL_FILE_PATH = System.getProperty("user.home") + "/dap/credential_web.json";
    /** Directory path of the tokens file.*/
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "/dap/tokens";
    /** The application's name.*/
    private static final String APPLICATION_NAME = "HoC DaP";
    /** Authorized redirect URL . */
    private static final String AUTH2_CALLBACK_URL = "/oAuth2Callback";

    /** Declaration applicationName.*/
    private String applicationName;
    /** Declaration tokensDirectoryPath.*/
    private String tokensDirectoryPath;
    /** Declaration credentialFilePath.*/
    private String credentialFilePath;
    /**Declaration auth2CallBackUrl.*/
    private String auth2CallBackUrl;

    /** constructor for the custom Configuration.*/
    public Config() {
        applicationName = APPLICATION_NAME;
        tokensDirectoryPath = TOKENS_DIRECTORY_PATH;
        credentialFilePath = CREDENTIAL_FILE_PATH;
        auth2CallBackUrl = AUTH2_CALLBACK_URL;

    }

    /**
     * @return a credentialFolder
     */
    public String getCredentialFolder() {
        return tokensDirectoryPath;
    }

    /**
     * @param credentialFolder set a new credentialFolder.
     */
    public void setCredentialFolder(final String credentialFolder) {
        this.tokensDirectoryPath = credentialFolder;
    }

    /**
     * @return clientSecretFile
     */
    public String getClientSecretFile() {
        return credentialFilePath;
    }

    /**
    * @param credFilePath File path of the user's credentials.
    */
    public void setClientSecretFile(final String credFilePath) {
        this.credentialFilePath = credFilePath;
    }

    /**
     * @return credentialFilePath
     */
    public String getCredentialFilePath() {
        return credentialFilePath;
    }

    /**
     * @return The credential Folder.
     */
    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    /**
     * @param appName the applicationName to set
     */
    public void setApplicationName(final String appName) {
        this.applicationName = appName;
    }

    /**
     *
     * @return New Application name.
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @return URL autorized.
     */
    public String getoAuth2CallbackUrl() {
        return auth2CallBackUrl;

    }

}
