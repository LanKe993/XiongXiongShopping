var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("TT_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://sso.lvhaotnt.top/user/token/" + _ticket,
//			url : "http://localhost:8084/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到熊熊！<a href=\"\" class=\"link-logout\" id=\"logout\">[退出]</a>";
					$("#loginbar").html(html);
					$("#logout").click(function () {
						   $.ajax({
						       url : "http://sso.lvhaotnt.top/user/logout/" + _ticket,
//						       url : "http://localhost:8084/user/logout/" + _ticket,
						       dataType : "jsonp",
						       type : "GET",
						       error: function(request) {  //失败
						           alert("登出失败！");
						       },
						       success: function(data) {  //成功
						       if (data.status == 200){
						           alert("登出成功！");
						           window.location.href="http://www.lvhaotnt.top"
//							       window.location.href="http://localhost:8082"
						       }
						   }
						 });
						});
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});