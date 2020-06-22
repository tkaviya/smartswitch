package net.symbiosis.common.persistence.dao.complex_type;

import net.symbiosis.common.persistence.entity.enumeration.sym_config;
import net.symbiosis.persistence.dao.super_class.SymbisoisEnumEntityDao;

import java.util.HashMap;

/***************************************************************************
 *                                                                         *
 * Created:     26 / 04 / 2019                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public interface SymConfigDao extends SymbisoisEnumEntityDao<sym_config, Long> {
    HashMap<String, String> getAllConfigs(boolean forceRefresh);
    String getDBConfig(String configName);
}
