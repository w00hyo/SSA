<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">

	<div class="container-fluid">
	
		<a class="navbar-brand" href="http://localhost:8080/main">
		HOME
		</a>
		
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
		 <span class="navbar-toggler-icon"></span>		
		</button>
		
		<div class="collapse navbar-collapse" id="mynavbar">
			<ul class="navbar-nav me-auto">
				
				<li class="nav-item">
					<a class="nav-link" href="http://localhost:8080/member/login">
					login
					</a>
				</li>	
				
				<li class="nav-item">
					<a class="nav-link" href="http://localhost:8080/member/join">
					join
					</a>
				</li>	
			
			</ul>	
			<form class="d-flex">
			<input class="form-control me-2" type="text" placeholder="Search">
			<button class="btn btn-primary" type="button">Search</button>
			
			
			</form>	
		</div>
		
		
		
	
	</div>

</nav>