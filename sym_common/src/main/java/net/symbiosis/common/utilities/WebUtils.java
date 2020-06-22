package net.symbiosis.common.utilities;

import static net.symbiosis.core_lib.utilities.CommonUtilities.isNullOrEmpty;

/***************************************************************************
 *                                                                         *
 * Created:     16 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public class WebUtils {
	public static <T> T getRealParamValue(T param) {
		return isNullOrEmpty(String.valueOf(param)) ? null : param;
	}
}
