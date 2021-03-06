<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>햄버거 주문</title>
<style>
	table {
		border : 1px solid black;
		border-collapse : collapse;
	}
	
	th, td {
		border : 1px solid black;
		padding : 10px;
		text-align : center;
	}
</style>
</head>
<body>
<h1>메뉴</h1>
	<form action="/study2/confirmBerger" method="post">
		<table>
			<tr>
				<th width="50">종류</th>
				<th>이름</th>
				<th>가격</th>
				<th>체크</th>
			</tr>
			<tr>
				<td rowspan="5">햄버거</td>
				<td>불고기버거</td>
				<td>5000</td>
				<td><input type="radio" name="berger" value="불고기버거"></td>
			</tr>
			<tr>
				<td>치즈버거</td>
				<td>6000</td>
				<td><input type="radio" name="berger" value="치즈버거"></td>
			</tr>
			<tr>
				<td>상하이버거</td>
				<td>7000</td>
				<td><input type="radio" name="berger" value="상하이버거"></td>
			</tr>
				<tr>
				<td>빅맥</td>
				<td>8000</td>
				<td><input type="radio" name="berger" value="빅맥"></td>		
			</tr>
			<tr>
				<td>싸이버거</td>
				<td>9000</td>
				<td><input type="radio" name="berger" value="싸이버거"></td>
			</tr>
			<tr>
				<td rowspan="6">사이드메뉴</td>
				<td>양념감자</td>
				<td>1000</td>
				<td><input type="checkbox" name="side" value="양념감자"></td>
			</tr>
			<tr>
				<td>아이스크림</td>
				<td>1500</td>
				<td><input type="checkbox" name="side" value="아이스크림"></td>	
			</tr>
			<tr>
				<td>맥너겟</td>
				<td>2000</td>
				<td><input type="checkbox" name="side" value="파인애플 토핑"></td>	
			</tr>
			<tr>
				<td>롱치즈</td>
				<td>2000</td>
				<td><input type="checkbox" name="side" value="롱치즈"></td>	
			</tr>
			<tr>
				<td>치즈스틱</td>
				<td>2000</td>
				<td><input type="checkbox" name="side" value="치즈스틱"></td>	
			</tr>
			<tr>
				<td>치즈볼</td>
				<td>3000</td>
				<td><input type="checkbox" name="side" value="치즈볼"></td>	
			</tr>
		</table>
		<input type="submit" value="주문하기">
	</form>


</body>
</html>