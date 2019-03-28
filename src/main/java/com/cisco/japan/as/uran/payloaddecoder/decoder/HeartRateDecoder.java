package com.cisco.japan.as.uran.payloaddecoder.decoder;

import com.cisco.japan.as.uran.payloadencoder.util.CommonUtils;

public class HeartRateDecoder {

	/**
	 * HeartRateデコード処理
	 * 
	 * @param hexStr デコード用文字列
	 * @return heartRate
	 */
	public static String decodeHeartRate(String hexStr) {

		// 10進数へ変換
		return String.valueOf(CommonUtils.toDecimalNumber(hexStr));
	}

}
