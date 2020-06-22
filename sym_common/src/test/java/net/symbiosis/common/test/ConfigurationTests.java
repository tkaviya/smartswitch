package net.symbiosis.common.test;

import net.symbiosis.common.configuration.Configuration;
import net.symbiosis.core_lib.enumeration.DBConfigVars;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static org.testng.Assert.assertNotNull;

@Test
public class ConfigurationTests {

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Test
    public void testAllConfigs() {
        logger.info("Testing configuration 'AES128BitKey'");
        assertNotNull(Configuration.getProperty("AES128BitKey"));
        logger.info("Testing configuration 'AESInitializationVector'");
        assertNotNull(Configuration.getProperty("AESInitializationVector"));

        for (Field field : DBConfigVars.class.getFields()) {
            String config = field.getName();
            String configName = field.getName().toLowerCase().replaceFirst("config_", "");
            logger.info("Testing configuration '" + config + "'");
            assertNotNull(getConfig(configName));
        }
    }
}
