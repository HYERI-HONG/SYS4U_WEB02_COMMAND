(function(global, doc){
	global.onload = function(){
		
		var context = doc.getElementById('context').value;
		
		var getParameter = function (name){
			   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(global.location.search)) {
				   return decodeURIComponent(name[1]);
			   }
			   
			   return "";
		};
		
		var moveToAddPage = function(){
			location.href = context+"/move.do?pageName=empAdd";
		};
		var SubmintSearchForm = function(){
			
			var searchForm = doc.searchForm;
			
			if(searchForm.searchOption.value !== 'all' && searchForm.searchWord.value === ''){
				alert("검색어를 입력하세요");
			}else{
				
				searchForm.action = context+"/empList.do";
				searchForm.method = "post";
				searchForm.submit();
			}
		};
		
		doc.getElementById("addBtn").onclick = moveToAddPage;
		doc.getElementById("searchButton").onclick = SubmintSearchForm;
	};
}(window,document));