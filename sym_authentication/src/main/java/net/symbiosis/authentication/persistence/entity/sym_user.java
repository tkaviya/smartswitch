package net.symbiosis.authentication.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.common.persistence.entity.enumeration.sym_country;
import net.symbiosis.common.persistence.entity.enumeration.sym_language;
import net.symbiosis.common.persistence.entity.enumeration.sym_response_code;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "sym_user_id"))
@Cacheable(false)
public class sym_user extends sym_entity<sym_user> {
    @Column(length = 20)
    private String first_name;
    @Column(length = 20)
    private String last_name;
    @Column
    private Date date_of_birth;
    @Column(unique = true, length = 20)
    private String username;
    @Column
    private String password;
    @Column
    private String pin;
    @Column(nullable = false, precision = 1)
    private Integer password_tries;
    @Column(nullable = false, precision = 1)
    private Integer pin_tries;
    @Basic(optional = false)
    @Column(length = 128)
    private String salt;
    @Column(unique = true)
    private String email;
    @Column(unique = true, length = 12)
    private String msisdn;
    @Column(length = 12)
    private String msisdn2;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_status_id")
    private sym_response_code user_status;
    @OneToOne
    @JoinColumn(name = "wallet_id")
    private sym_wallet wallet;
    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    private sym_country country;
    @ManyToOne(optional = false)
    @JoinColumn(name = "language_id")
    private sym_language language;

    public sym_user(String first_name, String last_name, Date date_of_birth, String username, String password,
                    String pin, Integer password_tries, Integer pin_tries, String salt, String email,
                    String msisdn, String msisdn2, sym_response_code user_status,
                    sym_country country, sym_language language) {
        this(first_name, last_name, date_of_birth, username, password, pin, password_tries,
             pin_tries, salt, email, msisdn, msisdn2, user_status, null, country, language);
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public void setMsisdn2(String msisdn2) {
        this.msisdn2 = msisdn2;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setPassword_tries(Integer password_tries) {
        this.password_tries = password_tries;
    }

    public void setPin_tries(Integer pin_tries) {
        this.pin_tries = pin_tries;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setUser_status(sym_response_code user_status) {
        this.user_status = user_status;
    }

    public sym_user setWallet(sym_wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public void setCountry(sym_country country) {
        this.country = country;
    }

    public void setLanguage(sym_language language) {
        this.language = language;
    }
}
