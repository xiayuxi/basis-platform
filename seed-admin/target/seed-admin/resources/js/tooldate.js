//根据Date.getDay()获取的星期索引，返回星期的中午名称(索引中，1表示星期一，依此类推；0表示星期日)
function getWeekDayName(weekDayIndex) {
	switch (weekDayIndex) {
		case 1:
			return "星期一"
			break;
		case 2:
			return "星期二"
			break;
		case 3:
			return "星期三"
			break;
		case 4:
			return "星期四"
			break;
		case 5:
			return "星期五"
			break;
		case 6:
			return "星期六"
			break;
		case 0:
			return "星期日"
			break;
		default:
			return "入参：星期索引有误";
			break;
	}
};
//格局化日期：yyyy-MM-dd 
function formatDate(date) {
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	return (year + "-" + month + "-" + day);
};
//判断年份是否是闰年
function isLeapYear(year) {
	if (isNaN(year) && year > 0) {
		if (year % 100 != 0 && year % 4 == 0) { //非整百年能被4整除的为闰年
			return true;
		} else if (year % 400 == 0) { //整百年能被400整除的是闰年
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
};
//将时间戳转换为时间，时间戳举例：1438444800000
function timestampToDate(val) {
	var date = new Date(val);
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
};
//将时间戳转换为时间，时间戳举例：1438444800000
function timestampToTime(val) {
	var date = new Date(val); 
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()+" "
	+ (date.getHours()<10?"0":"") + date.getHours() + ":" 
	+ (date.getMinutes()<10?"0":"") + date.getMinutes() + ":" 
	+ (date.getSeconds()<10?"0":"") + date.getSeconds();
};
//Date(-2209017600000)转化为 1900 - 01 - 01
//公共方法：时间类型转换
function strToDate(val) {
	if (val != null) {
		if (val == "/Date(-2209017600000)/") {
			return ""; //如果为1900-01-01，则认为时间为空
		}
		var date = new Date(parseInt(val.replace("/Date(", "").replace(")/", ""), 10));
		//月份为0-11，所以+1，月份小于10时补个0
		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
		var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		return date.getFullYear() + "-" + month + "-" + currentDate;
	}
};