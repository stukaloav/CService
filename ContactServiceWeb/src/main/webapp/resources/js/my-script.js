$(document).ready(function(){

    //first view
    $("#sidebar").css("height", $("#content").css("height"));

    //datepicker on birthdate in addContact dialog
    $("#birthDate").datepicker();
    //datepicker on birthdate in user authorisation dialog
    $("#userBirthDate").datepicker();

    var contactId;
    var placeId;
    var hobbyId;

    var userFriends = new Array();
    var userPlaces = new Array();

    var map;
    var placeLatitude = 0;
    var placeLongitude = 0;
    var placeTitle = '';

    //action on start to check user_name
    if(($.cookie("userFirstName") === "")||
        ($.cookie("userFirstName") == undefined)){
        $("#user-name").text("unknown");
        $("#btn-userExit").addClass("invisible");
        $("#btn-userEnter").removeClass("invisible");
        $('#div-form-enter').removeClass('invisible');
    }else{
        $("#user-name").text("" + $.cookie("userFirstName") + " " + $.cookie("userLastName"));
        $('#user-name').addClass('btn-exit');
        $("#btn-userEnter").addClass("invisible");
        $("#btn-userExit").removeClass("invisible");
        contactId = $.cookie('userId');
        //fill table-contact-info
        $(".div-info").addClass("invisible");
        $.get("/getContactById", {contactId: contactId}, function(data){
            if(data !== "" || data != undefined){
                $("#table-contact-info > tbody > tr").remove();
                $("#table-contact-info > tbody:last").append(
                    '<tr><td>' + data.firstName + '</td><td>' + data.lastName + '</td><td>' +
                    data.birthDate + '</td><tr>');
                $('#h-contactId').text("Contact details: "+
                $.cookie('userFirstName')+" "+ $.cookie('userLastName'));
            }else {
                alert("something wrong in /getContactById");
            }
        });

        //fill table-contact-places
        $("#table-contact-places > tbody > tr").remove();
        $.get("/getPlaces", {contactId:contactId}, function(data){
            $.each(data, function(index, value){
                $("#table-contact-places > tbody:last").append(
                    '<tr class="tr-contactPlaces" id="'+
                    value.latitude+'/'+value.longitude+'/'+
                    value.title+'"><td>'+index+'</td><td>'+
                    value.title+'</td></tr>');
            });
        });

        if(contactId != $.cookie('userId')){
            $('#btn-addPlace').addClass('invisible');
        }else{
            $('#btn-addPlace').removeClass('invisible');
        }

        //fill table-contact-hobbies
        $("#table-contact-hobbies > tbody > tr").remove();
        $.get("/getHobbies", {contactId:contactId}, function(data){
            $.each(data, function(index, value){
                $("#table-contact-hobbies > tbody:last").append('<tr class="tr-table-hobbies" id="'+
                value.id+'"><td>'+index+'</td><td>'+value.title+'</td><td>'+
                value.description+'</td></tr>');
            });
        });

        //fill table-contact-friends
        $("#table-contact-friends > tbody > tr").remove();
        $.get("/getFriends", {contactId:contactId}, function(data){
            $.each(data, function(index, value) {
                $("#table-contact-friends > tbody:last").append("<tr class='tr-allContacts' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
                userFriends.push(""+value.id);
            });
        });
        $("#div-details").removeClass("invisible");
    }

    //get all contacts
    $("#getAllContacts").click(function () {
        $(".btn").removeClass("active");
        $("#table-allContacts").find("tbody>tr").remove();
        $.get("/contacts", {}, function (data){
            if(($.cookie("userFirstName") === "")||
                ($.cookie("userFirstName") == undefined)){
                    $.each(data, function(index, value) {
                        $("#table-allContacts > tbody:last").append("<tr><td>" + index + "</td><td>" + value.firstName + "</td><td>" +
                        value.lastName + "</td><td>" + value.birthDate + "</td></tr>");
                    });
            }else {
                $.each(data, function(index, value) {
                    if(value.id != $.cookie('userId')){
                        $("#table-allContacts > tbody:last").append("<tr class='tr-allContacts' id='"+
                        value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                        value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
                    }
                });
            }
        });
        $(".div-info").addClass("invisible");
        $("#div-table-allContacts").removeClass("invisible");
    });

    //jump on contact details from table-allContacts by $(this).attr('id');
    $('#table-allContacts').on('click', '.tr-allContacts', function(){
        $(".div-info").addClass("invisible");
        contactId = $(this).attr('id');
        $.get("/getContactById", {contactId: contactId}, function(data){
            if(data !== "" || data != undefined){
                $("#table-contact-info > tbody > tr").remove();
                $("#table-contact-info > tbody:last").append(
                    '<tr><td>' + data.firstName + '</td><td>' + data.lastName + '</td><td>' +
                    data.birthDate + '</td><tr>');
                $('#h-contactId').text("Contact details: "+
                data.firstName+" "+ data.lastName);
            }else {
                alert("something wrong in /getContactById");
            }
        });
        if($.inArray(""+contactId, userFriends) > -1){
            $('#btn-addFriendship').addClass('invisible');
            $('#btn-removeFriendship').removeClass('invisible');
        }else {
            $('#btn-addFriendship').removeClass('invisible');
            $('#btn-removeFriendship').addClass('invisible');
        }
        if(contactId == $.cookie('userId')){
            $('#btn-removeFriendship').addClass('invisible');
            $('#btn-addFriendship').addClass('invisible');
        }
        $('.div-details-satellite').addClass('invisible');
        $("#div-userDetails").removeClass("invisible");
        $("#div-details").removeClass("invisible");
        if($("#li-contact-info").hasClass('active') && contactId != $.cookie('userId')){
            $('#div-userMessages').removeClass('invisible');
        }else if($("#li-contact-place").hasClass('active')){
            $('#div-userPlacesMap').removeClass('invisible');
        }
        if(contactId != $.cookie('userId')){
            $('#btn-addPlace').addClass('invisible');
        }else{
            $('#btn-addPlace').removeClass('invisible');
        }
    });

    //action to add friendship
    $('#btn-addFriendship').click(function(){
        $("#table-contact-friends > tbody > tr").remove();
        $.post("/addFriendship", {userId: $.cookie('userId'), contactId: contactId}).done(function(data){
            $.each(data, function(index, value) {
                $("#table-contact-friends > tbody:last").append("<tr class='tr-allContacts' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
            });
        });
        $('#btn-addFriendship').addClass('invisible');
        $('#btn-removeFriendship').removeClass('invisible');
    });

    //action to remove friendship
    $('#btn-removeFriendship').click(function(){
        $.post("/removeFriendship", {userId: $.cookie('userId'), contactId: contactId}).done(function(data){
            $("#table-contact-friends > tbody > tr").remove();
            $.each(data, function(index, value) {
                $("#table-contact-friends > tbody:last").append("<tr class='tr-allContacts' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
            });
        });
        $('#btn-removeFriendship').addClass('invisible');
        $('#btn-addFriendship').removeClass('invisible');
    });

    //fill messages
    $('#table-allContacts').on('click', '.tr-allContacts', function(){
        if(contactId != $.cookie('userId')){
            $("#p-conversation > p").remove();
            $.get("/getConversation", {senderId: $.cookie('userId'), receiverId:contactId}, function(data){
                $.each(data, function(index, value){
                    $("#p-conversation").append(
                        '<p>'+value.messageDate+' from '+value.senderFirstName+' to '+value.receiverFirstName+':<br/>'+
                        value.content+'</p>');
                });
            });
        }else {
            $('#div-userMessages').addClass('invisible');
        }
    });

    //action to send message
    $('#btn-sendMessage').click(function(){
        var currentMessage = $("#textarea-currentMessage").val();
        $("#textarea-currentMessage").val("");
        var messageDate = new Date();
        if(currentMessage == "" || currentMessage === undefined){
            alert("Empty message! Please type your message to send");
        }else{
            $("#p-conversation > p").remove();
            $.post("/sendMessage", { content: currentMessage,
                                    messageDate: messageDate,
                                    senderId: $.cookie('userId'),
                                    receiverId: contactId }).done(
                function(data){
                    $.each(data, function(index, value){
                        $("#p-conversation").append(
                            '<p>'+value.messageDate+' from '+value.senderFirstName+' to '+value.receiverFirstName+':<br/>'+
                            value.content+'</p>');
                    });
                });

        }
    });

    //fill the contact-places table
    $('#table-allContacts').on('click', '.tr-allContacts', function(){
        $("#table-contact-places > tbody > tr").remove();
        $.get("/getPlaces", {contactId:contactId}, function(data){
            $.each(data, function(index, value){
                $("#table-contact-places > tbody:last").append(
                    '<tr class="tr-contactPlaces" id="'+
                    value.latitude+'/'+value.longitude+'/'+
                    value.title+'"><td>'+index+'</td><td>'+
                    value.title+'</td></tr>');
            });
        });
        if(contactId != $.cookie('userId')){
            $('#btn-addPlace').addClass('invisible');
        }else{
            $('#btn-addPlace').removeClass('invisible');
        }
    });

    //action for contact place-table
    $('#table-contact-places').on('click', '.tr-contactPlaces', function(){
        $('#div-details-addPlace').addClass('invisible');
        var placeDetail = $(this).attr('id').split("/");
        placeLatitude = placeDetail[1];
        placeLongitude = placeDetail[0];
        placeTitle = placeDetail[2];
        var latlng = new google.maps.LatLng(placeLatitude, placeLongitude);
        var settings = {
            zoom: 10,
            center: latlng};

        map = new google.maps.Map(document.getElementById("map_canvas"), settings);

        var contentString = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h1 id="firstHeading" class="firstHeading">'+placeTitle+'</h1>'+
            '</div>';
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });

        var companyImage = new google.maps.MarkerImage('resources/images/logo.png',
            new google.maps.Size(100,50),
            new google.maps.Point(0,0),
            new google.maps.Point(50,50)
        );

        var companyPos = new google.maps.LatLng(placeLatitude, placeLongitude);

        var companyMarker = new google.maps.Marker({
            position: companyPos,
            map: map,
            icon: companyImage,
            title:placeTitle,
            zIndex: 3});
    });

    //fill the contact-hobbies table
    $('#table-allContacts').on('click', '.tr-allContacts', function(){
        $("#table-contact-hobbies > tbody > tr").remove();
        $.get("/getHobbies", {contactId:contactId}, function(data){
            $.each(data, function(index, value){
                $("#table-contact-hobbies > tbody:last").append('<tr class="tr-table-hobbies" id="'
                +value.id+'" title="'+value.title+'"><td>'+index+
                '</td><td>'+value.title+'</td><td>'+value.description+'</td></tr>');
            });
        });
    });

    //fill the contact-friends table
    $('#table-allContacts').on('click', '.tr-allContacts', function(){
        $("#table-contact-friends > tbody > tr").remove();
        $.get("/getFriends", {contactId:contactId}, function(data){
            $.each(data, function(index, value) {
                $("#table-contact-friends > tbody:last").append("<tr class='tr-allContacts' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
            });
        });
    });

    //adding new contact
    $("#addContact").click(function(){
        $(".btn").removeClass("active");
        $("#addContact").addClass("active");
        $(".div-info").addClass("invisible");
        $("#firstName").val("");
        $("#lastName").val("");
        $("#birthDate").val("");
        $("#div-form-addContact").removeClass("invisible");
    });

    //submit action in addContact dialog
    $("#btn-addContactSubmit").click(function(){
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var birthDate = $("#birthDate").val();
        if(firstName === "" || firstName == undefined){
            alert("First Name is undefined");
        }else if(lastName === "" || firstName == undefined){
            alert("Last Name is undefined");
        }else if(birthDate === "" || birthDate == undefined){
            alert("Birth Date is undefined");
        }else {
            var dateBirthDate = new Date(birthDate);
            var month = dateBirthDate.getMonth();
            var date = dateBirthDate.getDate();
            var year = (dateBirthDate.getFullYear() - 1900);
            $.post("/add", {firstName: firstName, lastName: lastName,
                birthDate: date, birthMonth: month, birthYear: year}).done(
                function(data){
                if(data === "" || data == undefined){
                    alert("contact is already exists");
                }else {
                    alert("new contact added, id: " + data.id);
                }
            })
        }
    });

    //exit action
    $("#btn-userExit").on("click", function(){
        $.cookie("userFirstName", "");
        $.cookie("userLastName", "");
        $.cookie("userId", "");
    });

    //action for all .btn-exit
    $(".btn-exit").on("click", function(){
        location.reload();
    });

    //action to start login user
    $("#btn-userEnter").on("click", function(){
        $(".btn").removeClass("active");
        $("#btn-userEnter").addClass("active");
        $(".div-info").addClass("invisible");
        $("#userFirstName").val("");
        $("#userLastName").val("");
        $("#userBirthDate").val("");
        $("#div-add-newUserOnEnter").addClass("invisible");
        $("#div-userEnter-submit").removeClass("invisible");
        $("#div-form-enter").removeClass("invisible");
    });

    //submit action when user try to login
    $("#btn-userEnterSubmit").on("click", function(){
        var firstName = $("#userFirstName").val();
        var lastName = $("#userLastName").val();
        var birthDate = $("#userBirthDate").val();
        if(firstName === "" || firstName == undefined){
            alert("First Name is undefined");
        }else if(lastName === "" || firstName == undefined){
            alert("Last Name is undefined");
        }else if(birthDate === "" || birthDate == undefined){
            alert("Birth Date is undefined");
        }else {
            var dateBirthDate = new Date(birthDate);
            var month = dateBirthDate.getMonth();
            var date = dateBirthDate.getDate();
            var year = (dateBirthDate.getFullYear() - 1900);
            $.get("/user", {firstName: firstName, lastName: lastName,
                    birthDate: date, birthMonth: month, birthYear: year},
                function(data){
                    if(data !== "" && data != undefined){
                        $.cookie("userId", data.id);
                        $.cookie("userFirstName", data.firstName);
                        $.cookie("userLastName", data.lastName);
                        location.reload();
                    }else {
                        $("#div-userEnter-submit").addClass("invisible");
                        $("#div-add-newUserOnEnter").removeClass("invisible");
                    }
                });
        }
    });

    //adding of new user if db doesn't contain contact that try to login
    $("#btn-add-newUserOnEnter").on("click", function() {
        var firstName = $("#userFirstName").val();
        var lastName = $("#userLastName").val();
        var birthDate = $("#userBirthDate").val();
        if (firstName === "" || firstName == undefined) {
            alert("First Name is undefined");
        } else if (lastName === "" || firstName == undefined) {
            alert("Last Name is undefined");
        } else if (birthDate === "" || birthDate == undefined) {
            alert("Birth Date is undefined");
        } else {
            var dateBirthDate = new Date(birthDate);
            var month = dateBirthDate.getMonth();
            var date = dateBirthDate.getDate();
            var year = (dateBirthDate.getFullYear() - 1900);
            $.post("/add", {firstName: firstName, lastName: lastName,
                    birthDate: date, birthMonth: month, birthYear: year}).done(
                function(data){
                    if(data !== "" && data != undefined){
                        $.cookie("userFirstName", data.firstName);
                        $.cookie("userLastName", data.lastName);
                        $.cookie("userId", data.id);
                        location.reload();
                    }else{
                        alert("something wrong! this user is already exists");
                    }
                });
        }
    });

    //exit action when quit authorising
    $("#btn-exit-newUserOnEnter").on("click", function() {
        $("#div-userEnter-submit").removeClass("invisible");
        $("#div-add-newUserOnEnter").addClass("invisible");
        location.reload();
    });

    //actions for details-info
    $("#div-userDetails").on("click", '#btn-contact-info', function(){
        $(".div-details-satellite").addClass("invisible");
        if(contactId != $.cookie('userId')){
            $("#div-userMessages").removeClass("invisible");
        }
    });

    //action change on btn-contact-places
    $("#div-userDetails").on("click", '#btn-contact-places', function(){
        $(".div-details-satellite").addClass("invisible");
        $("#div-userPlacesMap").removeClass("invisible");
        if(contactId != $.cookie('userId')){
            $('#btn-addPlace').addClass('invisible');
        }else{
            $('#btn-addPlace').removeClass('invisible');
        }
    });

    //action change on btn-contact-hobbies
    $("#div-userDetails").on("click", '#btn-contact-hobbies', function(){
        $(".div-details-satellite").addClass("invisible");
    });

    //action change on btn-contact-friends
    $("#div-userDetails").on("click", '#btn-contact-friends', function(){
        $(".div-details-satellite").addClass("invisible");
    });

    //actions for details-places
    $("#div-userDetails").on("click", '#btn-contact-places', function initialize() {
            var latlng = new google.maps.LatLng(placeLatitude, placeLongitude);
            var settings = {
                zoom: 10,
                center: latlng};

            map = new google.maps.Map(document.getElementById("map_canvas"), settings);

        var contentString = '<div id="content">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<h1 id="firstHeading" class="firstHeading">'+placeTitle+'</h1>'+
                '</div>';
            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });

            var companyImage = new google.maps.MarkerImage('resources/images/logo.png',
                new google.maps.Size(100,50),
                new google.maps.Point(0,0),
                new google.maps.Point(50,50)
            );

            var companyPos = new google.maps.LatLng(placeLatitude, placeLongitude);

            var companyMarker = new google.maps.Marker({
                position: companyPos,
                map: map,
                icon: companyImage,
                title:placeTitle,
                zIndex: 3});

        });

    //action for add-place btn and show add-place detail, create marker on map
    $('#btn-addPlace').click(function initialize(){
        $('#btn-addPlace').addClass('invisible');
        var latLng = new google.maps.LatLng(placeLatitude, placeLongitude);

        var marker = new google.maps.Marker({
            position: latLng,
            title: 'Point A',
            map: map,
            draggable: true
        });

        $('#div-details-addPlace').removeClass('invisible');

        updateMarkerPosition(latLng);
        geocodePosition(latLng);

        // Add dragging event listeners.
        google.maps.event.addListener(marker, 'dragstart', function() {
            updateMarkerAddress('Dragging...');
        });

        google.maps.event.addListener(marker, 'drag', function() {
            updateMarkerStatus('Dragging...');
            updateMarkerPosition(marker.getPosition());
        });

        google.maps.event.addListener(marker, 'dragend', function() {
            updateMarkerStatus('Drag ended');
            geocodePosition(marker.getPosition());
        });
    });

    //action submit add place btn, adding and fill table
    $('#btn-submit-addPlace').click(function(){
        var newLng = $('#info').text().split(',')[0];
        var newLat = $('#info').text().split(',')[1];
        var newTitle = $('#address').text();
        $.post("/addPlace", {contactId: contactId, latitude: newLat,
            longitude: newLng, title: newTitle}).done(function (data){
            $("#table-contact-places > tbody > tr").remove();
                $.each(data, function (index, value) {
                    $("#table-contact-places > tbody:last").append(
                        '<tr class="tr-contactPlaces" id="' +
                        value.latitude + '/' + value.longitude + '/' +
                        value.title + '"><td>' + index + '</td><td>' +
                        value.title + '</td></tr>');
                });
        });
        $("#div-details-addPlace").addClass('invisible');
        $('#btn-addPlace').removeClass('invisible');
    });

    //get all hobbies
    $("#getAllHobbies").click(function () {
        $(".btn").removeClass("active");
        $("#table-allHobbies").find("tbody>tr").remove();
        $.get("/hobbies", {}, function (data){
            if(($.cookie("userFirstName") === "")||
                ($.cookie("userFirstName") == undefined)){
                $.each(data, function(index, value) {
                    $("#table-allHobbies > tbody:last").append("<tr><td>" + index + "</td><td>" +
                    value.title + "</td><td>" +
                    value.description + "</td></tr>");
                });
            }else {
                $.each(data, function(index, value) {
                    $("#table-allHobbies > tbody:last").append("<tr class='tr-allHobbies' id='"+
                    value.id+"' title='"+value.title+"'><td>"+index+"</td><td>"+value.title+"</td><td>"+
                    value.description+"</td></tr>");
                });
            }
        });
        $(".div-info").addClass("invisible");
        $("#div-table-allHobbies").removeClass("invisible");
    });

    //get all places
    $("#getAllPlaces").click(function () {
        $(".btn").removeClass("active");
        $("#table-allPlaces").find("tbody>tr").remove();
        $.get("/places", {}, function (data){
            if(($.cookie("userFirstName") === "")||
                ($.cookie("userFirstName") == undefined)){
                $.each(data, function(index, value) {
                    $("#table-allPlaces > tbody:last").append("<tr><td>" + index + "</td><td>" +
                    value.title + "</td></tr>");
                });
            }else {
                $.each(data, function(index, value) {
                    $("#table-allPlaces > tbody:last").append("<tr class='tr-allPlaces' id='"+
                    value.id+"' latitude='"+value.latitude+"' longitude='"+
                    value.longitude+"' title='"+value.title+"'><td>"+
                    index+"</td><td>"+value.title+"</td></tr>");
                });
            }
        });
        $(".div-info").addClass("invisible");
        $("#div-table-allPlaces").removeClass("invisible");
    });

    //jump to place-details-table
    $('#table-allPlaces').on('click', '.tr-allPlaces', function(){
        placeId = $(this).attr('id');
        $('#place-detail-title').text($(this).attr('title'));
        $('.div-info').addClass('invisible');
        $("#div-userDetails").addClass("invisible");
        $('#btn-addThisPlace').removeClass('invisible');
        $('#btn-removeThisPlace').addClass('invisible');
        $("#table-place-details > tbody > tr").remove();
        $.get("/contactsSamePlace", {placeId: placeId}, function(data){
                $.each(data, function(index, value) {
                    $("#table-place-details > tbody:last").append("<tr class='tr-table-place-details' id='"+
                    value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                    value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
                    if(value.id == $.cookie('userId')){
                        $('#btn-addThisPlace').addClass('invisible');
                        $('#btn-removeThisPlace').removeClass('invisible');
                    }
                });
        });
        $('#div-addHobby').addClass('invisible');
        $('#div-userMessages').addClass('invisible');
        $('#div-details-addPlace').addClass('invisible');
        $('#div-place-details').removeClass('invisible');

        placeLatitude = $(this).attr('latitude');
        placeLongitude = $(this).attr('longitude');

        $('#div-hobby-details').addClass('invisible');
        $("#div-details").removeClass("invisible");
        $('#div-userPlacesMap').removeClass('invisible');
    });

    //action for table-allPlaces to initialize map
    $('#table-allPlaces').on('click', '.tr-allPlaces', function initialize() {
        placeTitle = $(this).attr('title');
        placeLatitude = $(this).attr('latitude');
        placeLongitude = $(this).attr('longitude');
        var latlng = new google.maps.LatLng(placeLongitude, placeLatitude);
        var settings = {
            zoom: 10,
            center: latlng};

        map = new google.maps.Map(document.getElementById("map_canvas"), settings);

        var contentString = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h1 id="firstHeading" class="firstHeading">'+placeTitle+'</h1>'+
            '</div>';
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });

        var companyImage = new google.maps.MarkerImage('resources/images/logo.png',
            new google.maps.Size(100,50),
            new google.maps.Point(0,0),
            new google.maps.Point(50,50)
        );

        var companyPos = new google.maps.LatLng(placeLongitude, placeLatitude);

        var companyMarker = new google.maps.Marker({
            position: companyPos,
            map: map,
            icon: companyImage,
            title:placeTitle,
            zIndex: 3});

    });

    //action for btn-addThisPlace
    $('#btn-addThisPlace').on('click', function(){
        $('#btn-addThisPlace').removeClass('invisible');
        $('#btn-removeThisPlace').addClass('invisible');
        $.post("/addPlaceToContact", {contactId: $.cookie('userId'), placeId: placeId}).done(function(answer){
            $("#table-place-details > tbody > tr").remove();
            $.each(answer, function(index, value) {
                $("#table-place-details > tbody:last").append("<tr class='tr-table-place-details' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
                if(value.id == $.cookie('userId')){
                    $('#btn-addThisPlace').addClass('invisible');
                    $('#btn-removeThisPlace').removeClass('invisible');
                }
            });
        });
    });

    //action for btn-removeThisPlace
    $('#btn-removeThisPlace').on('click', function(){
        $('#btn-addThisPlace').removeClass('invisible');
        $('#btn-removeThisPlace').addClass('invisible');
        $.post("/removePlaceFromContact", {contactId: $.cookie('userId'), placeId: placeId}).done(function(answer){
            $("#table-place-details > tbody > tr").remove();
            $.each(answer, function(index, value) {
                $("#table-place-details > tbody:last").append("<tr class='tr-table-place-details' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
                if(value.id == $.cookie('userId')){
                    $('#btn-addThisPlace').addClass('invisible');
                    $('#btn-removeThisPlace').removeClass('invisible');
                }
            });
        });
    });

    //jump on contact detail from table-place-details
    $("#table-place-details").on('click', ".tr-table-place-details", function(){
        $(".div-info").addClass("invisible");
        contactId = $(this).attr('id');
        if(contactId != $.cookie('userId')){
            $.get("/getContactById", {contactId: contactId}, function(data){
                if(data !== "" || data != undefined){
                    $("#table-contact-info > tbody > tr").remove();
                    $("#table-contact-info > tbody:last").append(
                        '<tr><td>' + data.firstName + '</td><td>' + data.lastName + '</td><td>' +
                        data.birthDate + '</td><tr>');
                    $('#h-contactId').text("Contact details: "+
                    data.firstName+" "+ data.lastName);
                }else {
                    alert("something wrong in /getContactById");
                }
            });
            if($.inArray(""+contactId, userFriends) > -1){
                $('#btn-addFriendship').addClass('invisible');
                $('#btn-removeFriendship').removeClass('invisible');
            }else {
                $('#btn-addFriendship').removeClass('invisible');
                $('#btn-removeFriendship').addClass('invisible');
            }
            if(contactId == $.cookie('userId')){
                $('#btn-removeFriendship').addClass('invisible');
                $('#btn-addFriendship').addClass('invisible');
            }
            $('.div-details-satellite').addClass('invisible');
            $('#div-hobby-details').addClass('invisible');
            $("#div-userDetails").removeClass("invisible");
            $("#div-details").removeClass("invisible");
            if($("#li-contact-info").hasClass('active')&& contactId != $.cookie('userId')){
                $('#div-userMessages').removeClass('invisible');
            }else if($("#li-contact-place").hasClass('active')){
                $('#div-userPlacesMap').removeClass('invisible');
            }
            if(contactId != $.cookie('userId')){
                $('#btn-addPlace').addClass('invisible');
            }else{
                $('#btn-addPlace').removeClass('invisible');
            }
        }else{
            location.reload();
        }

    });

    //jump to hobby-details-table
    $('#table-allHobbies').on('click', '.tr-allHobbies', function(){
        hobbyId = $(this).attr('id');
        $('#hobby-detail-title').text($(this).attr('title'));
        $('.div-info').addClass('invisible');
        $("#div-userDetails").addClass("invisible");
        $('#btn-addThisHobby').removeClass('invisible');
        $('#btn-removeThisHobby').addClass('invisible');
        $("#table-hobby-details > tbody > tr").remove();
        $.get("/contactsSameHobby", {hobbyId: hobbyId}, function(data){
            $.each(data, function(index, value) {
                $("#table-hobby-details > tbody:last").append("<tr class='tr-table-hobby-details' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
                if(value.id == $.cookie('userId')){
                    $('#btn-addThisHobby').addClass('invisible');
                    $('#btn-removeThisHobby').removeClass('invisible');
                }
            });
        });
        $('#div-addHobby').addClass('invisible');
        $('#div-userMessages').addClass('invisible');
        $('#div-details-addPlace').addClass('invisible');
        $('#div-place-details').addClass('invisible');
        $('#div-userPlacesMap').addClass('invisible');
        $("#div-details").removeClass("invisible");
        $('#div-hobby-details').removeClass('invisible');
    });

    //action to add hobby to contact
    $('#btn-addThisHobby').on('click', function(){
        $('#btn-addThisHobby').removeClass('invisible');
        $('#btn-removeThisHobby').addClass('invisible');
        $.post("/addHobbyToContact", {contactId: $.cookie('userId'), hobbyId: hobbyId}).done(function(answer){
                $("#table-hobby-details > tbody > tr").remove();
                $.each(answer, function(index, value) {
                    $("#table-hobby-details > tbody:last").append("<tr class='tr-table-hobby-details' id='"+
                    value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                    value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
                    if(value.id == $.cookie('userId')){
                        $('#btn-addThisHobby').addClass('invisible');
                        $('#btn-removeThisHobby').removeClass('invisible');
                    }
            });
        });
    });

    //action to remove hobby from contact
    $("#btn-removeThisHobby").on('click', function(){
        $('#btn-addThisHobby').removeClass('invisible');
        $('#btn-removeThisHobby').addClass('invisible');
        $.post("/removeHobbyFromContact", {contactId: $.cookie('userId'), hobbyId: hobbyId}).done(function(answer){
            $("#table-hobby-details > tbody > tr").remove();
            $.each(answer, function(index, value) {
                $("#table-hobby-details > tbody:last").append("<tr class='tr-table-hobby-details' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
                if(value.id == $.cookie('userId')){
                    $('#btn-addThisHobby').addClass('invisible');
                    $('#btn-removeThisHobby').removeClass('invisible');
                }
            });
        });
    });

    //jump to contact-details from table-hobby-details
    $("#table-hobby-details").on('click', ".tr-table-hobby-details", function(){
        $(".div-info").addClass("invisible");
        contactId = $(this).attr('id');
        $.get("/getContactById", {contactId: contactId}, function(data){
            if(data !== "" || data != undefined){
                $("#table-contact-info > tbody > tr").remove();
                $("#table-contact-info > tbody:last").append(
                    '<tr><td>' + data.firstName + '</td><td>' + data.lastName + '</td><td>' +
                    data.birthDate + '</td><tr>');
                $('#h-contactId').text("Contact details: "+
                data.firstName+" "+ data.lastName);
            }else {
                alert("something wrong in /getContactById");
            }
        });
        if($.inArray(""+contactId, userFriends) > -1){
            $('#btn-addFriendship').addClass('invisible');
            $('#btn-removeFriendship').removeClass('invisible');
        }else {
            $('#btn-addFriendship').removeClass('invisible');
            $('#btn-removeFriendship').addClass('invisible');
        }
        if(contactId == $.cookie('userId')){
            $('#btn-removeFriendship').addClass('invisible');
            $('#btn-addFriendship').addClass('invisible');
        }
        $('.div-details-satellite').addClass('invisible');
        $('#div-hobby-details').addClass('invisible');
        $("#div-userDetails").removeClass("invisible");
        $("#div-details").removeClass("invisible");
        if($("#li-contact-info").hasClass('active')&& contactId != $.cookie('userId')){
            $('#div-userMessages').removeClass('invisible');
        }else if($("#li-contact-place").hasClass('active')){
            $('#div-userPlacesMap').removeClass('invisible');
        }
        if(contactId != $.cookie('userId')){
            $('#btn-addPlace').addClass('invisible');
        }else{
            $('#btn-addPlace').removeClass('invisible');
        }
    });

    //jump to hobby-details from contact-details
    $("#table-contact-hobbies").on("click", ".tr-table-hobbies", function(){
        hobbyId = $(this).attr('id');
        $('#hobby-detail-title').text($(this).attr('title'));
        $('.div-info').addClass('invisible');
        $("#div-userDetails").addClass("invisible");
        $('#btn-addThisHobby').removeClass('invisible');
        $('#btn-removeThisHobby').addClass('invisible');
        $("#table-hobby-details > tbody > tr").remove();
        $.get("/contactsSameHobby", {hobbyId: hobbyId}, function(data){
            $.each(data, function(index, value) {
                $("#table-hobby-details > tbody:last").append("<tr class='tr-table-hobby-details' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
                if(value.id == $.cookie('userId')){
                    $('#btn-addThisHobby').addClass('invisible');
                    $('#btn-removeThisHobby').removeClass('invisible');
                }
            });
        });
        $('#div-userMessages').addClass('invisible');
        $('#div-details-addPlace').addClass('invisible');
        $('#div-place-details').addClass('invisible');
        $('#div-userPlacesMap').addClass('invisible');
        $("#div-details").removeClass("invisible");
        $('#div-hobby-details').removeClass('invisible');
    });

    //jump on contact details from table-contact-friends;
    $('#table-contact-friends').on('click', '.tr-allContacts', function(){
        $(".div-info").addClass("invisible");
        contactId = $(this).attr('id');
        $.get("/getContactById", {contactId: contactId}, function(data){
            if(data !== "" || data != undefined){
                $("#table-contact-info > tbody > tr").remove();
                $("#table-contact-info > tbody:last").append(
                    '<tr><td>' + data.firstName + '</td><td>' + data.lastName + '</td><td>' +
                    data.birthDate + '</td><tr>');
                $('#h-contactId').text("Contact details: "+
                data.firstName+" "+ data.lastName);
            }else {
                alert("something wrong in /getContactById");
            }
        });
        if($.inArray(""+contactId, userFriends) > -1){
            $('#btn-addFriendship').addClass('invisible');
            $('#btn-removeFriendship').removeClass('invisible');
        }else {
            $('#btn-addFriendship').removeClass('invisible');
            $('#btn-removeFriendship').addClass('invisible');
        }
        if(contactId == $.cookie('userId')){
            $('#btn-removeFriendship').addClass('invisible');
            $('#btn-addFriendship').addClass('invisible');
        }
        $('.div-details-satellite').addClass('invisible');
        $("#div-userDetails").removeClass("invisible");
        $("#div-details").removeClass("invisible");
        if($("#li-contact-info").hasClass('active') && contactId != $.cookie('userId')){
            $('#div-userMessages').removeClass('invisible');
        }else if($("#li-contact-place").hasClass('active')){
            $('#div-userPlacesMap').removeClass('invisible');
        };
    });

    //fill messages from contact-friends
    $('#table-contact-friends').on('click', '.tr-allContacts', function(){
        if(contactId != $.cookie('userId')){
            $("#p-conversation > p").remove();
            $.get("/getConversation", {senderId: $.cookie('userId'), receiverId:contactId}, function(data){
                $.each(data, function(index, value){
                    $("#p-conversation").append(
                        '<p>'+value.messageDate+' from '+value.senderFirstName+' to '+value.receiverFirstName+':<br/>'+
                        value.content+'</p>');
                });
            });
        }else {
            $('#div-userMessages').addClass('invisible');
        }
    });

    //fill the contact-places table  from contact-friends
    $('#table-contact-friends').on('click', '.tr-allContacts', function(){
        $("#table-contact-places > tbody > tr").remove();
        $.get("/getPlaces", {contactId:contactId}, function(data){
            $.each(data, function(index, value){
                $("#table-contact-places > tbody:last").append(
                    '<tr class="tr-contactPlaces" id="'+
                    value.latitude+'/'+value.longitude+'/'+
                    value.title+'"><td>'+index+'</td><td>'+
                    value.title+'</td></tr>');
            });
        });
        if(contactId != $.cookie('userId')){
            $('#btn-addPlace').addClass('invisible');
        }else{
            $('#btn-addPlace').removeClass('invisible');
        }
    });

    //action for contact place-table  from contact-friends
    $('#table-contact-friends').on('click', '.tr-allContacts', function(){
        $('#div-details-addPlace').addClass('invisible');
        var placeDetail = $(this).attr('id').split("/");
        placeLatitude = placeDetail[1];
        placeLongitude = placeDetail[0];
        placeTitle = placeDetail[2];
        var latlng = new google.maps.LatLng(placeLatitude, placeLongitude);
        var settings = {
            zoom: 10,
            center: latlng};

        map = new google.maps.Map(document.getElementById("map_canvas"), settings);

        var contentString = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h1 id="firstHeading" class="firstHeading">'+placeTitle+'</h1>'+
            '</div>';
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });

        var companyImage = new google.maps.MarkerImage('resources/images/logo.png',
            new google.maps.Size(100,50),
            new google.maps.Point(0,0),
            new google.maps.Point(50,50)
        );

        var companyPos = new google.maps.LatLng(placeLatitude, placeLongitude);

        var companyMarker = new google.maps.Marker({
            position: companyPos,
            map: map,
            icon: companyImage,
            title:placeTitle,
            zIndex: 3});
    });

    //fill the contact-hobbies table  from contact-friends
    $('#table-contact-friends').on('click', '.tr-allContacts', function(){
        $("#table-contact-hobbies > tbody > tr").remove();
        $.get("/getHobbies", {contactId:contactId}, function(data){
            $.each(data, function(index, value){
                $("#table-contact-hobbies > tbody:last").append('<tr class="tr-table-hobbies" id="'
                +value.id+'" title="'+value.title+'"><td>'+index+
                '</td><td>'+value.title+'</td><td>'+value.description+'</td></tr>');
            });
        });
    });

    //fill the contact-friends table  from contact-friends
    $('#table-contact-friends').on('click', '.tr-allContacts', function(){
        $("#table-contact-friends > tbody > tr").remove();
        $.get("/getFriends", {contactId:contactId}, function(data){
            $.each(data, function(index, value) {
                $("#table-contact-friends > tbody:last").append("<tr class='tr-allContacts' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
            });
        });
    });


    //action change on btn-contact-hobbies
    $("#div-userDetails").on("click", '#btn-contact-hobbies', function(){
        if(contactId == $.cookie('userId')){
            $(".div-details-satellite").addClass("invisible");
            $('#div-addHobby').removeClass('invisible');
        }
    });

    //action for addHobby-submit
    $('#btn-addHobby-submit').click(function(){
        var newHobbyTitle = $("#newHobbyTitle").val();
        var newHobbyDescription = $("#textarea-hobby-description").val();
        $("#newHobbyTitle").val("");
        $("#textarea-hobby-description").val("");
        if (newHobbyTitle === "" || newHobbyTitle == undefined) {
            alert("Hobby title is undefined");
        } else if (newHobbyDescription === "" || newHobbyDescription == undefined) {
            alert("Hobby description is undefined");
        } else {
            $.post("/addNewHobby", {contactId: $.cookie('userId'), title: newHobbyTitle, description: newHobbyDescription}).done(
                function(data){
                    $("#table-contact-hobbies > tbody > tr").remove();
                    $.get("/getHobbies", {contactId:contactId}, function(data){
                        $.each(data, function(index, value){
                            $("#table-contact-hobbies > tbody:last").append('<tr class="tr-table-hobbies" id="'+
                            value.id+'" title="'+value.title+'"><td>'+index+
                            '</td><td>'+value.title+'</td><td>'+value.description+'</td></tr>');
                        });
                    });
                });
        }
    });

    //action for addHobby-exit
    $('#btn-addHobby-exit').click(function(){
        $("#newHobbyTitle").val("");
        $("#textarea-hobby-description").val("");
    });
});
