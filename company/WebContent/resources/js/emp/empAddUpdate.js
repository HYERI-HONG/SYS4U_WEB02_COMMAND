var emp = ((global, doc)=>{
	return {
		loadUpdateForm : x=>{
		
			var updateForm = doc.updateForm;
			
			updateForm.empno.value = x.empno;
			updateForm.ename.value = x.ename;
			updateForm.mgr.value = x.mgr;
			updateForm.hiredate.value = x.hiredate;
			updateForm.sal.value = x.sal;
			updateForm.comm.value = x.comm;
			
			updateForm.empno.readOnly = true;
			updateForm.ename.readOnly = true;
			updateForm.hiredate.readOnly = true;
			
			for(var i of document.querySelectorAll('.deptno-opt')){
				if(i.getAttribute('value')===x.deptno){
					i.setAttribute("selected","selected");
				}
			}
			
			for(var i of document.querySelectorAll('.job-opt')){
				if(i.getAttribute('value')===x.job){
					i.setAttribute("selected","selected");
				}
			}	
			
			doc.getElementById('updateFormBtn').addEventListener('click', function(){
				if(updateForm.mgr.value === ''||
						updateForm.sal.value === ''||
						updateForm.comm.value === ''){
					alert("필수값을 입력하세요.");
				}else{
					updateForm.action = x.context+"/empUpdate.do";
					updateForm.method = "post";	
					updateForm.submit();
				}
			
			});
		},
		loadAddForm : x=>{
		
			var addForm = doc.addForm;
			addForm.duplCheckBtn.style.visibility = 'visible';	
			addForm.hiredate.min = '2019-01-01';
			
			var duplCheck = ()=> {
				var ename = addForm.ename.value;
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
				
				xhr.open("POST", x+"/empDuplCheck.do", true);
				xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				xhr.send("ename="+ename);
				
				 // GET방식
				 // xhr.open("GET",
					// "/company/empDuplCheck.do?ename="+ename,true);
				 // xhr.send();
			};
	
			var formSubmit = ()=>{
				
				var addForm = doc.addForm;
				
				var checkedEname = localStorage.getItem('checkedEname');
				
				if (!(localStorage.getItem('checked') === 'checked' && checkedEname === addForm.ename.value)) {
					alert("이름 중복 여부를 확인하세요.");
					return;
				}
				
				if(addForm.empno.value===''||
						addForm.ename.value === ''||
						addForm.mgr.value === ''||
						addForm.hiredate.value === ''||
						addForm.sal.value === ''||
						addForm.comm.value === ''){
					alert("필수값을 입력하세요.");
				}else{
					addForm.action = x+"/empAdd.do";
					addForm.method = "post"
					addForm.submit();
				}
			};
	
			doc.getElementById("duplCheckBtn").onclick = duplCheck;
			doc.getElementById("addFormBtn").onclick = formSubmit;
		}
	};
})(window, document);

