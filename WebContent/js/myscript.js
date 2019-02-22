/* myscript.js */

function showtime(){
			var today=new Date();

			var str="";

			str += today.getFullYear()+".";

			if(today.getMonth()+1<10){
				str += "0";
			}
			str += (today.getMonth()+1)+".";

			if(today.getDate()<10){
				str += "0";
			}
			str += today.getDate();

			var weekName=["일", "월", "화", "수", "목", "금", "토"];
			str += " ("+weekName[today.getDay()]+") ";

			if((today.getHours()>12 && today.getHours()<23) || today.getHours()==0){
				str += "PM "+(today.getHours()-12)+":";
			}else{
			str += "AM "+today.getHours()+":";
			}

			if(today.getMinutes()<10){
				str+="0";
			}
			str += today.getMinutes()+":";

			if(today.getSeconds()<10){
				str+="0";
			}
			str += today.getSeconds();	

			document.myform.clock.value=str;
			timer = setTimeout(showtime, 1000);
		}//showtime() end

function del(){
   if(confirm("삭제할까요?")){
       alert("확인을 선택했습니다");
   }
   else{
       alert("취소를 선택했습니다");
   }
   return;
}
		

function bbsCheck(f){	//답변형 게시판 유효성검사
	// 작성자, 제목, 내용글자, 비번, 글자 갯수 확인
	
	//1) 작성자
	var wname=f.wname.value;
	wname=wname.trim();
	if(wname.length==0){
		alert("작성자 입력해주세요");
		f.wname.focus();
		return false;
	}//end
	
	//2) 제목
	
	
	//3) 내용
	
	
	//4) 비밀번호 4글자이상
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length<4){
		alert("비밀번호 4글자 이상 입력해주세요");
		f.passwd.focus();
		return false;
	}//end
	
	//5) 글자 갯수
	
	
	return true;	//onsubmit 이벤트에서 서버로 전송
	
}//bbsCheck() end

function pwCheck(f){	//비밀번호 유효성 검사
	//비밀번호가 입력되었는지 확인
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length<4){
		alert("비밀번호 4글자 이상 입력해주세요");
		f.passwd.focus();
		return false;
	}//end
	
	var msg="진행된 내용은 복구되지 않습니다\n계속 진행 할까요?";
	if(confirm(msg)){	//확인 true, 취소 false 반환
		return true;
	}else{
		return false;
	}
	return true;
}//pwCheck() end

function upPwCheck(f){	//수정
	//비밀번호가 입력되었는지 확인
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length<4){
		alert("비밀번호 4글자 이상 입력해주세요");
		f.passwd.focus();
		return false;
	}//end
	
	return true;
}//pwCheck() end


function move(f, file){
	f.action=file;
	f.submit();
}//pwCheck() end

/*
function move(f, file){
	f.action=file;
	f.submit();
}//pwCheck() end
*/

function searchCheck(f){
	var word=f.word.value;
	word=word.trim();
	if(word.length==0){
		alert("검색어를 입력하세요");
		return false;
	}
	
	return true;
}//searchCheck() end


function idCheck(){
	//아이디 중복확인
	
	//새창 만들기
	//window.open("파일명", "창이름", "다양한 옵션")
	window.open("idCheckForm.jsp", "idwin", "width=400, height=350");
	
	//새창이 출력되는 위치 지정
	var sx=parseInt(screen.width);	//모니터 해상도 넓이
	var sy=parseInt(screen.height);	//모니터 해상도 높이
	var x=(sx/2)+50;
	var y=(sy/2)-25;
	
	//화면이동
	win.moveTo(x, y);
	
	
	
}//idCheck() end


function emailCheck(){
	//아이디 중복확인
	
	//새창 만들기
	//window.open("파일명", "창이름", "다양한 옵션")
	window.open("emailCheckForm.jsp", "emailwin", "width=400, height=350");
	
	//새창이 출력되는 위치 지정
	var sx=parseInt(screen.width);	//모니터 해상도 넓이
	var sy=parseInt(screen.height);	//모니터 해상도 높이
	var x=(sx/2)+50;
	var y=(sy/2)-25;
	
	//화면이동
	win.moveTo(x, y);
	
}//emailCheck() end


function memberCheck(f){
	//회원가입 유효성 검사
	
	//1) 아이디5~10글자 이내
	var id=f.id.value;
	id=id.trim();
	var reid= /[a-zA-Z0-9]{5,10}$/g;
	reid=reid.trim();
	
	if(id.length==0){
		alert("아이디 입력하세요");
		return false;
	}else if(id.length<5||id.length>10){
		alert("아이디 5~10 글자 입력하세요");
		return false;
	}
	if(reid.test(id)==false){
		alert("아이디에 한글이나 특수문자가 올 수 없습니다.");
		return false;
	}
	
	//2) 비번 5~10글자 이내
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length==0){
		alert("비밀번호 입력하세요");
		return false;
	}else if(passwd.length<5||passwd.length>16){
		alert("비밀번호 5~16 글자 입력하세요");
		return false;
	}
	
	//3) 비번 특수문자 포함되어 있는지? 숫자, 영문자포함해야함
	if(/(\w)\1\1\1/.test(passwd)){
		alert('같은 문자를 4번 이상 사용하실 수 없습니다.');
		return false;
	}

	if(passwd.search(id) > -1){
		alert("비밀번호에 아이디가 포함되었습니다. 다시 입력해주세요.");
		return false;
	}
	
	var check = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{5,16}$/;
	check=check.trim();
	if(!check.test(passwd)){
		alert("비밀번호는 영문, 숫자, 특수문자 사용해주세요");
		return false;
	}

	//4) 비번과 비번확인이 서로 일치하는지?
	var repasswd=f.repasswd.value;
	repasswd=repasswd.trim();
	if(repasswd.length==0){
		alert("비밀번호 확인해주세요");
		return false;
	}else if(repasswd!=passwd){
		alert("비밀번호가 일치하지 않습니다");
		return false;
	}
	
	//5) 이름 2~20글자 이내
	var mname=f.mname.value;
	mname=mname.trim();
	var rename= /[a-zA-Z가-힣]{2,20}$/g;
	rename=rename.trim();
	if(mname.length==0){
		alert("이름 입력하세요");
		return false;
	}else if(mname.length<2||mname.length>20){
		alert("이름 2~20 글자 입력하세요");
		return false;
	}
	if(rename.test(mname)==false){
		alert("이름에는 숫자나 특수문자가 올 수 없습니다.");
		return false;
	}
	
	//6) 이메일에 @문자 있는지
	var email=f.email.value;
	email=email.trim();
	var remail=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	remail=remail.trim();
	if(email.length==0){
		alert("이메일 입력하세요");
		f.email.focus();
		return false;
	}else if(remail.test(email)==false){
		alert("이메일 형식이 아닙니다");
		f.email.focus();
		return false;;
	}//if end
	
	//7) 직업을 선택했는지?
	var job = f.jop.value;
	job=job.trim();
	if(job=="0"){
		alert("직업을 선택 하세요");
		return false;
	}

	return true;	//유효성 검사를 통과했으므로 memberProc.jsp.로 전송

}//memberCheck() end

function loginCheck(f){
	//로그인 유효성 검사
	var id=f.id.value;
	id=id.trim();
	
	if(id.length==0){
		alert("아이디 입력하세요");
		return false;
	}
	
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length==0){
		alert("비밀번호 입력하세요");
		return false;
	}
	
	return true;
}//loginCheck() end


function pdsCheck(f){
	// 포토갤러리 유효성 검사
	//1) 이름
	
	//2) 제목
	
	//3) 비밀번호
	
	//4) 첨부파일
	//문) 첨부한 파일의 확장명을 출력하시오
	//		예) .png gif jpg 등.. (.없어도됨)		//indexOf/split
	//indexOf
	var filename=f.filename.value;
	filename=filename.trim();
	if(filename.length<5){
		alert("첨부 파일 선택하세요");
		return false;
	}//if end
	
	//alert(filename);
	//alert(filename.lastIndexOf(".")); 	//마지막.의 위치
	//alert(filename.substr(filename.lastIndexOf(".")));
	
	var ext=filename.substr(filename.lastIndexOf(".")+1);
	ext=ext.toLowerCase();	//전부 소문자
	if(!(ext=="png" || ext=="gif" || ext=="jpg")){
		alert("이미지 파일만 가능합니다");
		return false;
	}//if end
	
	return true;
	
	/* 나
	var filename=f.filename.value;
	var ep=filename.substring(filename.indexOf("."));
	var ep=filename.substring(filename.lastIndexOf(".")+1);
	alert("확장명 : "+ep);
	*/
	
}//pdsCheck() end////////////////////////////////////////////////////////////////////////////////////////////////////////


function namemailCheck(f){	//이름/메일유효성검사
	var wname=f.wname.value;
	passwd=passwd.trim();
	if(passwd.length<4){
		alert("비밀번호 4글자 이상 입력해주세요");
		f.passwd.focus();
		return false;
	}//end
	
	var msg="진행된 내용은 복구되지 않습니다\n계속 진행 할까요?";
	if(confirm(msg)){	//확인 true, 취소 false 반환
		return true;
	}else{
		return false;
	}
	return true;
	
	
}//namemailCheck() end/////////////////////////////////////////////////////////////////////////////////////////////////

//NOTICE
function ntcCheck(f){	//답변형 게시판 유효성검사
/*if(s_mlevel.equals("A1")){ %> <!-- A1인 등급인 애들만 수정 삭제 가능 -->
	<input type="button" value="수정" onclick="move(this.form, 'bbsUpdate.jsp')">
	<input type="button" value="삭제" onclick="move(this.form, 'bbsDel.jsp')">
<%}//if end %>
*/	// 제목, 내용글자, 비번, 글자 갯수 확인	
	//2) 제목
	var subject=f.subject.value;
	subject=subject.trim();
	if(subject.length==0){
		alert("내용 입력해주세요");
		f.subject.focus();
		return false;
	}//end
	
	//3) 내용
	var content=f.content.value;
	content=content.trim();
	if(content.length==0){
		alert("내용 입력해주세요");
		f.content.focus();
		return false;
	}//end
	
	//4) 비밀번호 4글자이상
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length<4){
		alert("비밀번호 4글자 이상 입력해주세요");
		f.passwd.focus();
		return false;
	}//end
	
	//5) 글자 갯수
	
	
	return true;	//onsubmit 이벤트에서 서버로 전송
	
}//bbsCheck() end


function mem_passCheck(f){	//회원정보수정/탈퇴시 비밀번호 확인
	//비밀번호가 입력되었는지 확인
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length<5){
		alert("비밀번호 5글자 이상 입력해주세요");
		f.passwd.focus();
		return false;
	}//end
	
	return true;
}//pwCheck() end

