package net.symbiosis.common.persistence.dao.implementation;

import net.symbiosis.common.persistence.dao.complex_type.SymConfigDao;
import net.symbiosis.common.persistence.entity.enumeration.sym_config;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_CONFIG_UPDATE_DURATION;

/***************************************************************************
 *                                                                         *
 * Created:     26 / 04 / 2019                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Repository
@Transactional
public class SymConfigDaoImpl extends SymEnumEntityDaoImpl<sym_config, Long> implements SymConfigDao {

    private static final Logger logger = Logger.getLogger(SymConfigDaoImpl.class.getSimpleName());
    private static HashMap<String, String> configMap = null;
    private static Long lastUpdateTime = null;
    private static Long updateDuration = 3600000L; //default is to update configs every hour
    private static SymConfigDao symConfigDao;

    public SymConfigDaoImpl() {
        super(sym_config.class);
        symConfigDao = this;
    }

    protected SymConfigDaoImpl(SymConfigDao symConfigDao) {
        super(sym_config.class);
        this.symConfigDao = symConfigDao;
    }

    @Override
    public HashMap<String, String> getAllConfigs(boolean forceRefresh) {
        if (forceRefresh || configMap == null || lastUpdateTime == null || (new Date().getTime() - lastUpdateTime) >= updateDuration) {

            configMap = new HashMap<>();

            //clear all configs from hibernate cache
            getEntityManager().clear();

            //get all configs from database
            List<sym_config> symConfigs = findAll();

            //add all configs to memory for future use
            for (sym_config symConfig : symConfigs) {
//                symConfig.refresh();
                logger.info(format("Loading config '%s' with value '%s'",
                        symConfig.getName(),
                        symConfig.getConfig_value()));
                configMap.put(symConfig.getName(), symConfig.getConfig_value());
            }

            //if config update duration has changed, get the new value
            if (configMap.containsKey(CONFIG_CONFIG_UPDATE_DURATION)) {
                updateDuration = Long.parseLong(configMap.get(CONFIG_CONFIG_UPDATE_DURATION)) * 60000;
            }

            //update lastUpdateTime
            lastUpdateTime = new Date().getTime();
        }
        return configMap;

    }

    @Override
    public String getDBConfig(String configName) {
        return getAllConfigs(false).get(configName);
    }

    public static String getConfig(String configName) {
        return symConfigDao.getDBConfig(configName);
    }
}
