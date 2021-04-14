# jwt-spring-boot
<b>Simple JWT authorization for a rest resource built on spring-boot</b>

<b>Aim</b>: Project to show how to configure JWT token in a springboot rest application.<br/>
<b>Secure Endpoint</b>: "/hello" => http://localhost:8080/hello<br/>
<b>Method</b>: GET<br/>
<b>Response</b>: "Hello World"<br/>

<b>Endpoint</b>: "/authenticate" => http://localhost:8080/authenticate<br/>
<b>Method</b>: POST<br/>
<b>Request</b>: { "username":"flexy", "password":"flexy" }<br/>
<b>Response</b>: { "jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmbGV4eSIsImV4cCI6MTYxODQ3MzM3NywiaPF0IjoxNjE4NDM0Mzc3fQ.KZ1tx9YxmVlxotQlivsv_19MzyMR8a9vlL5_L_09zGI" }<br/>

<b>Steps:</b><br/>
<b>#1: Create an endpoint /authenticate</b><br/>
    -> Request: username and password<br/>
    -> Response: JWT token<br/>

<b>#2: Configure the application to use this JWT Token for other requests to authenticate</b><br/>
    -> Extract JWT token from request header<br/>
    -> Validate and set in execution context<br/>
    
<b>#3: Fire request to the "/hello" endpoint with and without the Bearer token</b><br/>
    
