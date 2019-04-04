package com.cisco.japan.as.uran.payloaddecoder.summary;

import java.util.Date;

import com.cisco.japan.as.uran.payloaddecoder.bean.RportBean;
import com.cisco.japan.as.uran.payloaddecoder.constants.BeaconId;
import com.cisco.japan.as.uran.payloaddecoder.constants.BeaconType;
import com.cisco.japan.as.uran.payloaddecoder.constants.NodeElements;
import com.cisco.japan.as.uran.payloaddecoder.constants.ProtocolSummary;
import com.cisco.japan.as.uran.payloaddecoder.constants.UnknownStatus;
import com.cisco.japan.as.uran.payloaddecoder.decoder.AlarmTypeDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.BatteryCapacityDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.BeaconIdDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.BeaconTypeDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.CalorieDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.CoordinateDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.DateTimeDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.DistanceDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.GpsFixStatusDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.HeartRateDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.ReportTypeDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.RssiDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.SkinTemperatureDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.StepCountDecoder;
import com.cisco.japan.as.uran.payloaddecoder.decoder.TxPowerDecoder;
import com.cisco.japan.as.uran.payloadencoder.util.CommonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Report {
	
	/** 最大長 */
	private static final int MAX_LENGTH = 100;
	
	/**
	 * Reportデコード処理
	 * 
	 * @param payloadObject JSONオブジェクト
	 * @param hexStr        デコード用文字列
	 * @param rBean 
	 * @param hrBean        HelpReport格納用Bean
	 */
	public static void decodeReport(ObjectNode payloadObject, String hexStr, RportBean rBean) {

		// hexStrのlengthチェック
		if (CommonUtils.checkPayloadLength(hexStr, MAX_LENGTH)) {
			
			// protocol詰め
			CommonUtils.packingJson(payloadObject,NodeElements.PROTOCOL.getCode(),hexStr.substring(0,6));
			
			// 経度変換
			String longitude = CoordinateDecoder.decodeCoordinate(hexStr.substring(6, 14));
			CommonUtils.packingJson(payloadObject, NodeElements.LONGITUDE.getCode(), longitude);

			// 緯度変換
			String latitude = CoordinateDecoder.decodeCoordinate(hexStr.substring(14, 22));
			CommonUtils.packingJson(payloadObject, NodeElements.LATITUDE.getCode(), latitude);

			// GPS-FixStatus変換
			String gpsFixStatus = GpsFixStatusDecoder.decodeGpsFixStatus(hexStr.substring(22, 24));
			CommonUtils.packingJson(payloadObject, NodeElements.GPS_FIX_STATUS.getCode(), gpsFixStatus);
	
			// Report Type or Alarm Type
			if (hexStr.startsWith(ProtocolSummary.PERIOD_REPORT.getCode(), 2)){
				String reportType = ReportTypeDecoder.decodeReportType(hexStr.substring(22, 24));
				CommonUtils.packingJson(payloadObject, NodeElements.REPORT_TYPE.getCode(), reportType);
			}else {
				String AlarmType = AlarmTypeDecoder.decodeAlarmType(hexStr.substring(22, 24));
				CommonUtils.packingJson(payloadObject, NodeElements.AlARM_TYPE.getCode(), AlarmType);
			}
			
			// reserved
			String reserved = hexStr.substring(24,26);
			CommonUtils.packingJson(payloadObject, NodeElements.RESERVED.getCode(), reserved);			
						
			// カロリー変換
			String calorie = CalorieDecoder.decodeCalorie(hexStr.substring(26,30));
			CommonUtils.packingJson(payloadObject, NodeElements.CALORIE.getCode(), calorie);
			
			// バッテリー残容量変換
			String batteryCapacity = BatteryCapacityDecoder.decodeBatteryCapacity(hexStr.substring(30,32));
			CommonUtils.packingJson(payloadObject, NodeElements.BATTERY_CAPACITY.getCode(), batteryCapacity);
			
			// 年月日時分秒変換
			Date dateTime = DateTimeDecoder.decodeDateTime(hexStr.substring(32, 40));
			// ISO8601準拠の形に整形
			String isoDate = CommonUtils.toIsoDate(dateTime);
			CommonUtils.packingJson(payloadObject, NodeElements.DATE_AND_TIME.getCode(), isoDate);
			rBean.setDateTime(dateTime);
			
			// ビーコン種別変換
			String beaconType = BeaconTypeDecoder.decodeBeaconType(hexStr.substring(80, 82));
			
			// BeaconID変換
			if (!BeaconType.BEACON_NOT_AVAILABLE.getTagName().equals(beaconType)
					&& !UnknownStatus.UNKNOWN_BEACON_TYPE.getCode().equals(beaconType)) {

				String[] beaconId = BeaconIdDecoder.decodeBeaconId(hexStr.substring(40,80), beaconType);

				if (beaconType.equals(BeaconType.IBEACON.getTagName())) { // iBeacon
					CommonUtils.packingJson(payloadObject, BeaconId.IBEACON_UUID.getCode(), beaconId[0]);
					CommonUtils.packingJson(payloadObject, BeaconId.IBEACON_MAJOR_ID.getCode(), beaconId[1]);
					CommonUtils.packingJson(payloadObject, BeaconId.IBEACON_MINOR_ID.getCode(), beaconId[2]);
				} else if (beaconType.equals(BeaconType.EDDYSOTNE_BEACON.getTagName())) { // Eddystone Beacon
					CommonUtils.packingJson(payloadObject, BeaconId.EDDYSTONE_BEACON_ID.getCode(), beaconId[0]);
				} else { // ALTBeacon
					CommonUtils.packingJson(payloadObject, BeaconId.ALTBEACON_ID.getCode(), beaconId[0]);
				}
			}

			// BeaconType詰め込み
			CommonUtils.packingJson(payloadObject, NodeElements.BEACON_TYPE.getCode(), beaconType);
			
			// RSSI変換
			String rssi = RssiDecoder.decodeRssi(hexStr.substring(82, 84));
			CommonUtils.packingJson(payloadObject, NodeElements.RSSI.getCode(), rssi);

			// TxPower変換
			String txPower = TxPowerDecoder.decodeTxPower(hexStr.substring(84, 86));
			CommonUtils.packingJson(payloadObject, NodeElements.TXPOWER.getCode(), txPower);
			
			// 心拍数変換
			String heartRate = HeartRateDecoder.decodeHeartRate(hexStr.substring(86, 88));
			CommonUtils.packingJson(payloadObject, NodeElements.HEART_RATE.getCode(), heartRate);			
			
			// 皮膚温度変換
			String skinTemperature = SkinTemperatureDecoder.decodeSkinTemperature(hexStr.substring(88, 92));
			CommonUtils.packingJson(payloadObject, NodeElements.SKIN_TEMPERATRE.getCode(), skinTemperature);			
			
			// 歩数変換
			String stepCount = StepCountDecoder.decodeStepCount(hexStr.substring(92, 96));
			CommonUtils.packingJson(payloadObject, NodeElements.STEP_COUNT.getCode(), stepCount);	
			
			// 歩行距離変換
			String distance = DistanceDecoder.decodeDistance(hexStr.substring(96, 100));
			CommonUtils.packingJson(payloadObject, NodeElements.DISTANCE.getCode(), distance);	
			
		}else {
			CommonUtils.packingJson(payloadObject, NodeElements.ERROR.getCode(),
					UnknownStatus.UNKNOWN_FORMAT.getCode());
		}
	}
}