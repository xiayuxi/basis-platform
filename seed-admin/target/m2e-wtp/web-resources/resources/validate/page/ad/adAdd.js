
$(function() {
	formValidate();// 字段校验
});

function formValidate() {
	$('#adFormAdd').validator({
		// 是否在验证出错时停止继续验证，默认不停止false
		stopOnError : true,
		// msgClass : "n-bottom",
		messages : {
			required : "{0}不能为空"
		},
		// 验证规则
		rules : {
		},
		// 验证
		fields : {
			'page' : {
				rule : '目标页面:required;'
			},
			'adPosition' : {
				rule : '目标广告位:required;'
			},
			'title' :{
				rule : '广告名称:required;'
			},
			'beginDate' :{
				rule : '投放时间:required;'
			},
			'endDate' :{
				rule : '投放时间:required;'
			},
			'path' :{
				rule : '图片:required;'
			}
		}
	});
}
