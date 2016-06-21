
$(function() {
	formValidate();// 字段校验
});

function formValidate() {
	$('#articleFormEdit').validator({
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
			 
			'title' : {
				rule : '文章标题:required;'
			 
			},
			'categoryId' :{
				rule : '文章分类:required;'
			},
			'seq' :{
				rule : '排序:required;'
			},
			'content' :{
				rule : '文章内容:required;'
			}
		}
	});
}
