package net.symbiosis.common.configuration;

import net.symbiosis.common.mail.EMailer;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Logger;

import static net.symbiosis.common.mail.EMailer.DEFAULT_CONTENT_TYPE;
import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_EMAIL_ALERT_TO;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_SYSTEM_NAME;

/**
 * Created by photon on 2016/01/01.
 */
public class NetworkUtilities {

    private static Logger logger = Logger.getLogger(NetworkUtilities.class.getSimpleName());

    public static String execReadToString(String execCommand) throws IOException {
        Process proc = Runtime.getRuntime().exec(execCommand);
        try (InputStream stream = proc.getInputStream()) {
            try (Scanner s = new Scanner(stream).useDelimiter("\\A")) {
                return s.hasNext() ? s.next() : "";
            }
        }
    }

    public static String getHostName() {
        try {
            String hostname = execReadToString("hostname").trim();
            logger.info("Got hostname of the system as " + hostname);
            return hostname;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFQDN() {
        try {
            String hostname = java.net.InetAddress.getLocalHost().getHostName();
            logger.info("Got hostname of the system as " + hostname);
            return hostname;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void sendEmailAlert(String alertSubject, String alertMessage) {
        sendEmailAlert(getConfig(CONFIG_SYSTEM_NAME), alertSubject, alertMessage);
    }

    public static void sendEmailAlert(String symSystem, String alertSubject, String alertMessage) {
        logger.info("Sending alert email from " + symSystem + " with subject: " + alertSubject);
        logger.info(alertMessage);
        ThreadPoolManager.schedule(new EMailer(
            new String[]{getConfig(CONFIG_EMAIL_ALERT_TO)}, symSystem + " alert! " + alertSubject,
            alertMessage, DEFAULT_CONTENT_TYPE)
        );
    }

    public static void sendEmail(String recipient, String emailSubject, String emailMessage) {
        sendEmail(getConfig(CONFIG_SYSTEM_NAME), recipient, emailSubject, emailMessage);
    }

    public static void sendEmail(String symSystem, String recipient, String emailSubject, String emailMessage) {
        logger.info("Sending email from " + symSystem + " with subject: " + emailSubject);
        ThreadPoolManager.schedule(new EMailer(new String[]{recipient}, emailSubject, emailMessage, DEFAULT_CONTENT_TYPE));
    }

    public static void sendEmail(String[] recipients, String emailSubject, String emailMessage, String contentType) {
        sendEmail(getConfig(CONFIG_SYSTEM_NAME), recipients, emailSubject, emailMessage, contentType);
    }

    public static void sendEmail(String symSystem, String[] recipients, String emailSubject, String emailMessage,
                                 String contentType) {
        logger.info("Sending email from " + symSystem + " with subject: " + emailSubject);
        ThreadPoolManager.schedule(new EMailer(recipients, emailSubject, emailMessage, contentType));
    }
}
