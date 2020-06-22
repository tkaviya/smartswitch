package net.symbiosis.persistence.entity.super_class;

import net.symbiosis.core_lib.interfaces.PrintableStringClass;
import net.symbiosis.persistence.helper.DaoManager;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/* *************************************************************************
 * Created:     18 / 09 / 2015                                             *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * *************************************************************************
 */

@MappedSuperclass
public abstract class sym_entity<E extends sym_entity> implements Serializable, PrintableStringClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public sym_entity() {
    }

    public Long getId() {
        return id;
    }

    /* this function is private because we do not want to be
     * able to update an entity id via the program ever */
    private E setId(Long id) {
        this.id = id;
        return (E) this;
    }

    @Override
    public String toString() {
        return this.toPrintableString();
    }

    public synchronized E save() {
        if (id != null) {
            return DaoManager.getEntityManagerRepo().saveOrUpdate(this);
        }
        return DaoManager.getEntityManagerRepo().save(this);
    }

    public synchronized E flushAndRefresh() {
        DaoManager.getEntityManagerRepo().flushAndRefresh(this);
        return (E) this;
    }

    public synchronized E refresh() {
        DaoManager.getEntityManagerRepo().refresh(this);
        return (E) this;
    }

    public synchronized void delete() {
        DaoManager.getEntityManagerRepo().delete(this);
    }
}
