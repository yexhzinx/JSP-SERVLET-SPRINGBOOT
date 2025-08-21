<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "java.util.*" %>
<%
	Scanner sc = new Scanner(System.in);
	System.out.println("단수 입력 : ");
	int dan= sc.nextInt();
	System.out.println(dan + "단");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		td {border: 1px solid; margin: 10px; background-color: beige;}
	</style>
</head>
<body>
	

	<!--  
		단수 입력받아 해당 구구단을 찍어보세요 (Table로 만드세요 - 스크립틀릿)
	-->
	
	<table>
		<tbody>

			<%
				for(int j = 1; j < 10; j++)
				{
			%>
				<%=dan + "X" + j + "=" + dan*j %><br>
			<%
				}
			
			%>

		</tbody>
	</table>
	
</body>
</html>