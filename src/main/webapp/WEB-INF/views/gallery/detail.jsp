<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css?family=Open+Sans:300,600&display=swap');

* {
	box-sizing: border-box;
}

body {
	background-color: #fafafa;
	font-family: 'Open Sans', sans-serif;
	padding-bottom: 100px;
}

.container {
	margin: 0 auto;
	max-width: 600px;
}

.blog-post {
	background-color: #fff;
    box-shadow: 0px 1px 2px rgba(50, 50, 50, .1), 
		0px 2px 4px rgba(60, 60, 60, 0.1);
	border-radius: 4px;
	padding: 40px;
	margin: 20px 0;
}

.title {
	margin: 0;
}

.text {
	color: #555;
	margin: 20px 0;
}

.user-info {
	display: flex;
	align-items: center;
}

.user-info img {
	border-radius: 50%;
	height: 30px;
	width: 30px;
}

.user-info span {
	font-size: 13px;
	margin-left: 10px;
}

.loading {
	opacity: 0;
	display: flex;
	position: fixed;
	bottom: 50px;
	left: 50%;
	transform: translateX(-50%);
	transition: opacity .3s ease-in;
}

.loading.show {
	opacity: 1;
}

.ball {
	background-color: #777;
	border-radius: 50%;
	margin: 5px;
	height: 10px;
	width: 10px;
	animation: jump .5s ease-in infinite;
}

.ball:nth-of-type(2) {
	animation-delay: 0.1s;
}

.ball:nth-of-type(3) {
	animation-delay: 0.2s;
}

@keyframes jump {
	0%, 100% {
		transform: translateY(0);
	}
	
	50% {
		transform: translateY(-10px);
	}
}
</style>
</head>
<body>
<script type="text/javascript">
const container = document.getElementById('container');
const loading = document.querySelector('.loading');

getPost();
getPost();
getPost();

window.addEventListener('scroll', () => {
	const  scrollTop, scrollHeight, clientHeight  = document.documentElement;
	
	
	if(clientHeight + scrollTop >= scrollHeight - 5) {
		// show the loading animation
		showLoading();
	}
});

function showLoading() {
	loading.classList.add('show');
	
	// load more data
	setTimeout(getPost, 1000)
}

async function getPost() {
	const postResponse = await fetch(`https://jsonplaceholder.typicode.com/posts/${getRandomNr()}`);
	const postData = await postResponse.json();
	
	const userResponse = await fetch('https://randomuser.me/api');
	const userData = await userResponse.json();
	
	const data = { post: postData, user: userData.results[0] };
	
	addDataToDOM(data);
}

function getRandomNr() {
	return Math.floor(Math.random() * 100) + 1;
}

function addDataToDOM(data) {
	const postElement = document.createElement('div');
	postElement.classList.add('blog-post');
	postElement.innerHTML = `<h2 class="title">${data.post.title}</h2><p class="text">${data.post.body}</p><div class="user-info"><img src="${data.user.picture.large}" alt="${data.user.name.first}" /><span>${data.user.name.first} ${data.user.name.last}</span></div>`;
	container.appendChild(postElement);
	
	loading.classList.remove('show');
} 	
</script>
	<div class="container" id="container">
	<h1>Blog Posts</h1>
 	<div class="blog-post">
		<h2 class="title">Blog post title</h2>
		<p class="text">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Provident quod debitis in repellat veritatis minus ab ex maiores itaque quis.</p>
		<div class="user-info">
			<img src="https://randomuser.me/api/portraits/women/26.jpg" alt="pic" />
			<span>Leah Taylor</span>
		</div>
	</div> 
</div>

<div class="loading">
	<div class="ball"></div>
	<div class="ball"></div>
	<div class="ball"></div>
</div>
</body>
</html>