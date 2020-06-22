package net.symbiosis.persistence.entity.super_class;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@MappedSuperclass
public abstract class sym_enum_entity<E extends sym_enum_entity> extends sym_entity<E> {

    @Basic
    @Column(nullable = false, unique = true)
    protected String name;

    @Basic
    @Column(nullable = false, columnDefinition = "bit default true")
    protected Boolean is_enabled = true;

    public sym_enum_entity() {
    }

    public sym_enum_entity(String name, Boolean is_enabled) {
        this.name = name;
        this.is_enabled = is_enabled;
    }

    public String getName() {
        return name;
    }

    /* this function is private because we do not want to be
     * able to update an enum entity to set name programmatically */
    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(Boolean enabled) {
        this.is_enabled = enabled;
    }

    @Override
    public String toString() {
        return name;
    }
}
