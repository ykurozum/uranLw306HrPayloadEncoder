package com.cisco.japan.as.uran.payloaddecoder.decoder;

import java.math.BigInteger;

import com.cisco.japan.as.uran.payloadencoder.util.CommonUtils;

public class SkinTemperatureDecoder {
	
	/** ステータス算出時の値 */
	private static final int DIVISION_VALUE = 100;

	/**
	 * SkinTemperatureデコード処理
	 * 
	 * @param hexStr デコード用文字列
	 * @return skinTemperature
	 */
	public static String decodeSkinTemperature(String hexStr) {

		String skinTemperature = null;

		// 10進数へ変換
		BigInteger tmpVal = new BigInteger(hexStr, 16);

		// リトルエンディアン変換
		short littleVal = CommonUtils.toLittleEndianShort(tmpVal);

		// リトルエンディアン変換後の値を16進数に変換
		String littleValStr = BigInteger.valueOf(littleVal).toString(16);
		// 10進数へ変換
		double hexValue = Long.parseLong(littleValStr, 16);

		skinTemperature = String.valueOf(hexValue/DIVISION_VALUE);

		return skinTemperature;

	}
}
