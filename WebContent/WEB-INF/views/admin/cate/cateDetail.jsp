<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link type="text/css" rel="stylesheet" href="/resources/css/pm.css" />

<script type="text/javascript" src="/resources/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript" src="/resources/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="/resources/js/myAjax.js"></script>
<script type="text/javascript" src="/resources/js/pageCommon.js"></script>

<title>UCT 인사관리</title>

<script type="text/javascript">
	$(function(){
		$('#rday').datepicker();
	});
	

	function pmForm(){
		if($('#phonenum').val() == ''){
			alert('휴대폰 번호를 입력해 주세요.');
			return;
		}else if(!$('#phonenum').val().match(/^[0-9]+$/)){
			alert('휴대폰 번호는 숫자만 입력 가능합니다.');
			$('#phonenum').val('');
			$('#phonenum').focus();
			return;
		}else if($('#phonenum').val().length != 11){
			alert('휴대폰 자리수를 확인해 주세요.');
			return;
		}else if($('#age').val() == ''){
			alert('나이를 입력해 주세요.');
			return;
		}else if(!$('#age').val().match(/^[0-9]+$/)){
			alert('나이는 숫자만 입력 가능합니다.');
			$('#age').val('');
			$('#age').focus();
			return;
		}else if($('#emergencynum').val() == ''){
			alert('비상연락처를 입력해 주세요.');
			return;
		}else if(!$('#emergencynum').val().match(/^[0-9]+$/)){
			alert('비상연락처는 숫자만 입력 가능합니다.');
			$('#emergencynum').val('');
			$('#emergencynum').focus();
			return;
		}else if($('#position').val() == ''){
			alert('직위를 입력해 주세요.');
			return;
		}else if($('#postnum').val() == ''){
			alert('우편번호를 입력해 주세요.');
			return;
		}else if(!$('#postnum').val().match(/^[0-9]+$/)){
			alert('우편번호는 숫자만 입력 가능합니다.');
			return;
		}else if($('#email').val() == ''){
			alert('E-Mail을 입력해 주세요.');
			return;
		}else if(!$('#email').val().match(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i)){
			alert('메일의 형식이 올바르지 않습니다.');
			return;
		}else if($('#address1').val() == ''){
			alert('주소를 입력해 주세요');
			return;
		}else if($('#address2').val() == ''){
			alert('상세 주소를 입력해 주세요.');
			return;
		}else if($('#family').val() == ''){
			alert('가족사항을 입력해 주세요.');
			return;
		}
		
		$('#pmregForm').attr("action","/pjt/uct/pmUpdate.do").submit();
	}
</script>
</head>
<body>

<!-- wrap -->
	<div id="wrap">
		<!-- header_top -->
		<div id="header">
		<div class="header_top">
			<div class="inner clear">
				<h1><a href="#">UCT</h1>
				<span class="user_id">ID 명</span>
				<a href="#" class="btn_bl logout">로그아웃</a>
			</div>
		</div>
		<!-- //header_top -->
		
		<div class="inner">
			<!-- gnb -->
			<ul class="gnb clear">
				<li class="first-child"><a href="pmList.do">인사관리</a>
					<ul>
						<li><a href="#">근태</a></li>
					</ul>
				</li>
				<li><a href="#">휴가</a></li>
				<li><a href="#">프로젝트</a></li>
				<li><a href="#">공지사항</a></li>
				<li><a href="#"> QnA </a></li>
				<li><a href="#"> 자료실</a></li>
			</ul>
			<!-- //gnb -->
         </div>
	     </div>
        
        <!-- Body_contents -->
        <!-- content -->
		<div id="content">
			<!-- top_content -->
			<div class="top_content">
				<!-- location -->
				<div class="location">
					<div class="inner">
						<ul class="clear">
							<li class="first-child"><a href="#">Home</a></li>
							<li><a href="#">UCT 인사 관리</a></li>
							<li><a href="#" class="current">UCT 인사 등록</a></li>
						</ul>
					</div>
				</div>
				<!-- //location -->
			</div>
		<!-- //top_content -->
        
        <!-- inner -->
		<div class="inner mt20">
		<!-- tbl -->
		<form name="pmregForm" id="pmregForm" method="POST">
			<input type="hidden" id="empno"  name="empno" value="${detailPm.EMPNO }">
			<div class="tbl mt10">
				<table class="upd type3">
					<colgroup>
						<col style="width:13%" />
						<col style="width:*" />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">이름</th>
							<td>${detailPm.EMPNM }</td>
						</tr>
						<tr>
							<th scope="row">핸드폰</th>
							<td><input type="text" id="phonenum" name="phonenum" value="${detailPm.PHONENUM}" maxlength="11" style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">비상연락처</th>
							<td><input type="text" id="emergencynum" name="emergencynum" value="${detailPm.EMERGENCYNUM}" maxlength="11" style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">나이</th>
							<td><input type="text" id="age" name="age" maxlength="2" value="${detailPm.AGE}" style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">직위</th>
							<td><input type="text" id="position" name="position" value="${detailPm.POSITION}" style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">주민번호</th>
							<td>${detailPm.IDENTITYNUM}</td>
						</tr>
						<tr>
							<th scope="row">생일</th>
							<td>${detailPm.BIRTHDAY}</td>
						</tr>
						<tr>
							<th scope="row">부서</th>
							<td><input type="text" id="deptnm" name="deptnm" value="${detailPm.DEPTNM}" style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">이메일</th>
							<td><input type="text" id="email" name="email" value="${detailPm.EMAIL}" style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">우편번호</th>
							<td><input type="text" id="postnum" name="postnum" value="${detailPm.POSTNUM}" maxlength="6"  style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">주소</th>
							<td><input type="text" id="address1" name="address1" value="${detailPm.ADDRESS1}" style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">상세주소</th>
							<td><input type="text" id="address2" name="address2" value="${detailPm.ADDRESS2}" style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">최종학력</th>
							<td>${detailPm.LVEDU}</td>
						</tr>
						<tr>
							<th scope="row">가족사항</th>
							<td><input type="text" id="family" name="family" value="${detailPm.FAMILY}" style="width:300px;" /></td>
						</tr>
						<tr>
							<th scope="row">입사일</th>
							<td>${detailPm.FDAY}</td>
						</tr>
						<tr>
							<th scope="row">퇴사일</th>
							<td>
								<input type="text" name="rday" id="rday" value="${detailPm.RDAY }">
							</td>
						</tr>
						</tbody>
					</table>
			</div>
			<!-- //tbl -->
			<!-- btn_area -->
			<div class="btn_area tc mt10">
				<a href="javascript:pmForm();" class="btn btn_b">수정</a>
				<a href="uct/pmList.do" class="btn btn_bl">목록</a>
			</div>
			<!-- //btn_area -->
			</form>
		</div>
		<!-- //inner -->
	</div>
	<!-- //content -->
	</div>
	<!-- //wrap -->
</body>
</html>
