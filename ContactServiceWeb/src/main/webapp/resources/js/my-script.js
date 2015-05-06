$(document).ready(function(){

    //first view
    $("#sidebar").css("height", $("#content").css("height"));

    //datepicker on birthdate in addContact dialog
    $("#birthDate").datepicker();
    //datepicker on birthdate in user authorisation dialog
    $("#userBirthDate").datepicker();

    var contactId;

    var userFriends = new Array();

    var map;
    var placeLatitude = 40.7141667;
    var placeLongitude = -74.0063889;
    var placeTitle = 'NewYork';

    //action on start to check user_name
    if(($.cookie("userFirstName") === "")||
        ($.cookie("userFirstName") == undefined)){
        $("#user-name").text("unknown");
        $("#btn-userExit").addClass("invisible");
        $("#btn-userEnter").removeClass("invisible");
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
                $("#table-contact-hobbies > tbody:last").append('<tr><td>'+index+
                '</td><td>'+value.title+'</td><td>'+value.description+'</td></tr>');
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
        $("#getAllContacts").addClass("active");
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
        $("#div-details").removeClass("invisible");
        if($("#li-contact-info").hasClass('active')){
            $('#div-userMessages').removeClass('invisible');
        }
    });

    //action to add friendship
    $('#btn-addFriendship').click(function(){
        $("#table-contact-friends > tbody > tr").remove();
        $.get("/addFriendship", {userId: $.cookie('userId'), contactId: contactId}, function(data){
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
        $.get("/removeFriendship", {userId: $.cookie('userId'), contactId: contactId}, function(data){
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
            $.get("/sendMessage", { content: currentMessage,
                                    messageDate: messageDate,
                                    senderId: $.cookie('userId'),
                                    receiverId: contactId },
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
                $("#table-contact-hobbies > tbody:last").append('<tr><td>'+index+
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
            $.get("/add", {firstName: firstName, lastName: lastName,
                birthDate: date, birthMonth: month, birthYear: year},
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
            $.get("/add", {firstName: firstName, lastName: lastName,
                    birthDate: date, birthMonth: month, birthYear: year},
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
        $('#btn-addPlace').removeClass('invisible');
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
    $('#btn-submit-addPlace').click(function() {
        var newLng = $('#info').text().split(',')[0];
        var newLat = $('#info').text().split(',')[1];
        var newTitle = $('#address').text();
        $.get("/addPlace", {contactId: contactId, latitude: newLat,
            longitude: newLng, title: newTitle}, function (data){
            $("#table-contact-places > tbody > tr").remove();
                $.each(data, function (index, value) {
                    $("#table-contact-places > tbody:last").append(
                        '<tr class="tr-contactPlaces" id="' +
                        value.latitude + '/' + value.longitude + '/' +
                        value.title + '"><td>' + index + '</td><td>' +
                        value.title + '</td></tr>');
                });
        });
    });

});
