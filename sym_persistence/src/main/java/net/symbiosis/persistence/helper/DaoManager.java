package net.symbiosis.persistence.helper;

import net.symbiosis.persistence.dao.EntityManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 11/14/13
 * Time: 8:00 PM
 */
@Component
public class DaoManager {
    @Autowired
    private EntityManagerRepo entityManagerRepo;

    private static DaoManager daoManager = null;

    private DaoManager() {
        daoManager = this;
    }

    public static DaoManager getInstance() {
        if (daoManager == null) {
            daoManager = new DaoManager();
        }
        return daoManager;
    }


    public static EntityManagerRepo getEntityManagerRepo() {
        return getInstance().entityManagerRepo;
    }
}
