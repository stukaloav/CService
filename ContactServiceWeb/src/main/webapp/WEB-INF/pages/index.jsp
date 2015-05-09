<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="resources/css/my-style.css">
        <script type="text/javascript" src="resources/js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="resources/js/jquery.cookie.js"></script>
        <script type="text/javascript" src="resources/js/my-script.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBYBCOWn5HLUBSxqK4PSOf0mCRm-mIl-Mg&sensor=false"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/datepicker.css">
        <script type="text/javascript" src="resources/js/geocoder.js"></script>


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
                        <button id="getAllContacts" class="btn btn-method">getAllContacts</button>
                        <button id="getAllHobbies" class="btn btn-method">getAllHobbies</button>
                        <button id="getAllPlaces" class="btn btn-method">getAllPlaces</button>
                </div>
            </div>
            <div class="span9" id="inform" style="margin-left: 280px">

                <div class="div-info invisible" id="div-details">


                    <div class="span5 div-details-satellite invisible" id="div-place-details">
                        <h4 id="place-detail-title">Place-detail-title</h4>
                        <button class="btn invisible" style="background: greenyellow" id="btn-addThisPlace">addThisPlace</button>
                        <button class="btn invisible" style="background: indianred;" id="btn-removeThisPlace">removeThisPlace</button>
                        <table class="table table-bordered" id="table-place-details">
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

                    <div class="span5 div-details-satellite invisible" id="div-hobby-details">
                        <h4 id="Hobby-detail-title">Hobby-detail-title</h4>
                        <button class="btn invisible" style="background: greenyellow" id="btn-addThisHobby">addThisHobby</button>
                        <button class="btn invisible" style="background: indianred;" id="btn-removeThisHobby">removeThisHobby</button>
                        <table class="table table-bordered" id="table-hobby-details">
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


                    <div class="span5" id="div-userDetails">
                        <ul id="myTab" class="nav nav-tabs">
                            <li class="active" id="li-contact-info"><a href="#div-contact-info" data-toggle="tab" id="btn-contact-info">Info</a></li>
                            <li class=""><a href="#div-contact-hobbies" data-toggle="tab" id="btn-contact-hobbies">Hobbies</a></li>
                            <li class="" id="li-contact-place"><a href="#div-contact-places" data-toggle="tab" id="btn-contact-places">Places</a></li>
                            <li class=""><a href="#div-contact-friends" data-toggle="tab" id="btn-contact-friends">Friends</a></li>
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
                            <div class="div-contactInfo" id="div-btn-friendship">
                                <button class="btn invisible" id="btn-addFriendship" style="background: greenyellow">addFriendship</button>
                                <button class="btn invisible" id="btn-removeFriendship" style="background: indianred">removeFriendship</button>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="div-contact-hobbies">
                            <table class="table table-bordered" id="table-contact-hobbies">
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
                            <table class="table table-bordered" id="table-contact-places">
                                <thead>
                                    <th>#</th>
                                    <th>title</th>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <button class="btn" id="btn-addPlace">addNewPlace</button>
                        </div>
                        <div class="tab-pane fade" id="div-contact-friends">
                            <table class="table table-bordered" id="table-contact-friends">
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
                    </div>
                    </div>
                    <div class="span3 div-details-satellite invisible" id="div-userMessages">
                        <h3>Messages</h3>
                        <p id="p-conversation" style="background:#FFC0C0;width:300px;height:300px;padding:10px;overflow:auto;">
                            <p>conversation</p>
                        </p>
                        <textarea rows="3" style="width: 290px;" id="textarea-currentMessage"></textarea>
                        <button class="btn" id="btn-sendMessage">Send</button>
                    </div>
                    <div class="span3 div-details-satellite invisible" id="div-userPlacesMap">
                        <h3>Map</h3>
                        <div id="map_canvas" style="width:300px; height:300px"></div>
                    </div>
                    <div class="div-details-satellite invisible" id="div-details-addPlace">
                        <div id="infoPanel">
                            <b>Marker status:</b>
                            <div id="markerStatus"><i>Click and drag the marker.</i></div>
                            <b>Current position:</b>
                            <div id="info"></div>
                            <b>Closest matching address:</b>
                            <div id="address"></div>
                            <button class="btn" id="btn-submit-addPlace" style="background: greenyellow">Submit</button>
                        </div>
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
                        <button class="btn" id="btn-add-newUserOnEnter" style="margin-left: 200px; background: greenyellow">Add</button>
                        <button class="btn btn-exit" id="btn-exit-newUserOnEnter" style="margin-left: 200px; background: #ff5a30">Exit</button>
                    </div>
                </div>
                <div class="div-info div-table-all invisible table-responsive" id="div-table-allContacts">
                    <h4>All contacts</h4>
                    <table class="table table-bordered" id="table-allContacts">
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

                <div class="div-info div-table-all invisible table-responsive" id="div-table-allHobbies">
                    <h4>All hobbies</h4>
                    <table class="table table-bordered" id="table-allHobbies">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>

                <div class="div-info div-table-all invisible table-responsive" id="div-table-allPlaces">
                    <h4>All places</h4>
                    <table class="table table-bordered" id="table-allPlaces">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Title</th>
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

