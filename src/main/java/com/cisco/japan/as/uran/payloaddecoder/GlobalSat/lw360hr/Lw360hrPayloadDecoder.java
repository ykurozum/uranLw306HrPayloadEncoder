package com.cisco.japan.as.uran.payloaddecoder.GlobalSat.lw360hr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cisco.japan.as.uran.payloadencoder.DecodedPayload;
import com.cisco.japan.as.uran.payloadencoder.EncodedPayload;
import com.cisco.japan.as.uran.payloadencoder.PayloadEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Lw360hrPayloadDecoder implements PayloadEncoder {

	/**
	 * Lt501のPayload変換処理
	 * 
	 * @param encodedPayloadList 変換対象のリスト
	 * @return decodeInfoList デコード情報のリスト
	 */
	public List<DecodedPayload> encode(List<EncodedPayload> encodedPayloadList) throws Exception {

		// ObjectMapperを作成
		ObjectMapper mapper = new ObjectMapper();

		List<DecodedPayload> decodeInfoList = new ArrayList<DecodedPayload>();
		DecodedPayload decodeInfo;

		return decodeInfoList;
	}

	/**
	 * デコード情報を詰め込む
	 * 
	 * @param payloadObject Jsonの中身
	 * @param payload       Link_upTime&Payload_Hex
	 * @param dateTime      Payload内の日時
	 * @return decodeInfo デコード情報
	 */
	private static DecodedPayload makeDecodeInfo(ObjectNode payloadObject, EncodedPayload payload,
			Date payloadDateTime) {

		DecodedPayload decodeInfo = new DecodedPayload(payload.getTime(), payloadDateTime, payload.getPayloadString(),
				payloadObject, payload.getDeviceIdentifiyer());

		return decodeInfo;

	}
}
