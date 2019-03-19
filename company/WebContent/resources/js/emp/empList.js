(function(global, doc){
	global.onload = function(){
		
		var context = doc.getElementById('context').value;
		
		//초기화 - 페이지, 페이지네이션 
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {

			if (this.status === 200 && this.readyState === 4) {
				
				var json = JSON.parse(this.responseText);
				drawList(json);
				drawPagination(json);
			
			}
		}
	
		var searchOption = doc.getElementById('searchOption');
		var searchWord = doc.getElementById('searchWord');
		
		xhr.open("GET",context+"/empAjaxList.do",true);
		xhr.send();
		
		var drawList = json =>{
			
			console.log("make list function execute");
			console.log(json);
			var tBody = doc.getElementById("empTable_body");
			
			for(var i=0; i< json.employee.length; i++){
				
				var tr = doc.createElement("tr");
				
				var empno = doc.createElement("td");
				empno.innerHTML = json.employee[i].empno;
				tr.appendChild(empno);
				
				var ename = doc.createElement("td");
				ename.innerHTML = json.employee[i].ename;
				tr.appendChild(ename);
				
				var hiredate = doc.createElement("td");
				hiredate.innerHTML = json.employee[i].hiredate;
				tr.appendChild(hiredate);
				
				var dname = doc.createElement("td");
				dname.innerHTML = json.employee[i].dname;
				tr.appendChild(dname);
				
				tBody.appendChild(tr);
			}
			
		};
		var drawPagination = json =>{
			
			var div = doc.getElementById('paginationSection');
			var h4 = doc.createElement('h4');
			h4.setAttribute("style", "display: inline;" );
			h4.innerHTML = "조회 결과 : "+json.page.count;
			
			var ul = doc.createElement('ul');
			ul.setAttribute("style", "display: inline; list-style: none;" )
			
			div.appendChild(h4);
			div.appendChild(ul);
			
			if(json.page.existPrev){
				var prevBtn = doc.createElement('li');
				prevBtn.setAttribute("style","display:inline; cursor: pointer;");
				prevBtn.setAttribute("class","changePage");
				prevBtn.setAttribute("id",json.page.prevBlock);
				prevBtn.innerHTML = "◀이전";
				ul.appendChild(prevBtn);
			}
			for(var i = json.page.beginPage; i<=json.page.endPage; i++){
				var pageNum = doc.createElement('li');
				pageNum.setAttribute("style","display: inline; cursor: pointer;");
				pageNum.innerHTML = "<a class='changePage' id="+i+" style='margin: 5px 5px;'>"+i+"</a>";
				ul.appendChild(pageNum);
			}
			if(json.page.existNext){
				var nextBtn = doc.createElement('li');
				nextBtn.setAttribute("style",'display: inline; cursor: pointer;');
				nextBtn.setAttribute("class","changePage");
				nextBtn.setAttribute("id",json.page.nextBlock);
				nextBtn.innerHTML = "다음▶";
				ul.appendChild(nextBtn);
			}
		};
		
		//페이지네이션
		var pageNum = document.querySelectorAll('.changePage');
		for(var i=0; i<pageNum.length; i++){
			pageNum[i].addEventListener('click',function(){
				alert("버튼 클릭");
				var pageNo = this.getAttribute('id');
				var searchOption = doc.getElementById('searchOption');
				var searchWord = doc.getElementById('searchWord');
				
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {

					if (this.status === 200 && this.readyState === 4) {
						
						var json = JSON.parse(this.responseText);
						drawList(json);
						drawPagination(json);
					
					}
				}
			
				xhr.open("GET",context+"/empAjaxList.do?pageNum="+pageNo+"&searchWord="+searchWord+"&searchOption="+searchOption,true);
				xhr.send();
			});
		}
		
//		for(var j of doc.querySelectorAll('.changePage')){
//			j.addEventListener('click',function(){
//				
//				alert("버튼 클릭");
//				var pageNo = this.getAttribute('id');
//				var searchOption = doc.getElementById('searchOption');
//				var searchWord = doc.getElementById('searchWord');
//				
//				var xhr = new XMLHttpRequest();
//				xhr.onreadystatechange = function() {
//
//					if (this.status === 200 && this.readyState === 4) {
//						
//						var json = JSON.parse(this.responseText);
//						drawList(json);
//						drawPagination(json);
//					
//					}
//				}
//			
//				xhr.open("GET",context+"/empAjaxList.do?pageNum="+pageNo+"&searchWord="+searchWord+"&searchOption="+searchOption,true);
//				xhr.send();
//				
//			});
//		}
		

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
			
			if( searchForm.searchWord.value === ''){
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




var pagination = ((global, doc)=>{
	return {
		loadUpdateForm : x=>{
		
			
		}
		
	};
})(window, document);

