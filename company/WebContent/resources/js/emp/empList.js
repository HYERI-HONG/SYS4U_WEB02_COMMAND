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
			 if(this.status === 500 && this.readyState === this.DONE){
		           alert(JSON.parse(this.responseText).errorText);
		     }
		}
		
		xhr.open("GET",context+"/empList.do", true);
		xhr.setRequestHeader("X-XMLHttpRequest", "true");  
		xhr.send();
		
		
		//리스트 테이블 출력
		var drawList = json =>{
			
			console.log("make list function execute");
			console.log(json);
			var tBody = doc.getElementById("empTable_body");
			
			while (tBody.hasChildNodes()) {   
				tBody.innerHTML = '';
			}
			
			for(var i=0; i< json.employee.length; i++){
				
				var tr = doc.createElement("tr");
				
				var empno = doc.createElement("td");
				empno.innerHTML = json.employee[i].empno;
				empno.setAttribute("id",json.employee[i].empno);
				empno.onclick = function(){	
					moveToDetail(this.getAttribute('id'));
				};
				tr.appendChild(empno);
				
				var ename = doc.createElement("td");
				ename.innerHTML = json.employee[i].ename;
				tr.appendChild(ename);
				
				var hiredate = doc.createElement("td");	
				var date = new Date(json.employee[i].hiredate);
	            var date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
				hiredate.innerHTML = date;
				tr.appendChild(hiredate);
				
				var dname = doc.createElement("td");
				dname.innerHTML = json.employee[i].dname;
				tr.appendChild(dname);
				
				tBody.appendChild(tr);
			}
			
		};
		
		//페이지 네이션 출력
		var drawPagination = json =>{
			
			var div = doc.getElementById('paginationSection');
			
			while (div.hasChildNodes()) {   
				  div.innerHTML = '';
			}
			
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
				prevBtn.setAttribute("id",json.page.prevBlock);
				prevBtn.innerHTML = "◀이전";
				prevBtn.onclick = function(){	
					sendData(this.getAttribute('id'));
				};
				ul.appendChild(prevBtn);
			}
			for(var i = json.page.beginPage; i<=json.page.endPage; i++){
				var pageNum = doc.createElement('li');
				pageNum.setAttribute("style","display: inline; cursor: pointer; margin: 5px 5px;");
				pageNum.setAttribute("id",i);
				pageNum.innerHTML = i;
				pageNum.onclick = function(){	
					sendData(this.getAttribute('id'));
				};
				ul.appendChild(pageNum);
			}
			if(json.page.existNext){
				var nextBtn = doc.createElement('li');
				nextBtn.setAttribute("style",'display: inline; cursor: pointer;');
				nextBtn.setAttribute("id",json.page.nextBlock);
				nextBtn.innerHTML = "다음▶";
				nextBtn.onclick = function(){	
					sendData(this.getAttribute('id'));
				};
				ul.appendChild(nextBtn);
			}
		};
		
		//사원 등록 버튼 클릭시 등록 페이지 출력
		var drawAddForm = function(){
			
			var addSection = doc.getElementById('addSection');
			var addDiv = doc.createElement('div');
			addDiv.setAttribute("style"," border: 1px solid gray; padding: 30px 20px; margin-top: 30px; width: 40%;");

			var addtable = doc.createElement('table');
			var tr1 = doc.createElement('tr');
			tr1.innerHTML = '<td>사원 번호</td>'
							+'<td><input type="text" id="empno"/></td>'
							+'<td>이 름</td>'
							+'<td id = "enametd"><input type="text" id="ename"/></td>';

			var tr2 = doc.createElement('tr');
			tr2.innerHTML = '<td>직속 상관</td>'
							+'<td><input type="text" id="mgr"/></td>'
							+'<td>입사 날짜</td>'
							+'<td><input type="date" id="hiredate" style="width: 160px;"/></td>';

			var tr3 = doc.createElement('tr');
			tr3.innerHTML = '<td>연 봉</td>'
							+'<td><input type="text" id="sal"/></td>'
							+'<td>상 여 금</td>'
							+'<td><input type="text" id="comm"/></td>'

			var tr4 = doc.createElement('tr');
			tr4.innerHTML = ' <td>부서</td>'
			               	+'<td><select id="deptno">'
			                     +'<option class="deptno-opt" value="10">Accounting</option>'
			                     +'<option class="deptno-opt" value="20">Research</option>'
			                     +'<option class="deptno-opt" value="30">Sales</option>'
			                     +'<option class="deptno-opt" value="40">Operations</option>'
			               		 +'</select>'
			               +'<td>직책</td>'
			               +'<td><select id="job">'
			                     +'<option class="job-opt" value="CLERK">Clerk</option>'
			                     +'<option class="job-opt" value="SALESMAN">Salesman</option>'
			                     +'<option class="job-opt" value="MANAGER">manager</option>'
			                     +'<option class="job-opt" value="ANALYST">Analyst</option>'
			                     +'<option class="job-opt" value="PRESIDENT">President</option>'
			              		 +'</select></td>'

			addtable.appendChild(tr1);
			addtable.appendChild(tr2);
			addtable.appendChild(tr3);
			addtable.appendChild(tr4);

			//등록 확인 버튼 클릭시 
			var okBtn = doc.createElement('input');
			okBtn.setAttribute('id','okBtn');
			okBtn.setAttribute('type','button');
			okBtn.setAttribute('value','확인');
			okBtn.setAttribute('style',' margin: 20px 5px 5px 450px;');

			okBtn.onclick = function(){
				
				var employee = { 'empno'    : doc.getElementById('empno').value,
								 'ename'    : doc.getElementById('ename').value,
								 'mgr'      : doc.getElementById('mgr').value,
								 'hiredate' : doc.getElementById('hiredate').value,
								 'sal'      : doc.getElementById('sal').value,
								 'comm'     : doc.getElementById('comm').value,
								 'deptno'   : doc.getElementById('deptno').value,
								 'job'      : doc.getElementById('job').value,
								 'dname'    : doc.getElementById('job').value};
				
				if(employee.empno === '' ||
						employee.ename === '' ||
						employee.mgr === '' ||
						employee.hiredate === '' ||
						employee.sal === '' ||
						employee.comm === ''){
					alert("필수값을 입력하세요.");
				}else{
					addEmployee(employee);
				}
				
			};

			//등록 취소 버튼 클릭시
			var cancelBtn = doc.createElement('input');
			cancelBtn.setAttribute('id','cancelBtn');
			cancelBtn.setAttribute('type','button');
			cancelBtn.setAttribute('value','취소');
			cancelBtn.onclick = function(){
				addSection.innerHTML="";
			};

			addDiv.appendChild(addtable);
			addDiv.appendChild(okBtn);
			addDiv.appendChild(cancelBtn);
			addSection.appendChild(addDiv);

			//등록 페이지 중복 확인 버튼 클릭
			var duplCheckBtn = doc.createElement('input');
			duplCheckBtn.setAttribute('type','button');
			duplCheckBtn.setAttribute('value','중복확인');
			duplCheckBtn.setAttribute('style','margin-left: 5px;');
			duplCheckBtn.onclick = function(){
			 	duplCheck(doc.getElementById('ename').value);
			};
			doc.getElementById('enametd').appendChild(duplCheckBtn);

		};
		
		//중복 확인 함수
		var duplCheck = ename => {
			if (ename === "") {
				alert("이름을 입력해주세요.");
				return;
			}

			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {

				if (this.status === 200 && this.readyState === 4) {
					var checked;
					if (this.responseText === 'exists') {
						checked = confirm("이미 존재하는 이름입니다. 사용하시겠습니까?") ? "checked" : "unchecked";
					} else {
						alert("사용가능한 이름입니다.");
						checked = "checked";
					}
					
					localStorage.setItem("checked", checked);
					if(checked === "checked"){
						localStorage.setItem("checkedEname", ename);
					}
				}
			}
			
			xhr.open("POST", context+"/empDuplCheck.do", true);
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhr.send("ename="+ename);
			
		};
		
		//사원 등록 함수
		var addEmployee = employee =>{
			
			var checkedEname = localStorage.getItem('checkedEname');
			
			if (!(localStorage.getItem('checked') === 'checked' && checkedEname === employee.ename)) {
				alert("이름 중복 여부를 확인하세요.");
				return;
			}
			
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {

				if (this.status === 200 && this.readyState === 4) {
					if(this.responseText ==='SUCCESS'){
						alert("성공적으로 등록하였습니다.");
						
						var xhr = new XMLHttpRequest();
						xhr.onreadystatechange = function() {

							if (this.status === 200 && this.readyState === 4) {
								
								var json = JSON.parse(this.responseText);
								drawList(json);
								drawPagination(json);
							}	
						}
						xhr.open("GET",context+"/empList.do",true);
						xhr.send();
						
						var div = doc.getElementById('addSection');
						while (div.hasChildNodes()) {   
							  div.innerHTML = '';
						}
					}
				}
			}
			
			xhr.open("POST", context+"/empAdd.do", true);
			xhr.setRequestHeader("Content-Type","application/json");
			xhr.send(JSON.stringify(employee));
			
		}
		
		var sendData = pageNum=>{
			
			var searchOption = doc.getElementById('searchOption').value;
			var searchWord = doc.getElementById('searchWord').value;
			
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {

				if (this.status === 200 && this.readyState === 4) {
					
					var json = JSON.parse(this.responseText);
					drawList(json);
					drawPagination(json);
				
				}
			}
			xhr.open("GET",context+"/empList.do?pageNum="+pageNum+"&searchWord="+searchWord+"&searchOption="+searchOption,true);
			xhr.send();
		};
		
		var moveToDetail = empno =>{
			location.href = context+"/empDetail.do?empno="+empno;
		};
		

		var getParameter = function (name){
			   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(global.location.search)) {
				   return decodeURIComponent(name[1]);
			   }
			   return "";
		};
		
		var SubmintSearchForm = function(){
			
			var searchForm = doc.searchForm;
			
			if( searchForm.searchWord.value === ''){
				alert("검색어를 입력하세요");
			}else{
				sendData("1");
			}
		};
		
		doc.getElementById("addBtn").onclick = drawAddForm;
		doc.getElementById("searchButton").onclick = SubmintSearchForm;
	};
}(window,document));




