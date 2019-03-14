(function(global, doc) {
	global.onload = function() {

		var duplCheck = function() {
			var ename = document.getElementById("eName").value;
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
			
			xhr.open("POST", "/company/empDuplCheck.do", true);
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhr.send("ename="+ename);
			
			 //GET방식
			 //xhr.open("GET", "/company/empDuplCheck.do?ename="+ename,true);
			 //xhr.send();
		};

		var formSubmit = function() {
			
			var checkedEname = localStorage.getItem('checkedEname')
			var ename = doc.getElementById("eName").value;
			
			console.log(checkedEname);
			console.log(ename);
			
			console.log(localStorage.getItem('checked'));
			
			
			if (!(localStorage.getItem('checked') === 'checked' && checkedEname === ename)) {
				alert("이름 중복 여부를 확인하세요.");
				return;
			}
			var addForm = doc.addForm;
			console.log(doc.addForm.comm.value);
			addForm.action = "/company/empAdd.do";
			addForm.method = "post"
			addForm.submit();
		};

		doc.getElementById("duplCheckBtn").onclick = duplCheck;
		doc.getElementById("addFormSubmitBtn").onclick = formSubmit;
	};

}(window, document));