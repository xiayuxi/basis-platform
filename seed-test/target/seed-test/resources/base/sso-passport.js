//==============================
//	陈天宇
//	2015年1月1日
//	chentianyu_rr@163.com
//	处理单点登录ajax的请求
//	如果使用该js，需要引入jquery-1.8.0.js及以上版本
//==============================

//认证中心登录地址
var passportServerUrl;

//定义回到函数栈
var loginCallbackStack = [];
var loginLayer;
var serviceUrl;
var appCode;
/**
 * 处理同域名下ajax的单点登录请求
 * @param url
 * 					请求地址
 * @param param
 * 					请求参数
 * @param method
 * 					执行方法eg.  post  get
 * @param success
 * 					执行成功的回调函数，由客户端自行扩展
 * @param error
 * 					执行失败的回调函数，由客户端自行扩展
 */
function requestAjaxInSameDomain(url,param,method,success,error){
	if(typeof uic_config == 'undefined'){
		console.log("页面上未定义uic_config变量！");
		return;
	}
	
	$.ajax({
		url:url,
		type:method,
		cache:false,
		error:error,
		success:function(result){
			//根据返回结果进行操作
			if(result.isNotLogin){
				if(result.outDomain){
					alert("该应用不在单点登录白名单，请联系管理员！");
				}else{
					var requestAjaxInSameDomainAgain = function(yncsid){
						if(url.indexOf('?')<0){
							url = url + "?yncsid=" + yncsid;
						}else{
							url = url + "&yncsid=" + yncsid;
						}
						requestAjaxInSameDomain(url,param,method,success,error);
					}
					var login = function () {
						loginCallbackStack.push(requestAjaxInSameDomainAgain);
						passportServerUrl = result.serviceUrl;
						popupLoginContainer();
	                };
					probeAuthStatus(result.checkServiceUrl,requestAjaxInSameDomain,login,uic_config);
				}
			}else{
				success(result);
			}
		}
	});
}

function probeAuthStatus(checkService, hasLoginCallback, unLoginCallback, config) {
	if(typeof uic_config == 'undefined'){
		console.log("页面上未定义uic_config变量！");
		return;
	}
    $.ajax({
        url: checkService,
        crossDomain: true,
        cache: false,
        dataType: 'jsonp',
        success: function (data) {
            if (data.isLogin) {
            	hasLoginCallback(data.yncsid);
            } else {
            	unLoginCallback();
            }
        }
    });
}


function popupLoginContainer() {
	loginLayer = $.layer({
	    type: 1,
	    title: false,
	    area: ['auto', 'auto'],
	    border: [0], //去掉默认边框
	    shade: [0], //去掉遮罩
	    closeBtn: [0, false], //去掉默认关闭按钮
	    page: {
	        html: "<table border = '1px'><tr><td>用户名：</td><td><input id='userName' name='userName' value='chentianyu' /> </td><tr><tr><td>密码：</td>	<td><input id='password' name='password' type='password' value='chentianyu' /> </td><tr><tr id = 'messageHr'><td colspan = '2'><div id = 'message'></div></td></tr><tr><td colspan = '2'><input type='button' onclick = 'javascript:return l();' value='登录' /></td></tr><input type = 'hidden' id = 'ac' name = 'ac' value = '${ac!\'\'}'/></table>"
	    }
	});
}

function l(){
	var userName = $("#userName").val();
	var password = $("#password").val();
	if(null == userName || "" == userName){
		alert("请输入用户名！");
		return false;
	}
	if(null == password || "" == password){
		alert("请输入密码！");
		return false;
	}
	$.ajax({
		type:"get",
		url:passportServerUrl,
		data:{userName:userName,password:password,verificationCode:""},
		dataType:"jsonp",
		success:function(data){
			if(data.isSuccess){
				//验证ticket
				$.ajax({
					type:"get",
					dataType:"jsonp",
					url:data.service,
					success:function(result){
						if(result.isSuccess){
							loginSuccess(result.yncsid);
						}else{
							$("#messageHr").show();
							$("#message").html(result.message);
						}
					},
					error:function(result){
						$("#messageHr").show();
						$("#message").html("登录失败，请稍后再试！");
					}
				});
			}else{
				$("#messageHr").show();
				$("#message").html(data.message);
			}
		},
		error:function(data){
			$("#messageHr").show();
			$("#message").html("登录失败，请稍后再试！");
		}
	});
}


function loginSuccess(yncsid) {
	layer.close(loginLayer);
    var callback = loginCallbackStack.pop();
    if (callback != null)
        callback(yncsid);
}

