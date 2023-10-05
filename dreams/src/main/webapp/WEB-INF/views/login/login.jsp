<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.aaa:hover {
   transform: scale(1.1);
    opacity: 0.7; 
    cursor: pointer; 
}

.aaa:hover:after {
   height: 5px;
  opacity: 1;
  -webkit-transform: translateY(0px);
  -moz-transform: translateY(0px);
  transform: translateY(0px);
}

</style>

<script>
	function MemberLogin() {
		if (login.memberId.value == "") {
			alert("아이디를 입력하십시오.");
			login.memberId.focus();
			return;
		}
		if (login.memberPw.value == "") {
			alert("비밀번호를 입력하십시오.");
			login.memberPw.focus();
			return;
		}
		login.action = "<c:url value="/login"/>";
		login.submit();
	}

	// 강민경(수정): 2023/09/09 Enter 이벤트 추가 
	document.addEventListener("keyup", function(event) {
		if (event.key === 'Enter') {
			$(MemberLogin).click();
		}
	});
</script>


<!-- 로그인 -->
<div class="login-register-area pt-100 pb-100">
	<div class="container">
		<div class="row">
			<div class="col-lg-0 col-md-20 ml-auto mr-auto">
				<div class="login-register-wrapper"
					style="width: 50%; margin: 50px auto;">
					<div class="login-register-tab-list nav">
						<!--강민경: 로고 이미지 기입-->
						<li id="loginimg"><img
							src="${pageContext.request.contextPath }/img/logo/dreams_login_logo.png">
						</li>

					</div>
					<div class="login-register-tab-list nav">
						<li><a class="active" data-toggle="tab" href="#lg1">
								<h6>반갑습니다. Dreams Club입니다.</h6>
						</a></li>
					</div>
					<div class="tab-content">
						<div id="lg1" class="tab-pane active">
							<div class="login-form-container">
								<div class="login-register-form">
									<form method="post" name="login" id="login_form">
										<input type="text" name="memberId" placeholder="아이디를 입력해 주세요." />
										<input type="password" name="memberPw"
											placeholder="비밀번호를 입력해 주세요." />
										<div class="button-box">
											<!--아이디 찾기, 비밀번호 찾기, 회원가입-->
											<div class="login-toggle-btn">
												<div>
													<a href="/dreams/login/search_pw">비밀번호
														찾기&nbsp;&nbsp;&nbsp;&nbsp;</a>
												</div>

												<div>
													<a href="/dreams/login/search_id">아이디
														찾기&nbsp;&nbsp;&nbsp;&nbsp;</a>
												</div>

												<div>
													<a href="/dreams/join/check">회원가입&nbsp;&nbsp;&nbsp;&nbsp;</a>
												</div>

											</div>
											<button type="button" onclick="MemberLogin();"
												class="MemberLogin">
												<span>로그인</span>
											</button>
											<!-- <a href="http://localhost:8001/dreams/login/kakao"><img src="<c:url value="/img/login/kakao_login_medium_wide.png"/>"></a> -->
	
											<p style="text-align: center; font-weight: bold; color: gray; margin: 25px 0 0 0;">소셜 계정으로 로그인</p>
											<div style="display: flex;">
												<div style="width: 50%; padding: 0 20px; text-align: right;">
													<img alt="카카오 로그인" class="aaa" style="width: 50px; margin: 10px 0 0 0;"
														src="<c:url value="/img/login/kakaotalk_sharing_btn_medium.png"/>"
														onclick="location.href='<c:url value="/login/kakao"/>';">
												</div>
												<div style="width: 50%; padding: 10px 0 0 20px;">
													<img alt="네이버 로그인" class="aaa" style="width: 50px;"
														src="<c:url value="/img/login/naver.png"/>"
														onclick="location.href='<c:url value="/login/naver"/>';">
												</div>
											</div>
											<tr>
												<td width="20"></td>
												<td style="text-align: center;"><font color="red">${message }</font></td>
											</tr>
											<!--틀린 경우 정보와 다르다는 메세지를 띄우고 3번 이상 틀릴 시 휴면 계정으로 변환-->
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>