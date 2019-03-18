var pagination = ((global,doc)=>{
	 
	return {changePage : x=>{
		var pageNum = document.querySelectorAll('.changePage');
		for(var i=0; i<pageNum.length; i++){
			pageNum[i].addEventListener('click',function(){
				var pageNo = this.getAttribute('id');
				location.href = x.contextPath+"/"+x.domain+"List.do?pageNum="+pageNo+"&searchWord="+x.searchWord+"&searchOption="+x.searchOption;
			});
		}
	}};
	
})(window,document);