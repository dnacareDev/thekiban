<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	변수전송 확인
	<% String h2 = request.getParameter("h2"); %>
	<% String NbChromosome = request.getParameter("NbChromosome"); %>
	<% String NumberOfRepeats = request.getParameter("NumberOfRepeats"); %>
	<% String geneticLengthF = request.getParameter("geneticLengthF"); %>
	<% String geneticLengthM = request.getParameter("geneticLengthM"); %>
	<% String NbQTLs = request.getParameter("NbQTLs"); %>
	<% String NbSNPs = request.getParameter("NbSNPs"); %>
	<% String NbKeepQTL = request.getParameter("NbKeepQTL"); %>
	<% String nuGammaF = request.getParameter("nuGammaF"); %>
	<% String nuGammaM = request.getParameter("nuGammaM"); %>
	<% String pM = request.getParameter("pM"); %>
	<% String jobid = request.getParameter("jobid"); %>

	<div> h2 : <%=h2 %></div>	
	<div> NbChromosome : <%=NbChromosome %> </div>
	<div> NumberOfRepeats : <%=NumberOfRepeats %> </div>
	<div> geneticLengthF : <%=geneticLengthF %> </div>
	<div> geneticLengthM : <%=geneticLengthM %> </div>
	<div> NbQTLs : <%=NbQTLs %> </div>
	<div> NbSNPs : <%=NbSNPs %> </div>
	<div> NbKeepQTL : <%=NbKeepQTL %> </div>
	<div> nuGammaF : <%=nuGammaF %> </div>
	<div> nuGammaM : <%=nuGammaM %> </div>
	<div> pM : <%=pM %> </div>
	<div> jobid : <%=jobid %> </div>
	
	
	<!--   window.open('http://112.169.63.197:8883/mabcsimulation/generation_config.jsp?jobid=20220520221050&htwo=1&nbchromosome=10&numberofrepeats=1&gtrainingpop=6,7&geneticlengthf=92.9,78,102.9,56.2,95.4,105.7,74.1,56.8,103.9,55.8&geneticlengthm=87.2,83.9,117.4,68.8,98.9,101.5,90.1,68,123.8,52.9&nbqtls=1,0,0,0,0,0,0,0,0,0&nbsnps=894,98,123,75,99,97,103,81,148,45&nbkeepqtl=1,0,0,0,0,0,0,0,0,0&nugammaf=6.97,6.139,7.207,18.576,12.934,8.814,13.001,12.185,4.895,63.778&pf=0.864,0.97,0.901,0.93,0.935,0.936,0.95,0.929,0.966,0.919&nugammam=4.891,6.15,7.773,4.369,13.46,7.356,17.801,23.329,6.886,11.693&pm=1,1,0.971,1,0.93,0.94,0.932,0.929,0.905,1', 'pop01', 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no'); -->

	<!-- ${} ${} ${} ${} ${} ${} ${} ${} ${} ${} ${} ${} ${} ${}  -->

</body>
</html>