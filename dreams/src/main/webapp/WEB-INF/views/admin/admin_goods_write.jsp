<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
A:link {
	color: #333333;
	font-family: "돋움", "굴림";
	font-size: 12px;
	text-decoration: none;
}

A:visited {
	color: #333333;
	font-family: "돋움", "굴림";
	font-size: 12px;
	text-decoration: none;
}

A:active {
	color: #333333;
	font-family: "돋움", "굴림";
	font-size: 12px;
	text-decoration: none;
}

A:hover {
	color: #BEA12C;
	font-family: "돋움", "굴림";
	font-size: 12px;
	text-decoration: underline;
}

A.user:link {
	color: #333333;
	font-family: "돋움", "굴림";
	font-size: 12px;
	text-decoration: underline;
}

A.user:visited {
	color: #333333;
	font-family: "돋움", "굴림";
	font-size: 12px;
	text-decoration: underline;
}

A.user:active {
	color: #333333;
	font-family: "돋움", "굴림";
	font-size: 12px;
	text-decoration: underline;
}

td {
	font-family: 돋움;
	font-size: 12px;
	color: #333333;
	line-height: 18px;
}

.title {
	font-family: 돋움;
	font-size: 12pt;
	color: #000000;
	line-height: 22px;
}

/* Form css */
.text {
	font-family: 돋움;
	font-size: 8pt;
	color: #333333;
}

.textarea {
	font-family: 돋움;
	font-size: 8pt;
	color: #333333;
}

.password {
	font-family: 돋움;
	font-size: 8pt;
	color: #333333;
}

.file {
	font-family: 돋움;
	font-size: 8pt;
	color: #333333;
}

.select {
	font-family: 돋움;
	font-size: 8pt;
	color: #333333;
}
</style>

<script>
	function goodsCreate() {
		f.action = "<c:url value="/admin/goods/write"/>";
		f.submit();
	}
</script>
<table width=780 border=0 cellpadding=0 cellspacing=0>
	<tr>
		<td width="20"></td>
		<td style="color: red;">${message }</td>
	</tr>

	<tr>
		<td width="20"></td>
		<td>
			<table width=590 border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>굿즈관리 - 굿즈등록</b></td>
				</tr>
			</table> <br>

			<form name="f" method="post" enctype="multipart/form-data">
				<table border="0" cellpadding="0" cellspacing="1" width="590" bgcolor="BBBBBB">
					<tr>
						<td width=100 align=center bgcolor="E6ECDE" height="22">굿즈 카테고리</td>
						<td colspan="4" bgcolor="ffffff" style="padding-left: 10px;">
							<select name="goodsCategory">
								<option value="U" selected>Uniform</option>
								<option value="C">Cap</option>
								<option value="F">Fan Goods</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width=100 align=center bgcolor="E6ECDE" height="22">굿즈 이름</td>
						<td colspan="4" bgcolor="ffffff" style="padding-left: 10px;">
							<input type="text" style="width: 150" name="goodsName" value="${goods.goodsName }">
						</td>
					</tr>
					
					<tr>
						<td width=100 align=center bgcolor="E6ECDE" height="22">가격</td>
						<td colspan="4" bgcolor="ffffff" style="padding-left: 10px;">
							<input type="text" style="width: 150" name="goodsPrice" value="${goods.goodsPrice }">
						</td>
					</tr>
					<tr>
						<td width=100 align=center bgcolor="E6ECDE" height="22">굿즈 정보</td>
						<td colspan="4" bgcolor="ffffff" style="padding-left: 10px;">
							<input type="text" style="width: 240" name="goodsInfo" value="${goods.goodsInfo }">
						</td>
					</tr>
					<tr>
						<td width=100 align=center bgcolor="E6ECDE" height="22">굿즈 사이즈</td>
						<td align=center bgcolor="ffffff" style="padding-left: 10px;">L</td>
						<td align=center bgcolor="ffffff" style="padding-left: 10px;">M</td>
						<td align=center bgcolor="ffffff" style="padding-left: 10px;">S</td>
						<td align=center bgcolor="ffffff" style="padding-left: 10px;">F</td>
					</tr>
					<tr>
						<td width=100 align=center bgcolor="E6ECDE" height="22">굿즈 판매여부</td>
						<td bgcolor="ffffff" style="padding-left: 10px;">
							<select name="goodsYnL">
								<option align=center value="Y" selected>Yes</option>
								<option align=center value="N">No</option>
							</select>
						</td>
						<td bgcolor="ffffff" style="padding-left: 10px;">
							<select name="goodsYnM">
								<option align=center value="Y" selected>Yes</option>
								<option align=center value="N">No</option>
							</select>
						</td>
						<td bgcolor="ffffff" style="padding-left: 10px;">
							<select name="goodsYnS">
								<option align=center value="Y" selected>Yes</option>
								<option align=center value="N">No</option>
							</select>
						</td>
						<td bgcolor="ffffff" style="padding-left: 10px;">
							<select name="goodsYnF">
								<option align=center value="Y" selected>Yes</option>
								<option align=center value="N">No</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width=100 align=center bgcolor="E6ECDE" height="22">굿즈 재고</td>
						<td align=center bgcolor="ffffff" style="padding-left: 10px;">
							<input type="text" style="width: 240" name="goodsStockL" value=0>
						</td>
						<td align=center bgcolor="ffffff" style="padding-left: 10px;">
							<input type="text" style="width: 240" name="goodsStockM" value=0>
						</td>
						<td align=center bgcolor="ffffff" style="padding-left: 10px;">
							<input type="text" style="width: 240" name="goodsStockS" value=0>
						</td>
						<td align=center bgcolor="ffffff" style="padding-left: 10px;">
							<input type="text" style="width: 240" name="goodsStockF" value=0>
						</td>
					</tr>
					<tr>
						<td width=100 align=center bgcolor="E6ECDE" height="22">굿즈 이미지</td>
						<td colspan="4" bgcolor="ffffff" style="padding-left: 10px;">
							<input type="file" style="width: 240" name="uploadImage">
						</td>
					</tr>
				</table>
			</form> <br>
			<table width=590 border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td align=center>
						<input type="submit" value="굿즈등록" onClick="goodsCreate();">
					</td>
				</tr>
				<tr>
					<td align=center>
					<a href="<c:url value="/admin/goods"/>">뒤로 가기</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>