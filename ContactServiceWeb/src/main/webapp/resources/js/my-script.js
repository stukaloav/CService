$(document).ready(function(){

    //first view
    $("#sidebar").css("height", $("#content").css("height"));

    //datepicker on birthdate in addContact dialog
    $("#birthDate").datepicker();
    //datepicker on birthdate in user authorisation dialog
    $("#userBirthDate").datepicker();


    //action on start to check user_name
    if(($.cookie("userFirstName") === "")||
        ($.cookie("userFirstName") == undefined)){
        $("#user-name").text("unknown");
        $("#btn-userExit").addClass("invisible");
        $("#btn-userEnter").removeClass("invisible");
    }else{
        $("#user-name").text("" + $.cookie("userFirstName") + " " + $.cookie("userLastName"));
        $("#btn-userEnter").addClass("invisible");
        $("#btn-userExit").removeClass("invisible");
    }


    //get all contacts
    $("#getAllContacts").click(function () {
        $(".btn").removeClass("active");
        $("#getAllContacts").addClass("active");
        $("#table-allContacts").find("tbody>tr").remove();
        $.get("/contacts", {}, function (data){
            $.each(data, function(index, value) {
                $("#table-allContacts > tbody:last").append("<tr class='tr-allContacts' id='"+
                value.id+"'><td>"+index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
            });
        });
        $(".div-info").addClass("invisible");
        $("#div-table-allContacts").removeClass("invisible");
    });


    //jump on contact details from table-allContacts by $(this).attr('id');
    var contactId;
    $('#table-allContacts').on('click', '.tr-allContacts', function(){
        $(".div-info").addClass("invisible");
        contactId = $(this).attr('id');
        $.get("/getContactById", {contactId: contactId}, function(data){
            if(data !== "" || data != undefined){
                $("#table-contact-info > tbody > tr").remove();
                $("#table-contact-info > tbody:last").append(
                    '<tr><td>' + data.firstName + '</td><td>' + data.lastName + '</td><td>' +
                    data.birthDate + '</td><tr>');
                $.each(data.places)
            }else {
                alert("something wrong in /getContactById");
            }
        });
        $("#div-details").removeClass("invisible");
    });

    $('#table-allContacts').on('click', '.tr-allContacts', function(){
        $("#table-contact-places > tbody > tr").remove();
        $.get("/getPlaces", {contactId:contactId}, function(data){
            $.each(data, function(index, value){
                $("#table-contact-places > tbody:last").append('<tr><td>'+index+
                '</td><td>'+value.title+'</td></tr>');
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
                        $.cookie("userFirstName", data.firstName);
                        $.cookie("userLastName", data.lastName);
                        $.cookie("userId", data.id);
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
        $("#div-userMessages").removeClass("invisible");
    });

    $("#div-userDetails").on("click", '#btn-contact-places', function(){
        $(".div-details-satellite").addClass("invisible");
        $("#div-userPlacesMap").removeClass("invisible");
    });





        //actions for details-places
    $("#div-userDetails").on("click", '#btn-contact-places', function initialize() {
            var latlng = new google.maps.LatLng(57.0442, 9.9116);
            var settings = {
                zoom: 15,
                center: latlng,
                mapTypeControl: true,
                mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
                navigationControl: true,
                navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
                mapTypeId: google.maps.MapTypeId.ROADMAP};

            var map = new google.maps.Map(document.getElementById("map_canvas"), settings);
            var contentString = '<div id="content">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<h1 id="firstHeading" class="firstHeading">Høgenhaug</h1>'+
                '</div>';
            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });

            var companyImage = new google.maps.MarkerImage('resources/images/logo.png',
                new google.maps.Size(100,50),
                new google.maps.Point(0,0),
                new google.maps.Point(50,50)
            );

            var companyPos = new google.maps.LatLng(57.0442, 9.9116);

            var companyMarker = new google.maps.Marker({
                position: companyPos,
                map: map,
                icon: companyImage,
                title:"Høgenhaug",
                zIndex: 3});

        });




});
