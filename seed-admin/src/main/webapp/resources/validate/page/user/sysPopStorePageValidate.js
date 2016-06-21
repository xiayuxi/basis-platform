
$(function() {
	formValidate();// 字段校验
});

function formValidate() {
//	var rootPath = getRootPath();
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
			popStoreName:[/([\u4e00-\u9fa5a-zA-Z]+[0-9]*)/,'不允许纯数字'],
			popLoginName:[/^(\w|-|[\u4e00-\u9fa5]){4,20}$/,'最少4个字符，最多20个字符'],
			moblie:[/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,'请输入正确手机号'],
			number:[/^[0-9]+(.[0-9]{0,2})?$/,'请输入数字'],
			telephone:[/(^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)/,'请输入正确座机号'],
			idCard:[/(^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)/,'请输入正确身份证号'],
			uniquenessRegion:function(el, param, field){
				var status = true;
				$('select[name^=serviceDistincts]').each(function(index,element){
					alert(element.name+"____"+el.name);
					if(element.name!=el.name){
						var serviceDistinctsVal_element = $(element).find("option:selected").val();
						var serviceDistinctsVal_el = $(el).find("option:selected").val();
						alert(serviceDistinctsVal_element+'___'+serviceDistinctsVal_el);
						if(serviceDistinctsVal_element==serviceDistinctsVal_el){
							status = false;
							return false;
						}
					}
				});
				return status;
			},
			checkSelect:function(element){
				var selected = $(element).find("option:selected").val();
				return /^[0-9]+(.[0-9]{0,2})?$/.test(selected)|| '请选择地址';
			}
		},
		// 验证
		fields : {
			'popStoreName' : {
				rule : '商铺名称:required; popStoreName; remote['+rootPath+'/sysPopStore/checkSysPopStoreName?popStoreNum='+ popStoreNum +']'
			},
			'popStoreContact' : {
				rule : '店铺联系人:required;'
			},
			'popStoreMobile' : {
				rule : '联系电话:required; moblie; remote['+rootPath+'/sysPopStore/searchSysPopStoreMobile?popStoreNum='+ popStoreNum +']'
			},
			'popStoreDomain' : {
				rule : '商铺域名:required; remote['+rootPath+'/sysPopStore/checkSysPopStoreDomain?popStoreNum='+ popStoreNum +']'
			},
			'popLoginName' : {
				rule : '供应商账号:required; popLoginName; remote['+rootPath+'/sysPopStore/searchSysPopLoginName?popStoreNum='+ popStoreNum +']'
			},
			'popStorePrincipal' : {
				rule : '负责人:required;'
			},
			'popStoreAddressDetail' : {
				rule : '详细地址:required;'
			},
			'popStoreAddress' : {
				rule : '详细地址:required;'
			},
			'popStoreType' : {
				rule : '入驻商类型:required;',
				msg : {
					required : '必须选择入驻商类型'
				}
			}
		}
	});
}

//获取项目域名 + 项目名称
function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
