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
<script type="text/javascript" src="/resources/se/js/HuskyEZCreator.js" charset="utf-8"></script>

<title>UCT 인사관리</title>

<script type="text/javascript">
	$(document).ready(function(){
		
		//카테고리 대분류 선택시
		$('#depth1List').change(function(){
			preMenuList();
		});
		
		//카테고리 중분류 선택시
		$('#depth2List').change(function(){
			preMenuList2();
		});
		
		$('#UPLOADFILE').on("change",function(){
			ext = $(this).val().split('.').pop().toLowerCase(); //확장자
	
			//배열에 추출한 확장자가 존재하는지 체크
			if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
				resetFormElement($(this)); //폼 초기화
				window.alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
			} else {
				file = $(this).prop("files")[0];
				blobURL = window.URL.createObjectURL(file);
				$('#image_preview').show();
				$('#image_preview img').attr('src', blobURL);
				$('#image_preview').slideDown(); //업로드한 이미지 미리보기 
			}
		});
		
	});
	
	function member_form(){
		$('#cForm').attr("action","/loginReg.do").submit();
	}
	
	function id_check(){
		var jData = {
			depth2 : $('#depth2List').val()
		};

		getAjax("/depth3List.jp", jData, saveOk2);
	};
	
</script>
</head>

<body>

<!-- wrap -->
	<div id="wrap">
		<!-- header_top -->
		<div id="header">
		<div class="header_top">
		</div>
		<!-- //header_top -->
		<div class="inner"></div>
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
							<li class="first-child">회원가입</li>
						</ul>
					</div>
				</div>
				<!-- //location -->
			</div>
		<!-- //top_content -->
        
        <!-- inner -->
		<div class="inner mt20">
			<!-- tbl -->
			<form name="cForm" id="cForm" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="rank" value="1">
				<div class="tbl mt10">
					<table class="upd type3">
						<colgroup>
							<col style="width:13%" />
							<col style="width:*" />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><span class="text_r">*</span> ID</th>
								<td><input type="text" id="TITLE" name="member_id" class="ip_title" placeholder="ID는 영문 및 숫자만 입력해주세요." style="width:300px;" /></td>
							</tr>
							<tr>
								<th scope="row"><span class="text_r">*</span> PassWord</th>
								<td><input type="text" id="EXPLAIN" name="member_pw" placeholder="비밀번호를 입력해주세요." style="width:300px;" /></td>
							</tr>
							<tr>
								<th scope="row"><span class="text_r">*</span> Re-PassWord</th>
								<td><input type="text" id="EXPLAIN" name="member_pw_re" placeholder="비밀번호를 확인" style="width:300px;" /></td>
							</tr>
							<tr>
								<th scope="row"><span class="text_r">*</span> 주소</th>
								<td><input type="text" id="PRICE" name="member_juso" placeholder="직접 입력해주세요." style="width:300px;" /></td>
							</tr>
							<tr>
								<th scope="row"><span class="text_r">*</span> E-Mail</th>
								<td><input type="text" id="PRICE" name="member_mail" placeholder="직접 입력해주세요." style="width:300px;" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- //tbl -->
			
			<!-- btn_area -->
			<div class="btn_area tc mt10">
				<a href="javascript:member_form();" class="btn btn_b">등록</a>
				<a href="/mainForm.do" class="btn btn_bl">취소</a>
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


<script>
//┌---------------------------------편집기 관련 소스----------------------------------------------------------┐
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "CONTENTS",
	sSkinURI: "./../resources/se/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["CONTENTS"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});

function pasteHTML(filePath) {
	console.log(">>>>> filePath : "+filePath);
	var sHTML = '<img src="'+filePath+'">';
<%-- 	var sHTML = '<img src="<%=request.getContextPath()%>/path에서 설정했던 경로/'+filepath+'">'; --%>
// 	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	console.log(">>>>> sHTML : "+sHTML);
	oEditors.getById["CONTENTS"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["CONTENTS"].getIR();
	alert(sHTML);
}
	
function submitContents(elClickedObj) {
	oEditors.getById["CONTENTS"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("CONTENTS").value를 이용해서 처리하면 됩니다.
	console.log(">>>>> "+document.getElementById("CONTENTS").value);
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '궁서';
	var nFontSize = 24;
	oEditors.getById["CONTENTS"].setDefaultFont(sDefaultFont, nFontSize);
}

// } 	
// └---------------------------------편집기 관련 소스----------------------------------------------------------┘
//	=============================================================================================================
</script>

</html>
