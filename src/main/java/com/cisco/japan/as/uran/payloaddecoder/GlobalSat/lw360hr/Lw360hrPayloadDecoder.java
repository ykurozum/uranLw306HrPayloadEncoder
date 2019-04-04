package com.cisco.japan.as.uran.payloaddecoder.GlobalSat.lw360hr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cisco.japan.as.uran.payloaddecoder.bean.RportBean;
import com.cisco.japan.as.uran.payloaddecoder.constants.NodeElements;
import com.cisco.japan.as.uran.payloaddecoder.constants.ProtocolSummary;
import com.cisco.japan.as.uran.payloaddecoder.constants.UnknownStatus;
import com.cisco.japan.as.uran.payloaddecoder.summary.Report;
import com.cisco.japan.as.uran.payloadencoder.DecodedPayload;
import com.cisco.japan.as.uran.payloadencoder.EncodedPayload;
import com.cisco.japan.as.uran.payloadencoder.PayloadEncoder;
import com.cisco.japan.as.uran.payloadencoder.util.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Lw360hrPayloadDecoder implements PayloadEncoder {

	/**
	 * Lw360のPayload変換処理
	 * 
	 * @param encodedPayloadList 変換対象のリスト
	 * @return decodeInfoList デコード情報のリスト
	 */
	public List<DecodedPayload> encode(List<EncodedPayload> encodedPayloadList) throws Exception {

		// ObjectMapperを作成
		ObjectMapper mapper = new ObjectMapper();

		List<DecodedPayload> decodeInfoList = new ArrayList<DecodedPayload>();
		DecodedPayload decodeInfo;

		for (EncodedPayload payload : encodedPayloadList) {

			ObjectNode payloadObject = mapper.createObjectNode();

			// payloadStringからpayload_hexを取得
			String hexStr = payload.getPayloadString();
			
			// payload存在チェック
			if (hexStr != null && !hexStr.isEmpty())
				// protocolチェック
				if (hexStr.startsWith(ProtocolSummary.PERIOD_REPORT.getCode(), 2)
						|| hexStr.startsWith(ProtocolSummary.ALARM_REPORT.getCode(), 2)) {
					
					RportBean rBean = new RportBean();
					
					Report.decodeReport(payloadObject, hexStr,rBean);
					decodeInfo = makeDecodeInfo(payloadObject, payload, rBean.getDateTime());

				} else { // unknwonProtocol

					CommonUtils.packingJson(payloadObject, NodeElements.PROTOCOL.getCode(),
							UnknownStatus.UNKNOWN_PROTOCOL.getCode());
					decodeInfo = makeDecodeInfo(payloadObject, payload, null);
				}
			else {// hexStr = null
				CommonUtils.packingJson(payloadObject, NodeElements.ERROR.getCode(),
						UnknownStatus.UNKNOWN_FORMAT.getCode());
				decodeInfo = makeDecodeInfo(payloadObject, payload, null);
			}
			decodeInfoList.add(decodeInfo);
		}
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
