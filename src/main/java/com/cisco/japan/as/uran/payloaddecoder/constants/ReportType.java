package com.cisco.japan.as.uran.payloaddecoder.constants;

import com.cisco.japan.as.uran.payloaddecoder.util.EnumDecodeUtil;
import com.cisco.japan.as.uran.payloaddecoder.util.EnumNameResolveUtil;

public enum ReportType implements IEnumResolvable<String>,IEnumDecodable<String> {

	/** Period Report */
	PERIOD_REPORT("2","Period Report");	
	/** コード値 */
	private String code;

	/** タグ名称 */
	private String tagName;

	/** コードデコーダ */
	private static final EnumDecodeUtil<String, ReportType> decoder = EnumDecodeUtil.create(values());

	/** Nameリゾルバ */
	private static final EnumNameResolveUtil<String, ReportType> nameResolver = EnumNameResolveUtil.create(values());
	/**
	 *
	 * コンストラクタ
	 *
	 * @param code
	 *            コード値
	 * @param tagName
	 *            タグ名称
	 */
	private ReportType(String code, String tagName) {
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
	public static ReportType decode(String code) {
		return decoder.decode(code);
	}

	/**
	 *
	 * ENUM名からコード定義を取得する
	 *
	 * @param name ENUM.name()
	 * @return ENUM
	 */
	public static ReportType nameResolve(String name) {
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
