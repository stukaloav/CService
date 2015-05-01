<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <title>JSP Page</title>
        <script type="text/javascript" src="resources/js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="resources/js/jquery.cookie.js"></script>
        <script type="text/javascript" src="resources/js/my-script.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/my-style.css">
        <script type="text/javascript" src="resources/js/bootstrap.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
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
                        <li id="message"><a href="#">Сообщения</a></li>
                    </ul>
                </div>
            </div>
            <div class="span9" id="info" style="margin-left: 280px">

                <div class="div-info" id="div-details">
                    <div class="span5" id="div-userDetails">
                        <ul id="myTab" class="nav nav-tabs">
                            <li class="active"><a href="#div-contact-info" data-toggle="tab" id="btn-contact-info">Info</a></li>
                            <li class=""><a href="#div-contact-hobbies" data-toggle="tab">Hobbies</a></li>
                            <li class=""><a href="#div-contact-places" data-toggle="tab" id="btn-contact-places">Places</a></li>
                            <li class=""><a href="#div-contact-friends" data-toggle="tab">Friends</a></li>
                        </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="div-contact-info">
                            <div class="div-contactInfo" id="div-contact-picture">
                                <img src="resources/images/shape.jpg" class="img-polaroid">
                            </div>
                            <div class="div-contactInfo table-responsive" id="div-table-contact-info">
                                <table class="table table-striped table-bordered" id="table-contact-info">
                                    <thead>
                                    <tr>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Birth Date</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="div-contact-hobbies">
                            <table class="table table-striped table-bordered" id="table-contact-hobbies">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>title</th>
                                        <th>description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="div-contact-places">

                            <table class="table table-striped table-bordered" id="table-contact-places">
                                <thead>
                                    <th>#</th>
                                    <th>title</th>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="div-contact-friends">
                            <p>Food truck fixie locavore, accusamus mcsweeney&#8217;s marfa nulla single-origin coffee squid.
                                Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four
                                loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip
                                jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit.
                                Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes
                                anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester
                                stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>
                        </div>
                    </div>
                    </div>
                    <div class="span3 div-details-satellite" id="div-userMessages">
                        <h3>Messages</h3>
                        <textarea rows="15" style="width: 290px;" id="textarea-conversation"></textarea>
                        <textarea rows="3" style="width: 290px;" id="textarea-currentMessage"></textarea>
                    </div>
                    <div class="span3 div-details-satellite invisible" id="div-userPlacesMap">
                        <h3>Map</h3>
                        <div id="map_canvas" style="width:300px; height:300px"></div>
                    </div>
                </div>

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

