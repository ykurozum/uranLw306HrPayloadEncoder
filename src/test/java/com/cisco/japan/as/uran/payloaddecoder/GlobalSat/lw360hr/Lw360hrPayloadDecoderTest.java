package com.cisco.japan.as.uran.payloaddecoder.GlobalSat.lw360hr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cisco.japan.as.uran.payloadencoder.DecodedPayload;
import com.cisco.japan.as.uran.payloadencoder.EncodedPayload;

public class Lw360hrPayloadDecoderTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Lw360hrPayloadDecoder target  = new Lw360hrPayloadDecoder();
		
		// 現在日時を取得
		Date date = new Date();

		// payloadStringからpayload_hexを取得
		String hexStr = "12022679967e0879967e0842zz2C015a84097d5babcdefghijklmnopqrstuvwxyzabcdefghijklmn20d15b5b2c0b401f8813";
		String hexStr2 = "12002579967e0879967e0842zz2C015a84097d5babcdefghijklmnopqrstuvwxyzabcdefghijklmn18d15b5b2c0b401f8813";
		
		// 日時,デコード用文字列(payload_hex),DeviceIdentifyerを入力
		EncodedPayload test1 = new EncodedPayload(date, hexStr, "test1");
		EncodedPayload test2 = new EncodedPayload(date, hexStr2, "test2");
		
		List<EncodedPayload> str = new ArrayList<EncodedPayload>();
		str.add(test1);
		str.add(test2);
		
		List<DecodedPayload> decodeInfoList = target.encode(str);

		// 結果をリスト分出力
		for (DecodedPayload decodeInfo : decodeInfoList) {
			System.out.println("--------start--------");
			System.out.println("PayloadString:" + decodeInfo.getPayloadString());
			System.out.println("Time:" + decodeInfo.getTime());
			System.out.println("PayloadJson:" + decodeInfo.getPalyloadJson());
			System.out.println("PayloadTime:" + decodeInfo.getPayloadTime());
			System.out.println("DeviceIdentifyer:" + decodeInfo.getDeviceIdentifiyer());
			System.out.println("--------end--------");
		}
		
	}

}
