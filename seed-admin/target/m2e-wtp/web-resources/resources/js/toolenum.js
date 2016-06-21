//-----promotion项目相关的enum字典
//活动状态：1启动 2停止
function getPromotionStatusName(status) {
	switch (status) {
	case 0:
		return "未开始";
	case 1:
		return "进行中";
	case 2:
		return "已结束";
	default:
		return "错误类型";
	}
};