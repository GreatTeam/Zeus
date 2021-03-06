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
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
  </head>

  <body>

    <div class="container">

      <form class="form-signin" action="/zeus/user/login" method="post">
        <label for="inputUsername" class="sr-only">用户名</label>
        <input type="text" id="inputUsername" name="username" class="form-control" placeholder="用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
        </div>
        <input type="text" id="verification" name="verification"/>
        <input type="hidden" id="hiddenVerification" name="hiddenVerification"/>
        <img id="mVcodeImg" class="vCode" width="120" height="50" alt="验证码" src="/zeus/verification" style="cursor:pointer;"/>
        <a id="reCodeImg" href="javascript:void(0);">换一张</a>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        <button class="btn btn-lg btn-primary btn-block" id="register" type="button">注册</button>
      </form>

    </div> <!-- /container -->

  </body>
  <script type="text/javascript">
  	$(function(){
  		var reMath=Math.floor(Math.random()*(99999999999));
  		$("#mVcodeImg").attr('src','/zeus/verification?reMath='+reMath);
  		$("#hiddenVerification").val(reMath);
  	});
  	$("#register").click(function(){
  		window.location.href="http://localhost:8080/zeus/register";
  	});
  	$("#reCodeImg").click(function (){
  		var reMath=Math.floor(Math.random()*(99999999999));
  		$("#mVcodeImg").attr('src','/zeus/verification?reMath='+reMath);
  		$("#hiddenVerification").val(reMath);
  	});
  </script>
</html>
