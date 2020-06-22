package net.symbiosis.common.security;

import net.symbiosis.core_lib.security.Security;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AESEncrypterTest {

	@Test
	public void testDoAESECBMcryptCompatible() {
		try {
			var data = "{\"MethodName\":\"DstGenerateSessionID\",\"RequestUniqueID\":\"1000029\"}";
			System.out.println("Initial data: " + data);
			// encrypt
			var encryptedData = Security.doAESECBMcryptCompatible("2205505435344133", data, true);
			System.out.println("Encrypted data: " + encryptedData);
			var decryptedData = Security.doAESECBMcryptCompatible("2205505435344133", encryptedData, false);
			System.out.println("Decrypted data: " + decryptedData);

			Assert.assertEquals(encryptedData.replaceAll("\\+","-").replaceAll("/","_").replaceAll("=",","),
				"io_c8BAVfSOVcsTU1jbm7-4bd_2cT_ccc1Bq9ddtVsbfd0V7MaL_lGm6cESo6xBmJZ9CV9UKwsjhsiATQN3Vi8gFxN0H2DtPRNABPzQes60,");
			Assert.assertEquals(data, decryptedData);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void testDoAESECBMcryptCompatible1() {
		try {
			var data1 = "{\"function\":\"TransactionService\",\"SessionID\":\"430b9124-914d-4408-bfc9-407b22d0319a\",\"RequestUniqueID\":\"1000051\",\"ProductCode\":\"MTNMOMOCashout\",\"SystemServiceID\":\"64\",\"WalletData\":\"{\\\"Mobile Number\\\":\\\"0547965699\\\"}\",\"Amount\":\"100\",\"FromANI\": \"\",\"Email\":\"empowerttl@gmail.com\",\"MethodName\":\"TransactionService\"}";
			var data2 = "{\"function\":\"TransactionService\",\"SessionID\":\"430b9124-914d-4408-bfc9-407b22d0319a\",\"RequestUniqueID\":\"1000052\",\"ProductCode\":\"MTNMOMOCashout\",\"SystemServiceID\":\"64\",\"WalletData\":\"{\\\"Mobile Number\\\":\\\"0547965699\\\"}\",\"Amount\":\"100\",\"FromANI\": \"\",\"Email\":\"empowerttl@gmail.com\",\"MethodName\":\"TransactionService\"}";
			var data3 = "{\"function\":\"TransactionService\",\"SessionID\":\"480219b7-ea4b-4588-a033-23c9f2ebbc62\",\"RequestUniqueID\":\"1000052\",\"ProductCode\":\"MTNMOMOCashout\",\"SystemServiceID\":\"64\",\"WalletData\":\"{\\\"Mobile Number\\\":\\\"0547965699\\\"}\",\"Amount\":\"100\",\"FromANI\": \"\",\"Email\":\"empowerttl@gmail.com\",\"MethodName\":\"TransactionService\"}";
			System.out.println("Initial data: " + data1);
			System.out.println("Initial data: " + data2);
			// encrypt
			var encryptedData1 = Security.doAESECBMcryptCompatible("2205505435344133", data1, true);
			var encryptedData2 = Security.doAESECBMcryptCompatible("2205505435344133", data2, true);
			System.out.println("Encrypted data1: " + encryptedData1);
			System.out.println("Encrypted data2: " + encryptedData2);
			var decryptedData1 = Security.doAESECBMcryptCompatible("2205505435344133", encryptedData1, false);
			var decryptedData2 = Security.doAESECBMcryptCompatible("2205505435344133", encryptedData2, false);
			System.out.println("Decrypted data1: " + decryptedData1);
			System.out.println("Decrypted data2: " + decryptedData2);

//			Assert.assertEquals(encryptedData.replaceAll("\\+","-").replaceAll("/","_").replaceAll("=",","),
//				"io_c8BAVfSOVcsTU1jbm7-4bd_2cT_ccc1Bq9ddtVsbfd0V7MaL_lGm6cESo6xBmJZ9CV9UKwsjhsiATQN3Vi8gFxN0H2DtPRNABPzQes60,");
			Assert.assertEquals(data1, decryptedData1);
			Assert.assertEquals(data2, decryptedData2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}