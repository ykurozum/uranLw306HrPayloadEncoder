package com.cisco.japan.as.uran.payloaddecoder.constants;

import com.cisco.japan.as.uran.payloaddecoder.util.EnumDecodeUtil;

public enum ProtocolSummary implements IEnumDecodable<String> {
	
	/** Period Report */
	PERIOD_REPORT("0226"),
	/** Alarm Report */
	ALARM_REPORT("0025");

    /**　コード値 */ 
	private String code;

	/** コードデコーダ */
	private static final EnumDecodeUtil<String, ProtocolSummary> decoder = EnumDecodeUtil
			.create(values());

	/**
	 *
	 * コンストラクタ
	 *
	 * @param code
	 *            コード値
	 */
	private ProtocolSummary(String code) {
		this.code = code;
	}

	/**
	 *
	 * コード値からコード定義を取得する
	 *
	 * @param code
	 *            コード値
	 * @return コード定義
	 */
	public static ProtocolSummary decode(String code) {
		return decoder.decode(code);
	}

	/**
	 * codeを取得します。
	 *
	 * @return コード値
	 */
	public String getCode() {
		return code;
	}
}


