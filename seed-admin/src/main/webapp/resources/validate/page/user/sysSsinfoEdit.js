
$(function() {
	formValidate();// 字段校验
});

function formValidate() {
	$('#formId').validator({
		// 是否在验证出错时停止继续验证，默认不停止false
		stopOnError : true,
		// msgClass : "n-bottom",
		messages : {
			required : "{0}不能为空",
			uniquenessLoginName : "用户名已经注册！",
			uniquenessMobile : "手机号已经注册！",
		},
		// 验证规则
		rules : {
			moblie:[/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,'请输入正确手机号'],
			number:[/^[0-9]+(.[0-9]{0,2})?$/,'请输入数字'],
			telephone:[/\d{3}-\d{8}|\d{4}-\{7,8}|\d{4}\{7,8}|\d{3}\d{8}/,'请输入正确座机号'],
			idCard:[/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/,'请输入正确身份证号'],
			noNumber:[/^\D+.*|\d+\D+.*$/,'输入的不能全为数字'],
			loginReg:[/^(\w|\-|[\u4e00-\u9fa5]){4,20}$/,'4-20位字符，支持汉字、字母、数字及“-”、“_”组合'],
			noNull:[/^\S+$/,'不能输入空格'],
			uniquenessLoginName:function(el, param, field){
				return loginName_fun();
			},
			uniquenessMobile:function(el, param, field){
				return mobile_fun();
			},
		},
		// 验证
		fields : {
			 
			'userName' : {
				rule : '用户名:required;noNull;noNumber;loginReg;uniquenessLoginName;'
			 
			},
			'userRealName' : {
				rule : '姓名:required;'
			 
			},
			'userMobile' : {
				rule : '手机号:required;moblie;uniquenessMobile;'
			 
			},
			'userIdCard' : {
				rule : '身份证号:required;idCard;'
			 
			},
			'shopNameType' : {
				rule : '商铺名称:required;'
			 
			}
		}
	});
}
