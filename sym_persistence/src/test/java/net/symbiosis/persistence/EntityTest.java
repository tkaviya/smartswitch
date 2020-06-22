package net.symbiosis.persistence;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */

@Test
public class EntityTest {

    @BeforeClass
    public void setUp() {
        new ClassPathXmlApplicationContext("test-sym_persistence-spring-context.xml");
    }

//	private static void enumerateAuthUsers(sym_user symUser) {
//
//		int numAuthUsers = symUser.getAuth_users() == null ? 0 : symUser.getAuth_users().size();
//
//		System.out.println("Number of auth users linked to symUser" + symUser.getId() + " is " + numAuthUsers);
//
//		for (sym_auth_user testUser : symUser.getAuth_users()) {
//			System.out.println("---------------");
//			System.out.println("Found linked testSymbiosisUser" + symUser.getId() + " entity: " + testUser.getId());
//			System.out.println("Group name: " + testUser.getGroup().getName());
//			System.out.println("Channel name: " + testUser.getChannel().getName());
//			System.out.println("Registration date: " + testUser.getRegistration_date());
//			System.out.println("---------------");
//		}
//
//	}
//
//	@Test
//	public void lazyOneToManyTest() {
//
//		System.out.println("RUNNING TEST: lazyOneToManyTest");
//
//		sym_user symOTMUser1 = createTestSymbiosisUser();
//		sym_auth_user authOTMUser1 = createTestAuthUser(symOTMUser1);
//
//		//persist only system user
//		getUserDao().saveOrUpdate(symOTMUser1);
//		assertNotNull(authOTMUser1.getUser().getAuth_users());
//		assertTrue(authOTMUser1.getUser().getAuth_users().size() >= 0);
//		enumerateAuthUsers(authOTMUser1.getUser());
//
//
//		sym_user symOTMUser2 = createTestSymbiosisUser();
//		sym_auth_user authOTMUser2 = createTestAuthUser(symOTMUser2);
//
//		//persist only auth user
//		getAuthUserDao().saveOrUpdate(authOTMUser2);
//		assertNotNull(authOTMUser2.getUser().getAuth_users());
//		assertTrue(authOTMUser2.getUser().getAuth_users().size() >= 0);
//		enumerateAuthUsers(authOTMUser2.getUser());
//
//		//reload directly from DB
//		sym_user symUser1 = getUserDao().findById(1L);
//		assertNotNull(symUser1.getAuth_users());
//		assertTrue(symUser1.getAuth_users().size() >= 0);
//		enumerateAuthUsers(symUser1);
//	}
}
