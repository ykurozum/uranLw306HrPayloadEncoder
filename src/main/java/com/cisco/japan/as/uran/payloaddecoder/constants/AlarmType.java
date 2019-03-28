package com.cisco.japan.as.uran.payloaddecoder.constants;

import com.cisco.japan.as.uran.payloaddecoder.util.EnumDecodeUtil;
import com.cisco.japan.as.uran.payloaddecoder.util.EnumNameResolveUtil;

public enum AlarmType implements IEnumResolvable<String>, IEnumDecodable<String> {

	/** Help */
	HELP("1", "Help"),
	/** Man Down */
	MAN_DOWN("2", "Man Down"),
	/** Low Heart Rate Report */
	LOW_HEART_RATE("8", "Low Heart Rate");

	/** コード値 */
	private String code;

	/** タグ名称 */
	private String tagName;

	/** コードデコーダ */
	private static final EnumDecodeUtil<String, AlarmType> decoder = EnumDecodeUtil.create(values());

	/** Nameリゾルバ */
	private static final EnumNameResolveUtil<String, AlarmType> nameResolver = EnumNameResolveUtil.create(values());

	/**
	 *
	 * コンストラクタ
	 *
	 * @param code    コード値
	 * @param tagName タグ名称
	 */
	private AlarmType(String code, String tagName) {
		this.code = code;
		this.tagName = tagName;
	}

	/**
	 *
	 * コード値からコード定義を取得する
	 *
	 * @param code コード値
	 * @return コード定義
	 */
	public static AlarmType decode(String code) {
		return decoder.decode(code);
	}

	/**
	 *
	 * ENUM名からコード定義を取得する
	 *
	 * @param name ENUM.name()
	 * @return ENUM
	 */
	public static AlarmType nameResolve(String name) {
		return nameResolver.resolve(name);
	}

	/**
	 * codeを取得します。
	 *
	 * @return コード値
	 */
	public String getCode() {
		return code;
	}

	/**
	 * tagNameを取得します。
	 *
	 * @return タグ名称
	 */
	public String getTagName() {
		return tagName;
	}
}
