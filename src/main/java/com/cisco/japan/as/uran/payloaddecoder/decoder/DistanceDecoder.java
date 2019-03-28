package com.cisco.japan.as.uran.payloaddecoder.decoder;

import java.math.BigInteger;

import com.cisco.japan.as.uran.payloadencoder.util.CommonUtils;

public class DistanceDecoder {

	/**
	 * Distanceデコード処理
	 * 
	 * @param hexStr デコード用文字列
	 * @return distance
	 */
	public static String decodeDistance(String hexStr) {

		String distance = null;

		// 10進数へ変換
		BigInteger tmpVal = new BigInteger(hexStr, 16);

		// リトルエンディアン変換
		short littleVal = CommonUtils.toLittleEndianShort(tmpVal);

		// リトルエンディアン変換後の値を16進数に変換
		String littleValStr = BigInteger.valueOf(littleVal).toString(16);
		// 10進数へ変換
		distance = String.valueOf(Long.parseLong(littleValStr, 16));

		return distance;

	}
}
