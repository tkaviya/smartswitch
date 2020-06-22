package net.symbiosis.authentication;

import net.symbiosis.authentication.persistence.entity.sym_user;
import net.symbiosis.common.persistence.entity.enumeration.sym_channel;
import net.symbiosis.common.persistence.entity.enumeration.sym_country;
import net.symbiosis.core_lib.response.SymResponseObject;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;

import static net.symbiosis.common.configuration.NetworkUtilities.sendEmailAlert;
import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.common.persistence.helper.SymEnumHelper.countryFromString;
import static net.symbiosis.common.utilities.SymValidator.*;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.*;
import static net.symbiosis.core_lib.enumeration.SymChannel.fromString;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.*;

/***************************************************************************
 * *
 * Created:     30 / 12 / 2016                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

public class AuthenticationHelper {

    public static boolean isPinChannel(sym_channel channel) {

        switch (fromString(channel.getName())) {

            //password channels
            case DESKTOP:
            case WEB:
                return false;

            //pin channels
            case POS_MACHINE:
            case POS_TILL:
            case USSD:
            case SMART_PHONE:
                return true;

            default:
                return true;
        }
    }

	public static SymResponseObject<sym_user> validateMandatoryChannelData(sym_user userData, sym_channel channel) {

        if (channel == null) {
            return new SymResponseObject<sym_user>(GENERAL_ERROR).setMessage("Invalid channel specified");
        } else if (userData == null) {
            return new SymResponseObject<sym_user>(INPUT_INVALID_REQUEST).setMessage("Invalid user data (null) specified");
        } else if (userData.getCountry() == null || !isValidName(userData.getCountry().getName())) {
            return new SymResponseObject<sym_user>(INPUT_INVALID_REQUEST).setMessage("Invalid country specified");
        } else if (userData.getLanguage() == null || !isValidName(userData.getLanguage().getName())) {
            return new SymResponseObject<sym_user>(INPUT_INVALID_REQUEST).setMessage("Invalid language specified");
        }

        sym_country country = countryFromString(userData.getCountry().getName());
        if (country == null) {
            return new SymResponseObject<sym_user>(NOT_SUPPORTED).setMessage("Invalid country specified");
        } else if (userData.getLanguage() == null || !isValidName(userData.getLanguage().getName())) {
            return new SymResponseObject<sym_user>(NOT_SUPPORTED).setMessage("Invalid language specified");
        } else if (!isValidUsername(userData.getUsername())) {
		    return new SymResponseObject<sym_user>(INPUT_INVALID_USERNAME).setMessage("Invalid username (" + userData.getUsername() + ") specified");
	    } else if (!isValidName(userData.getFirst_name())) {
		    return new SymResponseObject<sym_user>(INPUT_INVALID_FIRST_NAME).setMessage("Invalid first name (" + userData.getFirst_name() + ") specified");
	    } else if (!isValidName(userData.getLast_name())) {
		    return new SymResponseObject<sym_user>(INPUT_INVALID_LAST_NAME).setMessage("Invalid last name (" + userData.getLast_name() + ") specified");
	    }  else if (!isValidTenDigitMsisdn(userData.getMsisdn()) && !isValidMsisdn(userData.getMsisdn(), country.getDialing_code())) {
		    return new SymResponseObject<sym_user>(INPUT_INVALID_MSISDN).setMessage("Invalid phone number (" + userData.getMsisdn() + ") specified");
	    }

	    switch (fromString(channel.getName())) {
            case WEB: {
	            if (!isValidEmail(userData.getEmail())) {
		            return new SymResponseObject<sym_user>(INPUT_INVALID_EMAIL).setMessage("Invalid email (" + userData.getEmail() + ") specified");
	            } else if (!isValidPassword(userData.getPassword())) {
		            return new SymResponseObject<sym_user>(INPUT_INVALID_PASSWORD).setMessage("Invalid password specified");
	            } else return new SymResponseObject<sym_user>(SUCCESS).setResponseObject(userData);
            }
	        case SMART_PHONE: {
		        if (!isValidPin(userData.getPin())) {
			        return new SymResponseObject<sym_user>(INPUT_INVALID_PASSWORD).setMessage("Invalid pin number specified");
		        } else return new SymResponseObject<sym_user>(SUCCESS).setResponseObject(userData);
	        }
            default:
                return new SymResponseObject<sym_user>(NOT_SUPPORTED).setMessage("Channel not supported: " + channel.getName());
        }
    }

    public static String getEmailTemplate(String template) {

	    FileReader fr = null;
	    BufferedReader br = null;
	    StringBuilder systemEmail = new StringBuilder();

	    try {
		    ClassPathResource resource = new ClassPathResource(template);
		    fr = new FileReader(resource.getFile().getAbsolutePath());
		    br = new BufferedReader(fr);
		    String line;
		    br = new BufferedReader(fr);

		    while ((line = br.readLine()) != null) {
			    systemEmail.append(line.replaceAll("%system_name%", getConfig(CONFIG_SYSTEM_NAME))
			            .replaceAll("%domain_name%", getConfig(CONFIG_DOMAIN_NAME))
					    .replaceAll("%resource_path%", getConfig(CONFIG_SYSTEM_NAME).toLowerCase().replaceAll(" ", "_"))
					    .replaceAll("%full_company_name%", getConfig(CONFIG_FULL_COMPANY_NAME))
					    .replaceAll("%copyright_year%", getConfig(CONFIG_COPYRIGHT_YEAR))
					    .replaceAll("%contact_address%", getConfig(CONFIG_CONTACT_ADDRESS))
					    .replaceAll("%support_phone%", getConfig(CONFIG_SUPPORT_PHONE))
					    .replaceAll("%support_email%", getConfig(CONFIG_SUPPORT_EMAIL))).append("\r\n");
		    }
	    } catch (Exception e) {
		    e.printStackTrace();
		    sendEmailAlert(getConfig(CONFIG_SYSTEM_NAME), "Failed to send registration email! \r\n", e.getMessage());
	    } finally {
		    try {
			    if (br != null) {
				    br.close();
			    }
			    if (fr != null) {
				    fr.close();
			    }
		    } catch (Exception ex) {
			    ex.printStackTrace();
		    }
	    }
	    return systemEmail.toString();
    }
}
