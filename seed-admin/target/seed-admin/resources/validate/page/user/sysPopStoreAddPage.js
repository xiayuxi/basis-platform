
$(function() {
	formValidate();// 字段校验
});


function formValidate() {
	$('#formId').validator({
		// 是否在验证出错时停止继续验证，默认不停止false
		stopOnError : true,
		timely:2,
		debug:1,
		// msgClass : "n-bottom",
		messages : {
			required : "{0}不能为空",
			uniquenessRegion : "重复区域",
			popLoginName : {
				required : "用户名不能为空",
				rangelength : "账号必须大于5个字符，小于16个字符"
			}
		},
		// 验证规则
		rules : {
			moblie:[/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,'请输入正确手机号'],
			number:[/^[0-9]+(.[0-9]{0,2})?$/,'请输入数字'],
			telephone:[/(^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)/,'请输入正确座机号'],
			idCard:[/(^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)/,'请输入正确身份证号'],
			uniquenessRegion:function(el, param, field){
				var status = true;
				$('select[name^=serviceDistincts]').each(function(index,element){
					if(element.name!=el.name){
						var serviceDistinctsVal_element = $(element).find("option:selected").val();
						var serviceDistinctsVal_el = $(el).find("option:selected").val();
						if(serviceDistinctsVal_element==serviceDistinctsVal_el){
							status = false;
							return false;
						}
					}
				});
				return status;
			},
			checkPopName:function(element){
				$.get("searchSysPop?popLoginName="+element.value, function(data){
					if(data) {
						return false;
					} else {
						return /^[a-zA-Z]\w{3,}/.test(element.value)|| '请填写供应商账号';
					}
				});
			},
			checkSelect:function(element){
				var selected = $(element).find("option:selected").val();
				return /^[0-9]+(.[0-9]{0,2})?$/.test(selected)|| '请选择地址';
			},
			domainCheck: {
				
			}
		},
		// 验证
		fields : {
			'popStoreName' : {
				rule : '商铺名称:required;'
			},
			'popStoreContact' : {
				rule : '店铺联系人:required;'
			},
			'popStoreDomain' : {
				rule : '商铺域名:required;remote[checkSysPopStoreDomain]'
			},
			'popStorePrincipal' : {
				rule : '负责人:required;'
			},
			'popStoreAddressDetail' : {
				rule : '详细地址:required;'
			},
			'popStoreAddress' : {
				rule : '详细地址:required;'
			}
		}
	});
	
}
