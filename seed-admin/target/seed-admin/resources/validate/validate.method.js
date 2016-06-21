/**
 * 某日期不能小于当前日期 日期格式为yyyy-MM-dd
 */
jQuery.validator.addMethod('earlyThanCurrent', function(value, element) {
			var ary = value.split('-');
			var date1_year = ary[0];
			var date1_mon = ary[1];
			var date1_day = ary[2];
			if (new Date() >= new Date(parseInt(date1_year, 10), parseInt(
							date1_mon, 10)
							- 1, parseInt(date1_day, 10)))
				return false;
			else
				return true;
		}, '不能小于当前日期');

/**
 * 邮编验证
 */
jQuery.validator.addMethod('postcode', function(value, element) {
			var nn = /^\d{6}$/;
			return this.optional(element) || nn.test(value);
		}, '请正确填写邮编');

/**
 * 手机号码验证
 */
jQuery.validator.addMethod("mobile", function(value, element) {
			var length = value.length;
			//var mobile = /^1+\d{10}$/;
			var mobile = /^(((13[0-9]{1})|(14[0-9]{1})|(18[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
			return this.optional(element)
					|| (length == 11 && mobile.test(value));
		}, "请正确填写您的手机号码");


// 字符验证
jQuery.validator.addMethod("commonString", function(value, element) {
			return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
		}, "只能包括中文字、英文字母、数字和下划线");

// 中文字两个字节
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
	var length = value.length;
	for (var i = 0; i < value.length; i++) {
		if (value.charCodeAt(i) > 127) {
			length++;
		}
	}
	return this.optional(element) || (length >= param[0] && length <= param[1]);
}, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");

// 电话号码验证
jQuery.validator.addMethod("tel", function(value, element) {
			//var tel = /^\d{3,4}-?\d{7,9}$/; // 电话号码格式010-12345678 --李旭12.5.22修改
			var tel=/^[0-9]{3,4}-{0,1}[0-9]{7,8}$/;//--李旭12.5.22修改
			return this.optional(element) || (tel.test(value));
		}, "请正确填写您的电话号码");

// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("phone", function(value, element) {
			var length = value.length;
			var mobile = /^(((13[0-9]{1})|(14[0-9]{1})|(18[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
			//var tel = /^\d{3,4}-?\d{7,9}$/; --李旭12.5.22修改
			var tel=/^[0-9]{3,4}-{0,1}[0-9]{7,8}$/;//--李旭12.5.22修改
			return this.optional(element)
					|| (tel.test(value) || mobile.test(value));

		}, "请正确填写您的联系电话");

// 验证正整数
jQuery.validator.addMethod("positiveIntegerNum", function(value, element) {
			var tel = /^[1-9]\d*$/;
			return this.optional(element) || (tel.test(value));
		}, "请输入正整数");

// 验证只能输入中文 （不能有其他字符）
jQuery.validator.addMethod("chineseV", function(value, element) {
			var rel = new RegExp("^[\\u4e00-\\u9fa5]+$", "");
			return this.optional(element) || (rel.test(value));
		}, "请输入中文");

// 验证大写英文字母 zhangna
jQuery.validator.addMethod("AtoZ", function(value, element) {
			var tel = /^[A-Z]+$/;
			return this.optional(element) || (tel.test(value));
		}, "请输入大写英文字母");

//验证只能输入中文或英文字母（不能有其他字符）
jQuery.validator.addMethod("CnToEn", function(value, element) {
			var rel = new RegExp("^[\\u4e00-\\u9fa5A-Za-z]+$", "");
			return this.optional(element) || (rel.test(value));
		}, "请输入中文或英文字母");

// 验证只能输入大写字母A~Z
jQuery.validator.addMethod("OnlyUpdate", function(value, element) {
			var rel = "/^[A-Z]+$/";
			return this.optional(element) || (tel.test(value));
		}, "请输入大写英文字母");

jQuery.validator.addMethod("Ato9", function(value, element) {
			var tel = /^[A-Za-z0-9]+$/;
			return this.optional(element) || (tel.test(value));
		}, "请输入英文字母或数字");

jQuery.validator.addMethod("Atoz", function(value, element) {
			var tel = /^[A-Za-z-|///]+$/;
			//var tel = /^[A-Za-z]+$/;
			return this.optional(element) || (tel.test(value));
		}, "请输入英文字母");
// 验证英文字母中间带“-”	var tel = /^\d{3,4}-?\d{7,9}$/;
jQuery.validator.addMethod("Atoz1", function(value, element) {
			  var tel = /^[A-Za-z]+$/;
	          var tel1= /^[a-zA-Z-]+$/;
	          var tel2= /^[a-zA-Z ]+$/;
			return this.optional(element)
				|| (tel.test(value) || tel1.test(value)|| tel2.test(value));
		}, "请输入英文字母");

// 验证正数和正整数 zhangna
jQuery.validator.addMethod("positiveNum", function(value, element) {
			var tel = /^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/;
			var tel1 = /^[1-9]\d*$/;
			return this.optional(element)
					|| (tel.test(value) || tel1.test(value));
		}, "请输入正数");

// 验证特殊字符 paul
jQuery.validator.addMethod("illegal", function(value, element) {
	var tel = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	return this.optional(element) || !(tel.test(value));
}, "请输入正确字符");


jQuery.validator.addMethod("illegal1", function(value, element) {
	var tel1 = /^[\\+\\-]+[0-9]+$/;
	return this.optional(element) ||tel1.test(value);
}, "请输入正确字符");


// 校验一个日期小于另外一个日期
jQuery.validator.addMethod('earlyThan', function(value, element, param) {
			var target = $(param).unbind(".validate-earlyThan").bind(
					"blur.validate-earlyThan", function() {
						$(element).valid();
					});
			if (value != "" && target.val() != "" && value > target.val()) {
				return false;
			} else {
				return true;
			}
		}, '开始时间不能大于结束时间');

// 15位和18位验证身份证
jQuery.validator.addMethod("isIdCardNo", function(value, element) {
	var tel = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	return this.optional(element) || idCardNoUtil.checkIdCardNo(value);
}, "请输入正确身份证号");

// 验证身份证15位和18位 paul
var idCardNoUtil = {
	/* 省,直辖市代码表 */
	provinceAndCitys : {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	},
	/* 每位加权因子 */
	powers : ["7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9",
			"10", "5", "8", "4", "2"],

	/* 第18位校检码 */
	parityBit : ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"],
	/* 性别 */
	genders : {
		male : "男",
		female : "女"
	},
	/* 校验地址码 */
	checkAddressCode : function(addressCode) {
		var check = /^[1-9]\d{5}$/.test(addressCode);
		if (!check)
			return false;
		if (idCardNoUtil.provinceAndCitys[parseInt(addressCode.substring(0, 2))]) {
			return true;
		} else {
			return false;
		}
	},
	/* 校验日期码 */
	checkBirthDayCode : function(birDayCode) {
		var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/
				.test(birDayCode);
		if (!check)
			return false;
		var yyyy = parseInt(birDayCode.substring(0, 4), 10);
		var mm = parseInt(birDayCode.substring(4, 6), 10);
		var dd = parseInt(birDayCode.substring(6), 10);
		var xdata = new Date(yyyy, mm - 1, dd);
		if (xdata > new Date()) {
			return false;// 生日不能大于当前日期
		} else if ((xdata.getFullYear() == yyyy)
				&& (xdata.getMonth() == mm - 1) && (xdata.getDate() == dd)) {
			return true;
		} else {
			return false;
		}
	},

	/* 计算校检码 */
	getParityBit : function(idCardNo) {
		var id17 = idCardNo.substring(0, 17);
		/* 加权 */
		var power = 0;
		for (var i = 0; i < 17; i++) {
			power += parseInt(id17.charAt(i), 10)
					* parseInt(idCardNoUtil.powers[i]);
		}
		/* 取模 */
		var mod = power % 11;
		return idCardNoUtil.parityBit[mod];
	},

	/* 验证校检码 */
	checkParityBit : function(idCardNo) {
		var parityBit = idCardNo.charAt(17).toUpperCase();
		if (idCardNoUtil.getParityBit(idCardNo) == parityBit) {
			return true;
		} else {
			return false;
		}
	},
	/* 校验15位或18位的身份证号码 */
	checkIdCardNo : function(idCardNo) {
		// 15位和18位身份证号码的基本校验
		var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(idCardNo);
		if (!check)
			return false;
		// 判断长度为15位或18位
		if (idCardNo.length == 15) {
			return idCardNoUtil.check15IdCardNo(idCardNo);
		} else if (idCardNo.length == 18) {
			return idCardNoUtil.check18IdCardNo(idCardNo);
		} else {
			return false;
		}
	},
	// 校验15位的身份证号码
	check15IdCardNo : function(idCardNo) {
		// 15位身份证号码的基本校验
		var check = /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/
				.test(idCardNo);
		if (!check)
			return false;
		// 校验地址码
		var addressCode = idCardNo.substring(0, 6);
		check = idCardNoUtil.checkAddressCode(addressCode);
		if (!check)
			return false;
		var birDayCode = '19' + idCardNo.substring(6, 12);
		// 校验日期码
		return idCardNoUtil.checkBirthDayCode(birDayCode);
	},
	// 校验18位的身份证号码
	check18IdCardNo : function(idCardNo) {
		// 18位身份证号码的基本格式校验
		var check = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}(\d|x|X)$/
				.test(idCardNo);
		if (!check)
			return false;
		// 校验地址码
		var addressCode = idCardNo.substring(0, 6);
		check = idCardNoUtil.checkAddressCode(addressCode);
		if (!check)
			return false;
		// 校验日期码
		var birDayCode = idCardNo.substring(6, 14);
		check = idCardNoUtil.checkBirthDayCode(birDayCode);
		if (!check)
			return false;
		// 验证校检码
		return idCardNoUtil.checkParityBit(idCardNo);
	},
	formateDateCN : function(day) {
		var yyyy = day.substring(0, 4);
		var mm = day.substring(4, 6);
		var dd = day.substring(6);
		return yyyy + '-' + mm + '-' + dd;
	},
	// 获取信息
	getIdCardInfo : function(idCardNo) {
		var idCardInfo = {
			gender : "", // 性别
			birthday : "" // 出生日期(yyyy-mm-dd)
		};
		if (idCardNo.length == 15) {
			var aday = '19' + idCardNo.substring(6, 12);
			idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
			if (parseInt(idCardNo.charAt(14)) % 2 == 0) {
				idCardInfo.gender = idCardNoUtil.genders.female;
			} else {
				idCardInfo.gender = idCardNoUtil.genders.male;
			}
		} else if (idCardNo.length == 18) {
			var aday = idCardNo.substring(6, 14);
			idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
			if (parseInt(idCardNo.charAt(16)) % 2 == 0) {
				idCardInfo.gender = idCardNoUtil.genders.female;
			} else {
				idCardInfo.gender = idCardNoUtil.genders.male;
			}

		}
		return idCardInfo;
	}
};

// 终端版本号校验
jQuery.validator.addMethod("version", function(value, element) { // 校验升级包版本号的有效性
			var reg = /^\d+\.{1}\d\.{1}\d+$/;
			if (reg.test($.trim(value))) {
				return true;
			} else {
				return false;
			}
		}, "版本号格式不正确,例1.7.2");
// 终端上传文件名校验

// 结算账期设置校验
jQuery.validator.addMethod("month_day", function(value, element) {
			var reg = new RegExp("^[1-9]{1,2}(,[1-9]{1,2})*$");
			return this.optional(element) || (reg.test(value));
		}, "输入的月日期格式不正确或只能输入0-99之间的数字");

jQuery.validator.addMethod("week_day", function(value, element) {
			var reg = new RegExp("^[1-7]{1}(,[1-7]{1})*$");
			return this.optional(element) || (reg.test(value));
		}, "输入的周日期格式不正确或只能输入1-7之间的数字");

// 匹配正浮点数
jQuery.validator.addMethod("floatNum", function(value, element) {
			var reg = new RegExp("^\\d+(\\.\\d+)?$");
			return this.optional(element) || (reg.test(value));
		}, "输入正数");

jQuery.validator.addMethod("numAnden", function(value, element) { // 校验终端编号
																	// wangjian
			var reg = /^[A-Z0-9]+$/;
			return this.optional(element) || (reg.test(value));
		}, "输入数字和大写英文");

jQuery.validator.addMethod("atmLength", function(value, element) { // 终端编号长度
																	// wangjian
			if (value.length < 9 || value.length > 20) {
				return false;
			}
			return true;
		}, "终端编号长度在9-20之间");

jQuery.validator.addMethod("unionpayLength", function(value, element) { // 银联编号长度
																		// wangjian
			if (value.length != 8) {
				return false;
			}
			return true;
		}, "银联编号长度为8位");

jQuery.validator.addMethod("sim", function(value, element) { // SIM卡校验
			var length = value.length;
			var mobile = /^1+\d{10}$/;
			return this.optional(element)
					|| (length == 11 && mobile.test(value));
		}, "请正确填写SIM卡号");

jQuery.validator.addMethod("num", function(value, element) { // 校验银联编号
																// wangjian
			var reg = /^\d*$/;
			return this.optional(element) || (reg.test(value));
		}, "输入数字");

jQuery.validator.addMethod("checkReceipt", function(value, element) { // 校验行程单号
																		// wangjian
			var reg = /^[1-9]{1}[0-9]{9}$/;
			return this.optional(element) || (reg.test(value));
		}, "输入10位数字,非0开始");

jQuery.validator.addMethod("checkScope", function(value, element) { // 校验行程单范围
																	// wangjian
			var tempStartno = $("#temp_receiptStartno").val();
			var tempEndno = $("#temp_receiptEndno").val();
			if (value >= tempStartno && value <= tempEndno) {
				return true;
			}
			return false;
		}, "注销单号超出绑定范围");

jQuery.validator.addMethod("checkEndno", function(value, element) { // 校验行程单结束单号是否小于开始单号
																	// wangjian
			var message = "";
			var startNo = $("#receiptStartno").val();
			var endNo = value;
			if (endNo - startNo < 0||endNo - startNo>=500) {
				return false;
			}
			return true;
		}, "终止单号大于开始单号,或超过500张");

jQuery.validator.addMethod("numAndupper", function(value, element) { // 校验终端类型
																		// wangjian
			var reg = /^[A-Z0-9]+$/;
			return this.optional(element) || (reg.test(value));
		}, "输入数字和大写英文");

jQuery.validator.addMethod("discribLength", function(value, element) { // 校验终端类型描述的长度
																		// wangjian
			if (value.length < 100) {
				return true;
			}
			return false;
		}, "字符串长度过长");

jQuery.validator.addMethod("validateAirPortCode", function(value, element) { // 假方法
																				// 永远返回false
																				// zhuchenglin

			return false;
		}, "机场三字码已存在");

jQuery.validator.addMethod("storeNum", function(value, element) { // 校验入库数量
			if (value == 0) {
				return true;
			}
			var tel = /^[1-9]\d*$/;
			return this.optional(element) || (tel.test(value));
		}, "请输入正整数");

jQuery.validator.addMethod("comparedDate", function(value, element, param) { // 日期比较
			var start = $(param).val();
			if (start == null || start == '' || value == null || value == '') {
				return true;
			}
			start = new Date(Date.parse(start.replace("-", "/")));
			var end = new Date(Date.parse(value.replace("-", "/")));
			start = new Date(Date.parse(start));
			return start <= end;
		}, "结束时间不能小于开始时间");

jQuery.validator.addMethod("comparedCount", function(value, element) { // 发货数量比较
			var num = 0;
			$("input[name='selAtmType']").each(function() {
						if ($(this).attr("checked") == true) {
							var type = $(this).attr("value");
							var val = $("#" + "count" + type).val();
							num += parseInt(val);
						}
					});
			var count = $("#count").val();
			if (count == num) {
				return true;
			}
			return false;
		}, "申请终端数量与发货类型数量不一致");


//jianghy 2011-11-29
jQuery.validator.addMethod("hourMinute", function(val, element) { // 校验时间只能是HH:MM形式
	  var regx = /^\d{2}\d{2}$/; 
	  if(regx.test(val)){
	  	var hour = parseInt(val.substring(0,2));
	  	var mm = parseInt(val.substring(3));
	  	return !(hour <0 || hour>=24 || mm<0 || mm>=60);
	  	
	  }else{
		return ;
	  }
}, "时间格式为 HHmm");

jQuery.validator.addMethod("comparedDateTime", function(value, element, param) { // 日期比较
	var start = $(param).val();
	if (start == null || start == '' || value == null || value == '') {
		return true;
	}
	start = new Date(Date.parse(start.replace("-", "/")));
	var end = new Date(Date.parse(value.replace("-", "/")));
	start = new Date(Date.parse(start));
	return start < end;
}, "结束时间不能小于开始时间");

