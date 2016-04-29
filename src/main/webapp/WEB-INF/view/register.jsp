<%@ page     contentType="text/html;charset=utf-8" %> 
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>
    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://localhost:8080/zeus/resources/css/signin.css" rel="stylesheet" type="text/css"/>
    <script src="http://localhost:8080/zeus/resources/css/jquery-1.11.1.js" type="text/javascript"></script>
  	<script src="http://localhost:8080/zeus/resources/js/validate.js" type="text/javascript"></script>
  	<!--<script src="http://localhost:8080/zeus/resources/js/messages_zh.js" type="text/javascript"></script>-->
  </head>

  <body>

    <div class="container">

      <form class="form-signin" action="/zeus/user/saveRegister" method="post">
        <label for="inputUsername" class="sr-only"></label>
        <input type="text" id="inputUsername" name="username" class="form-control" placeholder="请输入用户名">
        <label for="inputPassword" class="sr-only"></label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="请输入密码">
        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
      </form>

    </div> <!-- /container -->
	
  </body>

  <script type="text/javascript">
  	$(function(){
  		$(".form-signin").validate({
  			rules:{
  				username:{
  					required:true,
  					rangelength:[6,20],
  					remote:{                                          //验证用户名是否存在
  	  	               type:"POST",
  	  	               url:"user/ajaxName",             //servlet
  	  	              }
  				}, 
  				password: {
  					required:true,
  					minlength:6
  				}
  			}, 
  			messages: {						
  		  	     username:{
  		  	    	 required:"用户名不能为空！",
  		  	    	 rangelength:"用户名位数必须在6到20字符之间！",
  		  	    	 remote:"用户名已经被注册"
  		  	    	 },
  		  	     password: {
  		  	    	 required:"密码不能为空！",
  		  	    	 minlength:"密码位数必须大于等于6个字符！"
  		  	    	 }
  		  	    }	
  		});
  	});
  </script>
</html>
