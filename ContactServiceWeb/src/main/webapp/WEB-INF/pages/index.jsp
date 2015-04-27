<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="resources/js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="resources/js/jquery.cookie.js"></script>
        <script type="text/javascript" src="resources/js/my-script.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/my-style.css">
        <script type="text/javascript" src="resources/js/bootstrap.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap-datepicker.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="resources/css/datepicker.css">


    </head>

    <body>
    <div class="container">
        <div class="row" id="header">
            <div class="span12" id="header-div">
                <div class="span6">header</div>
                <div class="span3" id="div-user">
                    <h5 id="user-name" style="color: #fff"></h5>
                </div>
                <div class="span2">
                    <button class="btn invisible" id="btn-userEnter">enter</button>
                    <button class="btn btn-exit invisible" id="btn-userExit">exit</button>
                </div>
            </div>
        </div>
        <div class="row" id="content">
            <div class="span3" id="sidebar">
                <div class="row" id="sidebar-btns">
                    <ul class="nav nav-pills nav-stacked">
                        <li id="getAllContacts" class="btn btn-method"><a href="#">getAllContacts</a></li>
                        <li id="addContact" class="btn btn-method"><a href="#">addContact</a></li>
                        <li><a href="#">Сообщения</a></li>
                    </ul>
                </div>
            </div>
            <div class="span9" id="info" style="margin-left: 280px">
                <div class="div-info invisible" id="div-form-enter">
                    <form class="form-horizontal" style="margin-top: 50px;">
                        <div class="control-group">
                            <label class="control-label">First Name</label>
                            <div class="controls">
                                <input type="text" id="userFirstName" placeholder="John">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Last Name</label>
                            <div class="controls">
                                <input type="text" id="userLastName" placeholder="Smith">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Birth Date</label>
                            <div class="controls">
                                <input type="text" name="date" class="datepicker" id="userBirthDate" placeholder="mm/dd/yyyy">
                            </div>
                        </div>
                    </form>
                    <div class="row" id="div-userEnter-submit">
                        <button class="btn" id="btn-userEnterSubmit" style="margin-left: 200px;">Submit</button>
                        <button class="btn btn-exit" id="btn-exit-submit">exit</button>
                    </div>
                    <div class="row invisible" id="div-add-newUserOnEnter">
                        <button class="btn" id="btn-add-newUserOnEnter" style="margin-left: 200px; background: green">Add</button>
                        <button class="btn btn-exit" id="btn-exit-newUserOnEnter" style="margin-left: 200px; background: red">Exit</button>
                    </div>
                </div>
                <div class="div-info invisible table-responsive" id="div-table-allContacts">
                    <table class="table table-striped table-bordered" id="table-allContacts">
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

                <div class="div-info invisible" id="div-form-addContact">
                        <form class="form-horizontal" style="margin-top: 50px;">
                            <div class="control-group">
                                <label class="control-label">First Name</label>
                                <div class="controls">
                                    <input type="text" id="firstName" placeholder="John">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Last Name</label>
                                <div class="controls">
                                    <input type="text" id="lastName" placeholder="Smith">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Birth Date</label>
                                <div class="controls">
                                    <input type="text" name="date" class="datepicker" id="birthDate" placeholder="mm/dd/yyyy">
                                </div>
                            </div>
                        </form>
                    <div class="row" id="div-addContact-submit">
                        <button class="btn" id="btn-addContactSubmit" style="margin-left: 200px;">Submit</button>
                        <button class="btn btn-exit" id="btn-addContactExit" style="margin-left: 200px;">Exit</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
    </body>
</html>

