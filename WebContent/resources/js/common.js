$(function() {
	
	//메인메뉴 마우스 오버
	$(".main_nav > li").hover(function() {
			if(!$(this).hasClass("on")) {
				$(".main_nav > li.on ul").hide();
			}else {
				$(".main_nav > li.on ul").show();
			}
		}, function() {
			$(".main_nav > li.on ul").show();
	});
	
	//캘린더
//	$(".ip_datepicker").datepicker({
//		showOn: "both",
//		buttonImage: "/resources/images/btn_date.gif",
//		buttonImageOnly: true,
//		changeMonth: true,
//		changeYear: true,
//		yearRange: '2014:2024'
//	});


	$(document).on("click",".btn_bottom_add",function(){
		var bottom_con = '<div class="bottom group"><div class="tit"><strong>소제목</strong><input type="text" name="titleC[]" class="ip_txt titleC"   style="width:270px;" maxlength="100"/><input type="hidden" name="groupCA[]" class="groupA"/><input type="hidden" name="groupCB[]" class="groupB"/></div><div class="txt_box"><strong>내용</strong><textarea cols="30" rows="10" style="width:256px" name="contentsC[]" maxlength="500"class="contentsC"></textarea></div><div class="btns"><a href="#" class="btn_add btn_inner btn_bottom_add"><span>추가</span></a><a href="#" class="btn_del btn_inner btn_bottom_del"><span>삭제</span></a></div></div>';
		$(this).parents(".bottom").after(bottom_con);
		fn_order();
		return false;
	});
	$(document).on("click",".btn_bottom_del",function(){
		if($(this).parents(".mid").find(".btn_bottom_del").size() > 1){
			$(this).parents(".bottom").remove();
			fn_order();
			return false;
		}else{
			alert("최소 한개 이상")
				return false;
		}
	});

	$(document).on("click",".btn_mid_add",function(){
		var mid_con ='<div class="mid"><strong>중제목</strong><input type="text" name="titleB[]" class="ip_txt titleB"  style="width:300px;" maxlength="100"><input type="hidden" name="groupBA[]" value="1" class="groupA"/><div class="btns"><a href="#" class="btn_add btn_inner btn_mid_add"><span>추가</span></a><a href="#" class="btn_del btn_inner btn_mid_del"><span>삭제</span></a></div>';
		mid_con += '<div class="bottom group"><div class="tit"><strong>소제목</strong><input type="text" name="titleC[]" class="ip_txt titleC"   style="width:270px;" maxlength="100"><input type="hidden" name="groupCA[]" class="groupA"/><input type="hidden" name="groupCB[]" class="groupB"/></div><div class="txt_box"><strong>내용</strong><textarea cols="30" rows="10" style="width:256px" name="contentsC[]" maxlength="500" class="contentsC"></textarea></div><div class="btns"><a href="#" class="btn_add btn_inner btn_bottom_add"><span>추가</span></a><a href="#" class="btn_del btn_inner btn_bottom_del"><span>삭제</span></a></div></div></div>'
		$(this).parents(".mid").after(mid_con);
		fn_order();
		return false;
	});

	$(document).on("click",".btn_mid_del",function(){
		if($(this).parents(".card_info").find(".btn_mid_del").size() > 1){
			$(this).parents(".mid").remove();
			fn_order();
			return false;
		}else{
			alert("최소 한개 이상")
				return false;
		}
	});
	$(document).on("click",".btn_top_add",function(){
		var top_con = '<div class="card_info"><div class="top"><strong>대제목</strong><input type="text" name="titleA[]" class="ip_txt titleA" style="width:314px;" maxlength="100"><div class="btns"><a href="#" class="btn_add btn_inner btn_top_add"><span>추가</span></a><a href="#" class="btn_del btn_inner btn_top_del"><span>삭제</span></a></div></div>';
			top_con += '<div class="mid"><strong>중제목</strong><input type="text" name="titleB[]" class="ip_txt titleB" style="width:300px;" maxlength="100"><input type="hidden" name="groupBA[]" class="groupA"/><div class="btns"><a href="#" class="btn_add btn_inner btn_mid_add"><span>추가</span></a><a href="#" class="btn_del btn_inner btn_mid_del"><span>삭제</span></a></div>';
			top_con += '<div class="bottom group"><div class="tit"><strong>소제목</strong><input type="text" name="titleC[]" class="ip_txt titleC" style="width:270px;" maxlength="100"><input type="hidden" name="groupCA[]" class="groupA"/><input type="hidden" name="groupCB[]" class="groupB"/></div><div class="txt_box"><strong>내용</strong><textarea cols="30" rows="10" name="contentsC[]" style="width:256px" maxlength="500" class="contentsC"></textarea></div><div class="btns"><a href="#" class="btn_add btn_inner btn_bottom_add"><span>추가</span></a><a href="#" class="btn_del btn_inner btn_bottom_del"><span>삭제</span></a></div></div></div></div>'
		$(this).parents(".card_info").after(top_con);
		fn_order();
		return false;
	});
	
	$(document).on("click",".btn_top_del",function(){
		if($(".btn_top_del").size() > 1){
			$(this).parents(".card_info").remove();
			fn_order();
			return false;
		}else{
			alert("최소 한개 이상")
				return false;
		}
	});

	$(document).on("click",".btn_td_del",function(){
		if($(".btn_td_del").size() > 0){
			$(this).parent().parent().remove();
			fn_order();
			return false;
		}
	});
	
	
//	미리보기 드래그
	var max_wd = $(document).width();
	var max_ht = $(document).height(); 
	
	$(this).find(".preview_head").mousedown(function(e){
		var pop_wd = $(this).parent(".preview").width();
		var pop_ht = $(this).parent(".preview").height();

		$(this).data("clickPointX" , e.pageX - $(this).offset().left)
		$(this).data("clickPointY" , e.pageY - $(this).offset().top);

		$(this).mousemove(function(e){
			var pop_top = e.pageY  - $(this).data("clickPointY");
			var pop_left = e.pageX  - $(this).data("clickPointX");
			if(pop_top <= 0){
				pop_top = 0;
			}else if(pop_top >= (max_ht-pop_ht)){
				pop_top = (max_ht-pop_ht)-2;
			}else if(pop_left <= 0){
				pop_left = 0;
			}else if(pop_left >= (max_wd-pop_wd)){
				pop_left = (max_wd-pop_wd)-2;
			};
			$(".preview").css({zIndex:1000});
			$(this).parent(".preview").css({
				top:pop_top,
				left:pop_left,
				zIndex:10000
			});
		}).mouseup(function(){
			$(".preview").css({zIndex:1000});
			$(this).parent(".preview").css({zIndex:10000});
			$(this).unbind("mousemove");
		});
	});
});

function fn_order(){
	$('.top').each(function(index,data){
		$(this).parent().find('.groupA').val(index+1);
		$(this).parent().find('.mid').each(function(index,data){
			$(this).find('.groupB').val(index+1);
		});
	});
}

//엔터시에 필드값에 br 태그를 추가
function hitEnterKey(field,event){
	  if(event.keyCode == 13){
		  field.value = field.value+"<br>"; 
	  }else{
		  event.keyCode == 0;
	  return;
	  }
	 } 
		
function escape(str)
{
	if( str == null || str.length == 0 ) return "";
	var result = "";
	var reserved = ";/?:@&=+$,";
	var ch = "";
	for(var i=0; i<str.length; i++)
	{
		ch = str.charAt(i);
		if( reserved.indexOf(ch) != -1 ){
			result += "%"+ ch.charCodeAt(0).toString(16);
		}
		else if( ch == " " ){
			result += "+";
		}
		else{
			result += ch;
		}
	}
	return result;
}

//Replace 
String.prototype.simpleReplace = function(oldStr, newStr) {
	var rStr = oldStr;

	rStr = rStr.replace(/\\/g, "\\\\");
	rStr = rStr.replace(/\^/g, "\\^");
	rStr = rStr.replace(/\$/g, "\\$");
	rStr = rStr.replace(/\*/g, "\\*");
	rStr = rStr.replace(/\+/g, "\\+");
	rStr = rStr.replace(/\?/g, "\\?");
	rStr = rStr.replace(/\./g, "\\.");
	rStr = rStr.replace(/\(/g, "\\(");
	rStr = rStr.replace(/\)/g, "\\)");
	rStr = rStr.replace(/\|/g, "\\|");
	rStr = rStr.replace(/\,/g, "\\,");
	rStr = rStr.replace(/\{/g, "\\{");
	rStr = rStr.replace(/\}/g, "\\}");
	rStr = rStr.replace(/\[/g, "\\[");
	rStr = rStr.replace(/\]/g, "\\]");
	rStr = rStr.replace(/\-/g, "\\-");

	var re = new RegExp(rStr, "g");
	return this.replace(re, newStr);
};

//trim 
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
//trimAll
String.prototype.trimAll = function() {
	return this.replace(/\s*/g, "");
};
//cut
String.prototype.cut = function(start, length) {
	return this.substring(0, start) + this.substr(start + length);
};

//insert
String.prototype.insert = function(index, str) {
	return this.substring(0, index) + str + this.substr(index);
};

/*
 * @functoin : leng
 * @description : 텍스트의 byte수를 리턴한다.
 * @return : int
 */
String.prototype.leng = function() {
	var koreanByte = 2;//-- 한글은 2byte로 인식
	//var koreanBype = 3; //--oracle charset이 utf-8일 경우 한글은 3bype로 인식한다.
	var cntLeng = 0;
	for(var leni=0; leni<this.length; leni++)
	{
		var c = this.charCodeAt(leni);
		if( (0xAC00 <= c && c <= 0xD7A2) || (0x3131 <= c && c <= 0x318E) ) cntLeng += koreanByte;
		else cntLeng += 1;
	}
	return cntLeng;
};

/*
 * @functoin : isMaskCheck
 * @description : 정규식을 이용하여 데이터 형식을 check
 * @return : boolean
 */
String.prototype.isMaskCheck = function(mask){
	return (mask).test(this.trim());
};


//숫자만 입력 하는 함수
function onlyNumber(obj) {
	var str = obj.value;
	var len = str.length;
	var ch = str.charAt(0);
	for(var i = 0; i < len; i++) {
		ch = str.charAt(i);
		if( (ch >= '0' && ch <= '9')) {
		continue;
		} else {
			alert("숫자만 입력 해주세요.");
			obj.value="";
			obj.focus();
			return false;
			} ;
		};
		return true;
};

///input에 숫자및 영문 입력 하게하는 함수
function onlyNumber2(obj) {
	var str = obj.value;
	var len = str.length;
	var ch = str.charAt(0);
	for(var i = 0; i < len; i++) {
		ch = str.charAt(i);
		if( (ch >= '0' && ch <= '9')||(ch >= 'a' && ch <= 'z')||(ch >= 'A' && ch <= 'Z')) {
		continue;
		} else {
			alert("숫자와 영문만 입력 해주세요.");
			obj.value="";
			obj.focus();
			return false;
			} ;
		};
		return true;
};



//조회기간 일 수
function dateCheckDay(sDate,eDate){
	try {
		if(eDate == null){
			eDate = new Date();
		}
		var oneDay =(1000*60*60*24);
		return Math.ceil(eDate-sDate/oneDay);
	} catch (e) {
		return 0;
	}
};


//조회 기간 설정 함수
function dateSearchCheck(sday,eday){
	var ch = true;
	if(sday != "" && eday != ""){

		var mySdayArray = sday.split("-");
		var myEdayArray = eday.split("-");
		var tempSday = new Date(mySdayArray[0],mySdayArray[1]-1,mySdayArray[2]);
		var tempEday = new Date(myEdayArray[0],myEdayArray[1]-1,myEdayArray[2]);
		var tempday = new Date(Date.parse(tempEday) - 179*1000*60*60*24);

		if(tempSday > tempEday){
			alert("시작일이 종료일보다 작아야됩니다.");
			ch = false;
		}
		if(tempday > tempSday){
			alert("조회 기간은 180일 이하 입니다.\n다시 선택 해주세요.");
			ch = false;
		}

	} else if(sday == "" && eday != ""){
		alert("시작일을 입력해 주세요.");
		ch = false;
	} else if(sday != "" && eday == ""){
		alert("종료일을 입력해 주세요.");
		ch = false;
	}
	return ch;
};


//스크립트 태그 잘라버리는 함수
function scriptDel (objVal){
	var delTag =/<script.*?>|<\/script>/gi;
	if(objVal != null){
	objVal = objVal.split(delTag).join("");
	objVal = objVal.split(delTag).join("");
	}
	return objVal;
	};


//문자열형식 변환 20140313 --> 2013-03-13
function strParseDate (objVal){

	if(objVal.length == 8){
		objVal = objVal.substring(0,4)+"-"+objVal.substring(4,6)+"-"+objVal.substring(6,8);
	}
	
	return objVal;
};

function strParseDate (sday,eday){
	var objVal =sday+"~"+eday;
	if(sday.length == 8 && eday.length == 8){
		sday = sday.substring(0,4)+"-"+sday.substring(4,6)+"-"+sday.substring(6,8);
		eday = eday.substring(0,4)+"-"+eday.substring(4,6)+"-"+eday.substring(6,8);
		objVal =sday+"~"+eday;
	}

	return objVal;
};


function setContents() {
	var contentsText = document.body.innerHTML;
	initBody = contentsText;
	// 본문에서 'print start' 이 문자열(주석 문자 포함) 부터 프린트 시작 
	var PrnStart = contentsText.toLowerCase().indexOf(
			"<!-- print start -->");
	// 본문에서 'print end' 이 문자열(주석 문자 포함) 부터 프린트 시작
	var PrnEnd = contentsText.toLowerCase().indexOf("<!-- print end -->");
	var PrnContent = contentsText.substring(PrnStart, PrnEnd);

	ContentsText = PrnContent;

	if (ContentsText != "") {
		document.body.innerHTML = ContentsText;
		window.print();
		document.body.innerHTML = initBody;
	} else {
		alert("출력할 내용을 찾지 못하였습니다.");
	}
	//self.close();
}



/*
 * 사용 방법 .. container를 무조건 읽어서 처리 하도록 되어 있음. 
 * 아래 excel에서 url을 넣어야 정확하게 처리가 되서 이미지 등등 url 처리에 대해서는 확인이 필요함
 * 
<li class="last m5"  ><a href="#"  onclick="goPrint('디자인접수');"  >인쇄</a></li>
<li class="last m5"  ><a href="#"  onclick="saveExcel('c:\\kim.xls');"  >Excel</a></li>
*/
function goPrint(title){
    var sw=screen.width;
    var sh=screen.height;
    var w=800;//팝업창 가로길이
    var h=600;//세로길이
    var xpos=(sw-w)/2; //화면에 띄울 위치
    var ypos=(sh-h)/2; //중앙에 띄웁니다.

    var pHeader="<html><head><meta http-equiv='Pragma' content='no-cache'><link rel='stylesheet' type='text/css' href='/resources/css/style.css'>"+
    "<link rel='stylesheet' type='text/css' href='/resources/css/lcds.css'>"+
    "<title>" + title + "</title></head><body>";
  
//    $(".border_box").hide();
//    $(".detail_view").hide();
    var pgetContent=document.getElementById("right").innerHTML + "<br>";
 
    var pFooter="</body></html>";
    
//    $(".border_box").show();
//    $(".detail_view").show();
    
    pContent=pHeader + pgetContent + pFooter;  
     
    pWin=window.open("","print","width=" + w +",height="+ h +",top=" + ypos + ",left="+ xpos +",status=yes,scrollbars=yes"); //동적인 새창을 띄웁니다.
    pWin.document.open(); //팝업창 오픈
    pWin.document.write(pContent); //새롭게 만든 html소스를 씁니다.
    pWin.document.close(); //클로즈
    pWin.print(); //윈도우 인쇄 창 띄우고
    pWin.close(); //인쇄가 되던가 취소가 되면 팝업창을 닫습니다.
   }	



function saveExcel(title,e){

    var pHeader="<html><head><meta http-equiv='Pragma' content='no-cache'>\r\n"+
    "<meta http-equiv=\"Content-Type\" content=\"application/vnd.ms-excel;charset=utf-8\">"+
    "<link rel='stylesheet' type='text/css' href='/resources/css/sam.css'>"+
    "<title>"+title+"</title></head><body>";
    
    var pgetContent=document.getElementById("excelDown").innerHTML + "<br>";
    //innerHTML을 이용하여 Div로 묶어준 부분을 가져옵니다.
    var pFooter="</body></html>";
    pContent=pHeader + pgetContent + pFooter;  
//     console.log(pContent);
    window.open('data:application/vnd.ms-excel,' + pContent  );
    e.preventDefault();
  }	


function leadZeros(n, digits) {
	var zero = '';
	n = n.toString();
	
	if (n.length < digits) {
		for(var i = 0; i < digits - n.length; i++) {
			zero += '0';
		}
	}
	return zero + n;
}



