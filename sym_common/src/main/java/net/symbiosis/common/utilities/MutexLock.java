package net.symbiosis.common.utilities;

import java.util.logging.Logger;

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_MUTEX_LOCK_WAIT_INTERVAL;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_MUTEX_LOCK_WAIT_TIME;

/**
 * ***************************************************************************
 * *
 * Created:     25 / 09 / 2015                                             *
 * Platform:    Red Hat Linux 9                                            *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * ****************************************************************************
 */


public class MutexLock {

    private static final Logger logger = Logger.getLogger(MutexLock.class.getSimpleName());
    private boolean locked = false;

    private Long waitTimeout;
    private Long waitInterval;

    public MutexLock() {
        this.waitTimeout = parseLong(getConfig(CONFIG_MUTEX_LOCK_WAIT_TIME));
        this.waitInterval = parseLong(getConfig(CONFIG_MUTEX_LOCK_WAIT_INTERVAL));
    }

    public MutexLock(Long waitTimeout, Long waitInterval) {
        this.waitTimeout = waitTimeout;
        this.waitInterval = waitInterval;
    }

    public synchronized boolean waitForLock() {

        //wait for previous lock to be released
        while (this.locked && waitTimeout > 0) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitTimeout -= waitInterval;
        }

        return true;
    }

    public synchronized boolean acquireLock() {

        //wait for previous lock to be released
        while (this.locked && waitTimeout > 0) {
            logger.warning(format("Waiting for lock. Retrying in %d milliseconds", waitInterval));
            try {
                Thread.sleep(waitInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitTimeout -= waitInterval;
        }

        //acquire lock and return
        this.locked = true;

        return true;
    }

    public synchronized void unlock() {
        this.locked = false;
    }

    protected boolean isLocked() {
        return this.locked;
    }
}
