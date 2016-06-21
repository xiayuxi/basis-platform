
$(function() {
	formValidate();// 字段校验
});

function formValidate() {
	$('#featureFormAdd').validator({
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
			 
			'name' : {
				rule : '属性名称:required;'
			 
			},
			'categoryIds[0]' :{
				rule : '所属分类:required;'
			},
			'seq' :{
				rule : '排序:required;'
			},
			'fVList[0].attrValue' :{
				rule : '属性值:required;'
			}
		}
	});
}
