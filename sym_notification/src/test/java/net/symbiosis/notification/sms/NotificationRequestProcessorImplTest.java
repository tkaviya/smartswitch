package net.symbiosis.notification.sms;

import net.symbiosis.common.contract.symbiosis.SymSystemUser;
import net.symbiosis.core_lib.enumeration.SymResponseCode;
import net.symbiosis.notification.api.service.NotificationRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_SYSTEM_NAME;
import static net.symbiosis.core_lib.enumeration.SymChannel.SYSTEM;

@Test
@Component
public class NotificationRequestProcessorImplTest {

	private final NotificationRequestProcessor notificationRequestProcessor;

	@Autowired
	public NotificationRequestProcessorImplTest(NotificationRequestProcessor notificationRequestProcessor) {
		this.notificationRequestProcessor = notificationRequestProcessor;
	}

	@BeforeClass
	public void setUp() {
		new ClassPathXmlApplicationContext("test-sym_notification-spring-context.xml");
	}

	@Test
	public void testSendSMS() {
		var sendResult = notificationRequestProcessor.sendSMS(new SymSystemUser(
				0L, 0L, 0L, 0L, "Test", "Test",
				"system", "system@test.com", "263123456789", "263123456789",
				"system", 0.0, "MOBILE_USER", null, null, new Date())
			, SYSTEM.name(), "263785107830", "Test message from " + getConfig(CONFIG_SYSTEM_NAME));
		Assert.assertEquals(sendResult.getResponse_code(), SymResponseCode.SUCCESS.code);
	}
}