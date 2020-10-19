<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>

<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link type="text/css" rel="stylesheet" href="./resources/css/pm.css" />

<script type="text/javascript" src="/resources/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="./resources/js/common.js"></script>
<script type="text/javascript" src="./resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="./resources/js/jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript" src="./resources/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="./resources/js/myAjax.js"></script>
<script type="text/javascript" src="./resources/js/pageCommon.js"></script>

<title>UCT Login</title>

<script type="text/javascript">
	$(document).ready(function() {
		// enter event
		$('#MBER_PW').keyup(function(e) {
			if (e.keyCode == 13) {
				pageAction();
			}
		});
		
		$('#MBER_ID').focus().select();
		
		var cookie = getCookie("MBER_ID");
		if(cookie != ""){
			$(":input[type=checkbox][id=save_id]").attr("checked", true);
			$("#MBER_ID").val(cookie);
		}
		
		$("#btnOK").click(function() {
			pageAction();
		});
		
	});// END Ready
	
	function pageAction(){

		
		if(checkLogin()){
			var jData = "MBER_ID="+encodeURIComponent($("#MBER_ID").val())+
			"&MBER_PW="+encodeURIComponent($("#MBER_PW").val());
			
			getAjax("/pjt/checkLogin.do", jData, setAlert);
		} 
	}
	
	function checkLogin(){
		
		if($("#MBER_ID").val().trim().length == 0) {
			alert("아이디를 입력해 주십시요.");
			$('#MBER_ID').focus().select();
			return false;
		}
		
		if($("#MBER_ID").val().trim().length > 20) {
			alert("아이디는 20자 이하 입니다.");
			$('#MBER_ID').focus().select();
			return false;
		}
		
		if($("#MBER_PW").val().trim().length == 0) {
			alert("비밀번호를 입력해 주십시요.");
			$('#MBER_PW').focus().select();
			return false;
		}
		
		if($("#MBER_PW").val().trim().length > 50) {
			alert("비밀번호는 50자 이하 입니다.");
			$('#MBER_PW').focus().select();
			return false;
		}
		return true;
	}
	
	function setAlert(data) { 
		
		if(data.retCd == 'success'){
			if($(":input[type=checkbox][id=save_id]").is(":checked")){
				setCookie("MBER_ID", $("#MBER_ID").val(), 365);
			}else{
				setCookie("MBER_ID", $("#MBER_ID").val(), 0);
			}
			goForm.action = "/pjt/uct/pmList.do";
			goForm.submit();
		}else{
			
			alert(data.retMsg);
			$('#MBER_ID').val('').focus().select();
			$('#MBER_PW').val('');
		}
	}

	function setCookie(id, value, date) {
		var expire = new Date();
		expire.setDate(expire.getDate() + date);
		cookies = id + '=' + value + '; ';
		
		if (typeof date != 'undefined') {
			cookies += ';expires=' + expire.toGMTString() + ';';
		}
		document.cookie = cookies;
	}
	
	function getCookie(id) {
		id = id + '=';
		var value = '';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(id);
		
		if (start != -1) {
			start += id.length;
			var end = cookieData.indexOf(';', start);
			
			if (end == -1) {
				end = cookieData.length;
			}
			
			value = cookieData.substring(start, end);
		}// END if
		return value;
	}
	
	</script>

<!-- contents -->
<div id="contents">
	<div class="inner">
		<form name="goForm" method="post"></form>
		<fieldset>
			<legend>UCT Admin 로그인</legend>
			<div class="login_sect">
				<div class="login_bx">
					<div class="login_form">
						<p>관리자 ADMIN</p>
						<div class="inp_area">
							<input type="text" id="MBER_ID" name="MBER_ID" placeholder="아이디" class="admin_id" value=""/>
							<input type="password" id="MBER_PW" name="MBER_PW" class="pwd" placeholder="비밀번호" value=""/>
						</div>
						<a href="javascript:void(0);" class="btn btn_login" id="btnOK">로그인</a>
						<div class="save_id mt10">
							<input type="checkbox" id="save_id" name="save_id" /><label for="save_id">아이디 저장</label>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
	</div>
</div>
<!-- //contents -->
