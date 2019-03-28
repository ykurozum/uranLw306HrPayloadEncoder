package com.cisco.japan.as.uran.payloaddecoder.constants;

import com.cisco.japan.as.uran.payloaddecoder.util.EnumDecodeUtil;
import com.cisco.japan.as.uran.payloaddecoder.util.EnumNameResolveUtil;

public enum GpsFixStatus implements IEnumResolvable<String>, IEnumDecodable<String> {

	/** Not Fix */
	NOT_FIX("0", "Not Fix"),
	/** 2D Fix */
	FIX_2D("1", "2D Fix"),
	/** 3D Fix */
	FIX_3D("2", "3D Fix");

	/** コード値 */
	private String code;

	/** タグ名称 */
	private String tagName;

	/** コードデコーダ */
	private static final EnumDecodeUtil<String, GpsFixStatus> decoder = EnumDecodeUtil.create(values());

	/** Nameリゾルバ */
	private static final EnumNameResolveUtil<String, GpsFixStatus> nameResolver = EnumNameResolveUtil.create(values());

	/**
	 *
	 * コンストラクタ
	 *
	 * @param code    コード値
	 * @param tagName タグ名称
	 */
	private GpsFixStatus(String code, String tagName) {
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
	public static GpsFixStatus decode(String code) {
		return decoder.decode(code);
	}

	/**
	 *
	 * ENUM名からコード定義を取得する
	 *
	 * @param name ENUM.name()
	 * @return ENUM
	 */
	public static GpsFixStatus nameResolve(String name) {
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