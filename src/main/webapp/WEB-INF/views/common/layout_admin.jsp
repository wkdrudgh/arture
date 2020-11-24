<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
	body {
		-ms-overflow-style: none; 
	  	margin:0;
	  	padding:0;
	}
	
	/* chrome etc */
	body::-webkit-scrollbar { 
	    display: none !important; 
	}
	
	 #container {
        width: 100%;
        margin: 0px auto;        
        text-align:center;
   
      }
      #header {
        background-color: #000;
      }
      #sidebar-left {
        width: 17%;
        height:78.6vh;
        float: left;
        background-color: #eee;
        font-size:18px;
      }
      #content {
        width: 83%;
        float: left;
        border: 0px solid #bcbcbc;
      }
      #footer {
			clear: both;
			color: #fff;
			border-top:1px solid #464646;
			background-color: #000;
		}
		
		#footer .f_sitemap {
			width: 100%;
			margin: 0 auto;
			padding: 40px 0 20px 0;
		}
		
		#footer .f_sitemap li {
			display: inline;
			border-left: 1px solid #555;
			padding: 0 15px;
		}
		
		#footer .f_sitemap li:first-child {
			border-left: none;
		}
		
		#footer .f_sitemap li a {
			color: #fff;
			opacity: 0.7;
			text-decoration: none;
		}
		
		#footer .f_sitemap li a:hover {
			opacity: 1;
		}
		
		#footer .f_copy {
			display: block;
			width: 100%;
			text-align:center;
			height: 30px;
			font-size: 16px;
		}
      
</style>

<title><tiles:insertAttribute name="title"/></title>
</head>
<body>
	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header"/>
		</div>
		<div id="sidebar-left">
          <tiles:insertAttribute name="side"/> 
      </div>
		<div id="content">
			<tiles:insertAttribute name="body"/>
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer"/>
		</div>
	</div>
</body>
</html>