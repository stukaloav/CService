<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="<c:url value="/resources/jquery-2.1.1.js"/>"></script>
        <script src="<c:url value="/resources/my-script.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/core/my-style.css"/>"/>
    </head>

    <body>
    
        <div>
                <button id="getAllContacts">Find All</button>
            </div>
            <div id="qwerty"></div>
<!--  
        <div>
            <span id="firstFormRef">Form1</span>
            <span id="secondFormRef">Form2</span>
        </div>
        <div id="form1">
            <div>
                <p>First name</p>
                <input type="text" id="firstNameInput"/>
            </div>
            <div>
                <p>Last name</p>
                <input type="text"/>
            </div>
            <div>
                <p>Phone number</p>
                <input type="text"/>
            </div>
            <div>
                <p>E-mail</p>
                <input type="text"/>
            </div>
            <button id="submitBtn">Submit</button>
        </div>
        <div id="form2" class="hidden">
            <p>First name for search</p>
            <input id="findInput" type="text"/>
            <button id="findBtn">Try to find</button>
            <div id="qwerty"></div>
        </div>
-->
    </body>
</html>