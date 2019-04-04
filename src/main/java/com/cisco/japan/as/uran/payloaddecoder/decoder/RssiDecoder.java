package com.cisco.japan.as.uran.payloaddecoder.decoder;

import javax.xml.bind.DatatypeConverter;

public class RssiDecoder {
	
	/**
	 * RSSIデコード処理
	 * 
	 * @param hexStr デコード用文字列
	 * @return RSSI
	 */
	public static String decodeRssi(String hexStr) {
		
		byte[] bytes = DatatypeConverter.parseHexBinary(hexStr);
		// bit反転
		int val = ~bytes[0];
		// 1加えて、-1をかける
		val = (val + 1)*-1;
		
		return String.valueOf(val);
		
	}
}
