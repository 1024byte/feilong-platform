<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>��������һ�����������ҹ���ͼƬ���Զ�����</title>
		<link href="${pageContext.request.contextPath}/greencolor/css/feilongScroll.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<span id="span_test">1</span>
		<% for(int i=0;i<5;i++){%>
		<div class="rollBox">
			<div class="LeftBotton"></div>
			<div class="Cont" >
				<div class="ScrCont">
					<div class="List1">
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s1.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">�羰���续</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s2.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">�Ұ�Դ�밮����</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s3.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">ѧϰ��Դ��վ</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s4.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">ÿһ�����</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s5.jpg" width="109" height="87" alt="���ѵ���ϲ��" /> </a>
							<p>
								<a href="#" target="_blank">���ѵ���ϲ��</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s6.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">��̫����ʧ����</a>
							</p>
						</div>
						<div class="pic">
							<a href="/" target="_blank"><img src=" http://www.codefans.net/jscss/demoimg/wall_s7.jpg" width="109" height="87" /> </a>
							<p>
								<a href="#" target="_blank">�������˯</a>
							</p>
						</div>
					</div>
					<div class="List2"></div>
				</div>
			</div>
			<div class="RightBotton""></div>
		</div>
		<br />
		<%} %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/greencolor/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/greencolor/js/feilong-scroll.js"></script>
	</body>
</html>
