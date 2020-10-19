
//	캘린더



	function setRegDay(){
		$(".regSday").datepicker({
			showOn: "both",
			buttonImage: "/resources/images/btn_date.gif",
			buttonImageOnly: true,
			changeMonth: true,
			changeYear: true,
			yearRange: '2014:2024'
		});
		
		$(".regEday").datepicker({
			showOn: "both",
			buttonImage: "/resources/images/btn_date.gif",
			buttonImageOnly: true,
			changeMonth: true,
			changeYear: true,
			yearRange: '2014:2024'
		});
		
		
	}



