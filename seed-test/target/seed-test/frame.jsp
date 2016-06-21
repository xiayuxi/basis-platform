<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src = "./resources/base/jquery-1.8.0.min.js"></script>
	</head>
	<body>
		<h2>我的域名是：<script type = "text/javascript">document.write(document.domain)</script></h2>
		我是frame.jsp,<a href = "javascript:parent.do_main();">点击我调用main的do_main方法。</a>
	</body>
</html>