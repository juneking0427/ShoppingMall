<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title> new document </title>
  <meta name="generator" content="editplus" />
  <meta name="author" content="" />
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  
 
	<link type="text/css" rel="stylesheet" href="/resources/css/main.css" />
<!-- 	<link type="text/css" rel="stylesheet" href="/resources/css/hancom.css" /> -->
<!-- 	<link type="text/css" rel="stylesheet" href="./../resources/css/hancom.css" /> -->

	<script type="text/javascript">
// 		function fn_productList(no){
// 			alert("cate_idx" + no);
// 			$('#cate_idx').val(no);
// 			$('#cForm').attr("action","/mainList.do").submit()
// 		}
	</script>
 </head>

	<body>
		<form name="cForm" method="post">
			<input type="hidden" id="cate_idx" name="cate_idx">
		</form>
	
	
		<div id="top_bar">
	    	<h1><a href="#"><img src="/resources/img/hancom/hc.png" width="150px"/></a></h1>
			<div class="search"><input type="text" id="search_box" placeholder="검색어 입력" maxlength="40" /><button id="search_btn" type="button" onclick="searchr();">검색</button></div>
	      	<div id="login"><a href="/login_user.do">로그인</a> &nbsp;<a href="/loginRegF.do">회원가입</a> &nbsp;마이페이지 &nbsp;상담센터 &nbsp;장바구니</div>
	 	</div>
	    <div id="left_bar">
	    <div id="ct"><img src="/resources/img/hancom/category2.png" /></div>
	    <!-- MENU START -->
	    <!-- DELPTH1 -->
		<ul>
			<c:forEach var="i" items="${depth1List }"> 
			<li><a href="#">${i.DEPTH1 }</a>
				<!-- DEPTH2 -->
				<ul>
				<c:forEach var="j" items="${depth2List }">
					<c:if test="${i.DEPTH1 eq j.DEPTH1}">
						<li><a href="#">${j.DEPTH2 }</a></li>
						<!-- DEPTH3 -->
						<ul>
						<c:forEach var="l" items="${depth3List }">
							<c:if test="${j.DEPTH2 eq l.DEPTH2}">
								<li><a href="javascript:fn_productList('${l.CATE_IDX }')">${l.DEPTH3 }</a></li>
							</c:if>
						</c:forEach>
						</ul>
					</c:if>
				</c:forEach>			
				</ul>
			</c:forEach>
		</ul>
		<!-- MENU END -->
		
	    <div class="sub">
	    	<div class="sub2" align="center"><a href="#"><img src="/resources/img/hancom/left_pop2.png" width="115px" /></a></div>
	        <div class="sub3" align="center"><a href="#"><img src="/resources/img/hancom/left_pop3.png" width="115px" /></a></div>
	    </div>
	</div>					<!-- REFT MENU -->
						
						<!-- BOTTOM -->
	<div id="wrap">
		<div>
	    	<!-- <img src="/resources/img/hancom/pop.png" /> -->
	        <table>
<%-- 	        		<colgroup> --%>
<%-- 							<col style="width:17%" /> --%>
<%-- 							<col style="width:*" /> --%>
<%-- 							<col style="width:25%" /> --%>
<%-- 					</colgroup> --%>
	            <thead>
	           		<tr><p> ${boardDetail.DEPTH1 } < ${boardDetail.DEPTH2 } < ${boardDetail.DEPTH3 } </p></tr>
	            </thead>
	        	<tbody>
	                <tr>
	                    <td align="center" ><a href="#"><img src="/resources/img/hancom/tv1.png"/></a></td>
	                    <td width="650px"><b><a href="#">[삼성전자] 삼성모니터 LS24D300HL [무결점]</u></b><br /><br />
	                    24형(50.8CM) LED LCD(와이드) / TN패널 / 1920*1080 / 1,000:1(typ) / 5ms / 300cd / D-Sub / HDMI / 매직 업 스케일</a>
	                    </td>
	                    <td align="center" >판매가격<br /><br /><b>154,000원</b>
	                    </td>
	            	</tr>
	            </tbody>
	        </table>
	    </div>
	</div>				<!-- RIGHT MENU -->    
	    <div id="right_bar">
		<div class="shop"><img src="/resources/img/hancom/shopping_cart.png" /></div>
	    
	    <!-- tbl -->
				<div class="tbl mt10">
					<!-- list -->
					<table class="list">
						<colgroup>
							<col style="width:10" />
							<col style="width:*" />
							<col style="width:18%" />
	                        <col style="width:18%" />
						</colgroup>
						<thead>	
							<tr>
								<th colspan="2" scope="col">상품명/옵션</th>
								<th scope="col">주문금액</th>
							</tr>
						</thead>
						<tbody>
							<tr>
	                        		<td align="center"><a href="#"><img src="/resources/img/hancom/tv1.png" width="60px"/></a></td>
									<td align="left"><b><a href="#">[삼성전자] 삼성모니터 LS24D300HL</b><br /><br />
	                                <font size="-2">24형(50.8CM) LED LCD(와이드) / TN패널 / 1920*1080 / 1,000:1(typ) / 5ms / 300cd / D-Sub / HDMI / 매직 업 스케일</font></a></td>
									<td align="center">수량 : <input type="text" value="1" style="text-align:center; width:20px; heigfht:16px; border:#d1d1d1 1px solid;"/><br /><br /><b>154,000원</b></td>
							</tr>
						</tbody>
					</table>
					<!-- //list -->
				</div>
				<!-- //tbl -->
	            
	    <!-- btn_area -->
	    
	    <div class="order">
	    	<center><a href=""><img src="/resources/img/hancom/order_print.png" width="150px"/></a></center>
		</div>
	</div>
	
	<div id="bottom_bar">
		<p><center>copyright 2008 서울특별시 은평구 통일로92길 39-20 (불광동)</center></p>
	</div>
	
	</body>
</html>
