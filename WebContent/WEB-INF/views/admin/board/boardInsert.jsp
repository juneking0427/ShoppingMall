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
	
	function pmForm(){
		//kbm 코드 삽입
		oEditors.getById["CONTENTS"].exec("UPDATE_CONTENTS_FIELD", []);	
		
		$('#cForm').attr("action","/boardReg.do").submit();
	}
	
	function preMenuList(){
		var jData = {
			depth1 : $('#depth1List').val()
		};

		getAjax("/depth2List.jp", jData, saveOk);
	};
	
	function preMenuList2(){
		var jData = {
			depth2 : $('#depth2List').val()
		};

		getAjax("/depth3List.jp", jData, saveOk2);
	};
	
	function saveOk(data){
		var html = "";
		if(data.depth2List.length > 0 ){
			html += "<select name=\"depth2\" class=\"sort mr30\">";
				for(var i = 0; i < data.depth2List.length ; i ++ ){
					html += "<option value=\"" + data.depth2List[i].CATE_NM + "\">" + data.depth2List[i].CATE_NM + "</option>";
				}
			html += "</select>";
		}
		
		$('#depth2List').html(html);
	}
	
	function saveOk2(data){
		var html = "";
		if(data.depth3List.length > 0 ){
			html += "<select name=\"cate_idx\" class=\"sort mr30\">";
				for(var i = 0; i < data.depth3List.length ; i ++ ){
					html += "<option value=\"" + data.depth3List[i].CATE_IDX + "\">" + data.depth3List[i].CATE_NM + "</option>";
				}
			html += "</select>";
		}
		
		$('#depth3List').html(html);
	}
	
	
	//┌---------------------------------편집기 관련 소스----------------------------------------------------------┐
	function modNoti(elClickedObj) {
		oEditors.getById["CONTENTS"].exec("UPDATE_CONTENTS_FIELD", []);	
		try {
			regNews.action = "/noticeMod.do";
			regNews.submit();
		} catch(e) {}
	}
	//└---------------------------------편집기 관련 소스----------------------------------------------------------┘

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
					<li><a href="adminCateList.do">카테고리</a>
	<!-- 					<ul> -->
	<!-- 						<li><a href="#">근태</a></li> -->
	<!-- 					</ul> -->
					</li>
					<li class="first-child"><a href="adminBoardList.do">게시판 관리</a></li>
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
				<div class="location"></div>
				<!-- //location -->
			</div>
		<!-- //top_content -->
        
        <!-- inner -->
		<div class="inner mt20">
			<!-- tbl -->
			<form name="cForm" id="cForm" method="POST" enctype="multipart/form-data">
				<div class="tbl mt10">
					<table class="upd type3">
						<colgroup>
							<col style="width:13%" />
							<col style="width:*" />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><span class="text_r">*</span> 컨텐츠명</th>
								<td><input type="text" id="TITLE" name="TITLE" class="ip_title" placeholder="컨텐츠 명" style="width:300px;" /></td>
							</tr>
							<tr>
								<th scope="row"><span class="text_r">*</span> 추가 내용</th>
								<td><input type="text" id="EXPLAIN" name="EXPLAIN_OPTION" placeholder="추가 내용" style="width:300px;" /></td>
							</tr>
							<tr>
								<th scope="row"><span class="text_r">*</span> 가격</th>
								<td><input type="text" id="PRICE" name="PRICE" placeholder="숫자만 입력해 주세요" style="width:300px;" /></td>
							</tr>
							<tr>
								<th scope="row"><span class="text_r">*</span> 섬네일 이미지</th>
								<td>
									<input type="file" id="UPLOADFILE" name="uploadfile"/>
									<div id="image_preview" style="display: none;">
										<img src="#" style="max-width: 500px;"/>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row"><span class="text_r">*</span> 카테고리</th>
								<td>
									<select id="depth1List" class="sort premenu">
										<option value="">대분류를 선택해 주세요.</option>
										<c:forEach var="m" items="${depth1List }">
										<option value="${m.CATE_NM }"> ${m.CATE_NM } </option>
										</c:forEach>
									</select>
									
									<select id="depth2List">
										<option value="">대분류를 선택해 주세요.</option>
									</select>
									
									<select name="CATE_IDX" id="depth3List">
										<option value="">중분류를 선택해 주세요.</option>
									</select>
								</td>
							</tr>
							<tr>
								<th scope="row"><span class="text_r">*</span> 내용</th>
								<td>
									<textarea class="textarea" name="CONTENTS" id="CONTENTS" class="demand" placeholder="내용" style="height: 500px;"></textarea>
								</td>
							</tr>
							</tbody>
						</table>
					
				</div>
				<!-- //tbl -->
			
			<!-- btn_area -->
			<div class="btn_area tc mt10">
				<a href="javascript:pmForm();" class="btn btn_b">등록</a>
				<a href="/adminBoardList.do" class="btn btn_bl">취소</a>
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
