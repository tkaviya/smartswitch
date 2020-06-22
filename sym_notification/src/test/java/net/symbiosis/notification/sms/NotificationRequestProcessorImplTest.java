package net.symbiosis.notification.sms;

import net.symbiosis.common.service.NotificationRequestProcessor;
import net.symbiosis.core_lib.enumeration.SymResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
		var sendResult = notificationRequestProcessor.sendSMS(0L, SYSTEM.name(), "263785107830", "Test message from " + getConfig(CONFIG_SYSTEM_NAME));
		Assert.assertEquals(sendResult.getResponse_code(), SymResponseCode.SUCCESS.code);
	}
}