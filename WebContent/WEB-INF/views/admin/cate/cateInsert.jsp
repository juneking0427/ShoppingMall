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
		$('#depth1nm').keyup(function(){
			$('#depth_1').val($('#depth1nm').val());
		});

		$('#depth2nm').keyup(function(){
			$('#depth_2').val($('#depth2nm').val());
		});

		$('#depth3nm').keyup(function(){
			$('#depth_3').val($('#depth3nm').val());
		});
		
		$('#depth1List').change(function(){
			preMenuList();
		});
	});
	
	function preMenuList(){
		var jData = {
			depth1 : $('#depth1List').val()
		};

		getAjax("/depth2List.jp", jData, saveOk);
	};
	
	function saveOk(data){
		var html = "";
		if(data.depth2List.length > 0 ){
			html += "<select name=\"depth2\" class=\"sort ml30\">";
				for(var i = 0; i < data.depth2List.length ; i ++ ){
					html += "<option value=\"" + data.depth2List[i].CATE_NM + "\">" + data.depth2List[i].CATE_NM + "</option>";
				}
			html += "</select>";
		}
		
		$('#depth2List').html(html);
	}
	
	function depth1Form(num){
		if(num==1){
			$('#depth1').attr("action","/depth1Cate.jp").submit();	
		}else if(num==2){
			$('#depth2').attr("action","/depth1Cate.jp").submit();	
		}else if(num==3){
			$('#depth3').attr("action","/depth1Cate.jp").submit();	
		}
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
				<h1><a href="#">UCT</a></h1>
				<span class="user_id">ID 명</span>
				<a href="#" class="btn_bl logout">로그아웃</a>
			</div>
		</div>
		<!-- //header_top -->
		
		<div class="inner">
				<!-- gnb -->
				<ul class="gnb clear">
					<li class="first-child"><a href="adminCateList.do">카테고리</a>
	<!-- 					<ul> -->
	<!-- 						<li><a href="#">근태</a></li> -->
	<!-- 					</ul> -->
					</li>
					<li><a href="adminBoardList.do">게시판 관리</a></li>
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
				</div>
				<!-- //location -->
			</div>
		<!-- //top_content -->
        
        <!-- inner -->
		<div class="inner mt20">
			<!-- tbl -->
			<form name="depth1" id="depth1" method="POST">
				<div class="tbl mt10">
					<table class="upd type3">
						<colgroup>
							<col style="width:13%" />
							<col style="width:*" />
						</colgroup>
							<tr>
								<th scope="row">카테고리</th>
								<td >대메뉴</td>
							</tr>
							<tr>
								<th scope="row">대메뉴명</th>
								<td >
									<input type="text" id="depth1nm" name="cate_nm" style="width:300px;"/>
									<input type="hidden" id="depth1rank" name="rank" value="1">
									<input type="hidden" id="dapth1use_yn" name="use_yn" value="Y">
									<input type="hidden" id="depth_1" name="depth1">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="btn_area tc mt10">
					<a href="javascript:depth1Form(1);" class="btn btn_b">등록</a>
				</div>
			</form>
			
			<form name="depth2" id="depth2" method="POST">
				<div class="tbl mt10">
					<table class="upd type3">
						<colgroup>
							<col style="width:13%" />
							<col style="width:*" />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">카테고리</th>
								<td>
									<select name="depth1" class="sort premenu">
										<option value="">대분류를 선택해 주세요.</option>
										<c:forEach var="m" items="${depth1List }">
										<option value="${m.CATE_NM }"> ${m.CATE_NM } </option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th scope="row">중메뉴명</th>
								<td>
									<input type="text" id="depth2nm" name="cate_nm" style="width:300px;"/>
									<input type="hidden" name="rank" value="2">
									<input type="hidden" name="use_yn" value="Y">
									<input type="hidden" id="depth_2" name="depth2">
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
				
				<div class="btn_area tc mt10">
					<a href="javascript:depth1Form(2);" class="btn btn_b">등록</a>
				</div>
			</form>
			
			
			<form name="depth3" id="depth3" method="POST">
				<div class="tbl mt10">
					<table class="upd type3">
						<colgroup>
							<col style="width:13%" />
							<col style="width:*" />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">카테고리</th>
								<td >
									<select name="depth1" id="depth1List" class="sort mr30">
										<option value="">대분류를 선택해 주세요.</option>
										<c:forEach var="m" items="${depth1List }">
										<option value="${m.CATE_NM }">${m.CATE_NM }</option>
										</c:forEach>
									</select>
									
									<select name="depth2" id="depth2List">
										<option value="">대분류를 선택해 주세요.</option>
									</select>
								</td>
							</tr>
							<tr>
								<th scope="row">하메뉴명</th>
								<td>
									<input type="text" id="depth3nm" name="cate_nm" style="width:300px;"/>
									<input type="hidden" name="rank" value="3">
									<input type="hidden" name="use_yn" value="Y">
									<input type="hidden" id="depth_3" name="depth3">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- //tbl -->
				
				<!-- btn_area -->
				<div class="btn_area tc mt10">
					<a href="javascript:depth1Form(3);" class="btn btn_b">등록</a>
				</div>
				<!-- //btn_area -->
			</form>
			
			<a href="adminCateList.do" class="btn btn_bl">목록</a>
		</div>
		<!-- //inner -->
	</div>
	<!-- //content -->
	</div>
	<!-- //wrap -->
</body>
</html>
