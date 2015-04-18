<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="resources/js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="resources/js/my-script.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/my-style.css">
        <script type="text/javascript" src="resources/js/bootstrap.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-responsive.css">

    </head>

    <body>
        <div class="container" id="container">

        <div class="row" id="header">
            header
        </div>

        <div class="row-fluid" id="content">

            <div class="span2" id="sidebar">
                <!-- Side bar -->
               <div id="sidebarBtn">
                    <ul class="unstyled" >
                        <li><input id="getAll" class="btn btn btn-primary" type="button" style="width: 140px; margin: 10px; margin-top: 80px; margin-bottom: 15px; background: #7851A9; height: 40px;" value="getAll"></li>
                        <li><input class="btn btn btn-primary" type="button" style="width: 140px; margin: 10px; margin-top: 0px; margin-bottom: 15px; background: #7851A9; height: 40px;" value="Input"></li>
                        <li><input class="btn btn btn-primary" type="button" style="width: 140px; margin: 10px; margin-top: 0px; margin-bottom: 15px; background: #7851A9; height: 40px;" value="Input"></li>
                        <li><input class="btn btn btn-primary" type="button" style="width: 140px; margin: 10px; margin-top: 0px; margin-bottom: 15px; background: #7851A9; height: 40px;" value="Input"></li>
                    </ul>
                </div>
            </div>

            <div class="span6" id="contentText">
                    <!--Text-->
                <div class="invisible table-responsive" id="tableAllContacts">
                    <table class="table table-striped table-bordered" id="table_allContacts">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Birth Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>

                <div class="tabbable" id="mainTable"> <!-- Only required for left/right tabs -->
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1" data-toggle="tab">UserInfo</a></li>
                            <li><a href="#tab2" data-toggle="tab">Friends</a></li>
                            <li><a href="#tab3" data-toggle="tab">Places</a></li>
                            <li><a href="#tab4" data-toggle="tab">Hobbies</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab1">
                                <div>
                                    <!--UserInfo-->
                                    <div class="span4" id="userInfo">
                                        <div class="row" id="photo">
                                            <img src="/resources/img/UsersPhoto.jpg" class="img-polaroid">
                                        </div>
                                        <div class="row" id="info">
                                            <div id="tableInfo">
                                                <!-- TableInfo -->
                                                <table class="table table-striped">
                                                    <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>firstName</th>
                                                        <th>lastName</th>
                                                        <th>birthDate</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td>1</td>
                                                        <td>Mark</td>
                                                        <td>Otto</td>
                                                        <td>@mdo</td>
                                                    </tr>
                                                    <tr>
                                                        <td>2</td>
                                                        <td>Jacob</td>
                                                        <td>Thornton</td>
                                                        <td>@fat</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3</td>
                                                        <td>Larry</td>
                                                        <td>the Bird</td>
                                                        <td>@twitter</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab2">
                                <p>Friends</p>
                            </div>
                            <div class="tab-pane" id="tab3">
                                <p>Places</p>
                            </div>
                            <div class="tab-pane" id="tab4">
                                <p>Hobbies</p>
                            </div>
                        </div>
                    </div>
            </div>
        </div>

        <div class="row" id="footer">
            footer
        </div>
    </div>
    </body>
</html>

            <%--<div>--%>
                <%--<button id="getAllContacts" class="btn">Find All</button>--%>
            <%--</div>--%>
            <%--<div id="qwerty"></div>--%>
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
