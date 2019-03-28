package com.cisco.japan.as.uran.payloaddecoder.decoder;

import java.math.BigInteger;

import com.cisco.japan.as.uran.payloadencoder.util.CommonUtils;

public class StepCountDecoder {

	/**
	 * SteoCountデコード処理
	 * 
	 * @param hexStr デコード用文字列
	 * @return stepCount
	 */
	public static String decodeStepCount(String hexStr) {
		
		String stepCount = null;

		// 10進数へ変換
		BigInteger tmpVal = new BigInteger(hexStr, 16);

		// リトルエンディアン変換
		short littleVal = CommonUtils.toLittleEndianShort(tmpVal);

		// リトルエンディアン変換後の値を16進数に変換
		String littleValStr = BigInteger.valueOf(littleVal).toString(16);
		// 10進数へ変換
		stepCount = String.valueOf(Long.parseLong(littleValStr, 16));

		return stepCount;
		
	}
}
