package net.symbiosis.authentication.provider;

import net.symbiosis.authentication.persistence.entity.sym_auth_user;
import net.symbiosis.authentication.persistence.entity.sym_user;
import net.symbiosis.common.persistence.entity.enumeration.sym_auth_group;
import net.symbiosis.common.persistence.entity.enumeration.sym_auth_group_role;
import net.symbiosis.common.persistence.entity.enumeration.sym_channel;
import net.symbiosis.common.persistence.entity.enumeration.sym_response_code;
import net.symbiosis.core_lib.response.SymResponseObject;
import net.symbiosis.core_lib.structure.Pair;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static net.symbiosis.common.persistence.helper.SymEnumHelper.fromEnum;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.*;
import static net.symbiosis.persistence.dao.EnumEntityRepoManager.findByName;
import static net.symbiosis.persistence.helper.DaoManager.getEntityManagerRepo;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/6/13
 * Time: 7:06 PM
 */
@Service
public class SymUserDetailsService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(SymUserDetailsService.class.getSimpleName());

    private HashMap<String, List<SimpleGrantedAuthority>> grantedAuthoritiesCache = new HashMap<>();

    private sym_channel symChannel;

    public SymUserDetailsService setSymChannel(sym_channel symChannel) { this.symChannel = symChannel; return this; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SymResponseObject<sym_auth_user> userResponse = SymbiosisAuthenticator.getUserByUsername(username, symChannel);

        sym_user user = userResponse.getResponseObject().getUser();

        boolean accountNonExpired = true, accountNonLocked = true, credentialsNonExpired = true, enabled = false;

        sym_response_code userStatus = user.getUser_status();

        if (userStatus.equals(fromEnum(ACC_ACTIVE))) {
            enabled = true;
        } else if (userStatus.equals(fromEnum(ACC_INACTIVE)) || userStatus.equals(fromEnum(ACC_SUSPENDED))) {
            accountNonLocked = false;
        } else if (userStatus.equals(fromEnum(ACC_CLOSED))) {
            accountNonExpired = false;
        } else if (userStatus.equals(fromEnum(ACC_PASSWORD_TRIES_EXCEEDED)) || userStatus.equals(fromEnum(ACC_PASSWORD_EXPIRED))) {
            credentialsNonExpired = false;
        }

        return new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                getAuthorities(null));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String userGroup) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>();

        if (!grantedAuthoritiesCache.containsKey(userGroup)) {
            logger.fine("Getting authorities for access group " + userGroup);

            var userGroupRoles = getEntityManagerRepo().findWhere(sym_auth_group_role.class,
                new Pair<>("group_id", (findByName(sym_auth_group.class, userGroup).getId())
            ));

            for (sym_auth_group_role userGroupRole : userGroupRoles) {
                logger.fine("Caching role " + userGroupRole.getName());
                authList.add(new SimpleGrantedAuthority(userGroupRole.getId().toString()));
            }

            //cache the authorities to avoid future db hits.
            grantedAuthoritiesCache.put(userGroup, authList);
        }
        return grantedAuthoritiesCache.get(userGroup);
    }

}
