<%@page import="com.app.util.MybatisClient"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%

if(request.getContentLength() > 10*1024*1024 ){
%>
	<script>alert("업로드 용량(총 10Mytes)을 초과하였습니다.");history.back();</script>
<%
	return;
} else {
	String url = MybatisClient.insertFileInfo(request, "CNTNTS");
	
	if(!"".equals(url)){
		
			%>
			<form id="fileform" name="fileform" method="post">
				<input type="hidden" name="url" value="<%=url%>">
			</form>
			<%
	}else{
		%>
			<script>alert("이미지를 선택해 주세요.");history.back();</script>
		<%
			return;
	}
}
%>

<script type="text/javascript">
	function fileAttach(){ 
		var f = document.fileform;
		var url = f.url.value;
	    
	   
	    try{
             window.opener.parent.pasteHTML(url); 
	    	
	    	 window.close();
	    }catch(e){ 
            alert(e); 
	    }
	}
	fileAttach();
	this.window.close();
</script>
