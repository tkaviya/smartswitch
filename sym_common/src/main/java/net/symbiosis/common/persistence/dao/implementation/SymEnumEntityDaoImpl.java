package net.symbiosis.common.persistence.dao.implementation;

import net.symbiosis.core_lib.structure.Pair;
import net.symbiosis.persistence.dao.super_class.AbstractDao;
import net.symbiosis.persistence.dao.super_class.SymbisoisEnumEntityDao;
import net.symbiosis.persistence.entity.super_class.sym_entity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static net.symbiosis.persistence.helper.DaoManager.getEntityManagerRepo;

/**
 * ***************************************************************************
 * *
 * Created:     19 / 09 / 2015                                             *
 * Platform:    Red Hat Linux 9                                            *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * ****************************************************************************
 */

@Repository
@Transactional
public abstract class SymEnumEntityDaoImpl<E extends sym_entity, I extends Serializable>
        extends AbstractDao<E, I> implements SymbisoisEnumEntityDao<E, I> {

    private final static Logger logger = Logger.getLogger(SymEnumEntityDaoImpl.class.getSimpleName());

    protected SymEnumEntityDaoImpl(Class<E> entityClass) {
        super(entityClass);
    }

    @Override
    public List<E> findEnabled() {
        logger.fine(getEntityClass().getSimpleName() + " findEnabled = true");
        return getEntityManagerRepo().findWhere(getEntityClass(), new Pair<>("is_enabled", 1));
    }

    @Override
    public E findEnabledByName(String name) {
        logger.fine(getEntityClass().getSimpleName() + " findEnabledByName " + name);
        return getEntityManagerRepo().findWhere(getEntityClass(), Arrays.asList(new Pair<>("is_enabled", 1), new Pair<>("name", name))).get(0);
    }

    @Override
    public E findByName(String name) {
        logger.fine(getEntityClass().getSimpleName() + " findByName " + name);
        return getEntityManagerRepo().findWhere(getEntityClass(), new Pair<>("name", name)).get(0);
    }
}
