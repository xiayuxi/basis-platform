<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ync-test</title>
		
		<script type="text/javascript" src = "./resources/base/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src = "./resources/base/uic-passport.js"></script>
		<script type="text/javascript" src = "./resources/layer/layer.min.js"></script>
	
		<script type="text/javascript">
			//定义单点登录的基本配置信息
			var uic_config = {
				contextPath:"/",
				loginTheme:"yncapp"
			};
			
			//定义测试js,只用来进行测试
			function requestAjaxInSameDomainTest(){
				var url = "http://x.ync365.com/uic-test/test/ajax.htm";
				var param = "";
				var method = "get";
				requestAjaxInSameDomain(url,param,method,success,error);
			}
			
			function success(data){
				alert("message:" + data.message + ";yncuid:" + data.yncuid);
			}
			
			function error(data){
				console.log("受限资源访问失败");
			}
		</script>
	</head>
	<body>
		<h2>您好，欢迎使用ync365单点登录测试程序！</h2>
		<ul>
			<li><a href = "./test/member.htm">1.受限资源，需要登录才能访问！URL:http://x.ync365.com/uic-test/test/member</a></li>
			<li><a href = "./view.jsp">1.2非受限资源！URL:http://x.ync365.com/uic-test/view</a></li>
			<li><a href = "./test/member.htm?yncsid=1111111">1.3仿冒sid访问受限资源！URL:http://x.ync365.com/uic-test/test/member?yncsid=1111111</a></li>
			<li><a href = "http://y.ync365.com/uic-test/test/member.htm">2.已登录情况下，访问同域名下其他应用的受限资源！URL：http://y.ync365.com/uic-test/test/member</a></li>
			<li><a href = "http://x.ync365.cn/uic-test/test/member.htm">3.已登录情况下，夸域访问受限资源！URL：http://x.ync365.cn/uic-test/test/member</a></li>
			<li><a href = "javascript:void(0)" onclick = "javascript:requestAjaxInSameDomainTest()">4.ajax方式访问当前域名下的受限资源！</a></li>
			<li><a href = "./testLogin/autoLogin?yncuid=1&ac=yncapp&targetUrl=http://x.ync365.com/uic-test/test/member.htm">5.自动登录（userid=1,target:http://x.ync365.cn/uic-test/test/member.htm）！</a></li>
			<li><a href = "https://x.ync365.com:8443/uic-test/view.jsp">6.1同域登录访问HTTPS非受限资源</a></li>
			<li><a href = "https://x.ync365.com:8443/uic-test/test/member.htm">6.2同域登录访问HTTPS受限资源(多用户登录测试)</a></li>
			<li><a href = "https://x.ync365.cn:8443/uic-test/view.jsp">7.1不同域登录访问HTTPS非受限资源</a></li>
			<li><a href = "https://x.ync365.cn:8443/uic-test/test/member.htm">7.2不同域登录访问HTTPS受限资源</a></li>
			<li><a href = "https://passport.ync365.com:8443/uic-web/user/logout">8.单点登出</a></li>
		</ul>
	</body>
</html>