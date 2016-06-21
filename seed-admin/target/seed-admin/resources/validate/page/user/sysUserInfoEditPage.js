
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
			mobileRepeat : "{0}此手机号已经存在"
		},
		// 验证规则
		rules : {
			moblie:[/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,'请输入正确手机号'],
			number:[/^[0-9]+(.[0-9]{0,2})?$/,'请输入数字'],
			mobileRepeat:function(el, param, field){
				return validateMobileRepeat(el.value);
			}
		},
		// 验证
		fields : {
			 
			'sysUserRealName' : {
				rule : '姓名:required;'
			},
			'userMobile' : {
				rule : '手机号:required;moblie;mobileRepeat;'
			},
			'registerLocation' : {
				rule : '所在区域:required;'
			},
			'sysUserFarmArea' : {
				rule : '农场面积:required;number;'
			}
		}
	});
}
