<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>ע�� </title>
<style>
 	body {background-color: #0066FF;}
 	#loginTab {background-color: #f5f5f5;border-style:double;border-width:6px;border-color:#ddd;padding:10px 70px;}
	.lable-td {text-align:right;padding-left:22px;padding-top:20px;padding-bottom:20px;font-size:14px;font-weight:bold;color:#444;}
	.input-td {padding-left:15px;}
	.input-td input{width:200px;height:22px;}
</style>
<script type="text/javascript" src="jscript/jquery-1.8.3.min.js" ></script>
<script type="text/javascript">
	function sub(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		if(username==""){
			alert("�û�������Ϊ�գ�");
			return false;
		}
		if(password==""){
			alert("���벻��Ϊ�գ�");
			return false;
		}
		//document.getElementById("loginFm").submit();
		$.ajax({
			url : "<%=request.getContextPath()%>/register.do",
			data : $("#loginFm").serialize(),
			type : 'POST',
			dataType : 'json',
			success : function(response) {
				var data=eval(response);
				if(data.success){
					alert(data.message);
					window.location.href='login.jsp';
				}else{
					alert(data.message);
				}
			}
		}); 
	};	
</script>
</head>
<body>
<div style="width:100%;padding-top:50px;">
  <form id="loginFm" action="<%=request.getContextPath()%>/user/login" method="post">
  	<table id="loginTab" align="center">
  		<tr>
  			<td colspan="2" style="padding-top:30px;padding-bottom:30px;text-align:center;font-size:23px;font-family:'����';font-weight:bold;color:black; ">
				�û�ע��
			</td>
  		</tr>
	  	<tr>
		  	<td class="lable-td">�û���</td>
			<td class="input-td"><input id="username" name="username"/><td/>
		</tr>
		<tr>
		  	<td class="lable-td">������λ</td>
			<td class="input-td"><input id="unit" name="unit"/><td/>
		</tr>
		<tr>
		  	<td class="lable-td">��ϵ��ʽ</td>
			<td class="input-td"><input id="tel" name="tel"/><td/>
		</tr>
		<tr>
			<td class="lable-td">����</td>
			<td class="input-td"><input id="password" type="password" name="password"><td/>
		</tr>
		<tr>
			<td class="lable-td">����ȷ��</td>
			<td class="input-td"><input id="passwordAgain" type="password" name="passwordAgain"><td/>
		</tr>
		<tr>
			<td colspan="2" style="padding-top:30px;padding-bottom:30px;text-align:center;">
				<input type="button" value="�� ��" onclick="sub();" style="padding:7px 23px;font-size:14px;"/>
			</td>
		</tr>
	</table>	
  </form>
</div>
</body>
</html>