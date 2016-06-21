<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>frame回调测试</title>
		
		<script type="text/javascript" src = "./resources/base/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src = "./resources/base/passport.js"></script>
		<script type="text/javascript" src = "./resources/layer/layer.min.js"></script>
	
		<script type="text/javascript">
			function do_main(){
				alert("main.jsp的域名：" + document.domain);
			}
		</script>
	</head>
	<body>
		<h2>我的域名是：<script type = "text/javascript">document.write(document.domain)</script>，我有个函数do_main()，<a href = "javascript:do_main();">点击调用</a></h2>
		以下是<b><font color = 'red'>同域</font></b>的frame，我<font color = 'green'><b>可以调用[main.jsp中的do_main]</b></font>,访问地址：http://x.ync365.con/uic-test/frame.jsp<br>
		<iframe src = "./frame.jsp" width = "600px" height = "200px" ></iframe><br/>
		以下是<b><font color = 'red'>不同域</font></b>的frame，我<font color = 'red'><b>不可以调用[main.jsp中的do_main]</b></font>，访问地址：http://x.ync365.cn/uic-test/frame.jsp<br>
		<iframe src = "http://x.ync365.cn/uic-test/frame.jsp" width = "600px" height = "200px" ></iframe><br/>
	</body>
</html>