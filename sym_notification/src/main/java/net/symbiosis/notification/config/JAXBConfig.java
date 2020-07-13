//package net.symbiosis.notification.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//
//import java.util.Arrays;
//import java.util.logging.Logger;
//
///***************************************************************************
// *                                                                         *
// * Created:     13 / 07 / 2020                                             *
// * Author:      Tsungai Kaviya                                             *
// * System:      IntelliJ 2019 / Windows 10                                 *
// * Contact:     tsungai.kaviya@gmail.com                                   *
// *                                                                         *
// ***************************************************************************/
//
//@Configuration
//public class JAXBConfig {
//	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
//	@Bean
//	public Jaxb2Marshaller marshaller() {
//		logger.info("Initializing JAXB2Marshaller...");
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		String[] packagesToScan= {"net.symbiosis.common.contract.symbiosis"};
//		marshaller.setPackagesToScan(packagesToScan);
//		logger.info("Packages to scan set as " + Arrays.toString(marshaller.getPackagesToScan()));
//		return marshaller;
//	}
//}
