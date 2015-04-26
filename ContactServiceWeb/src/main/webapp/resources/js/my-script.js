$(document).ready(function(){

    //first view
    $("#sidebar").css("height", $("#content").css("height"));

    $("#birthDate").datepicker();
    $("#userBirthDate").datepicker();


    if(($.cookie("userFirstName") === "")||
        ($.cookie("userFirstName") == undefined)){
        $("#user-name").text("unknown");
        $("#btn-exit").addClass("invisible");
        $("#btn-enter").removeClass("invisible");
    }else{
        $("#user-name").text("" + $.cookie("userFirstName") + " " + $.cookie("userLastName"));
        $("#btn-enter").addClass("invisible");
        $("#btn-exit").removeClass("invisible");
    }


    //get all contacts
    $("#getAllContacts").click(function () {
        $(".btn").removeClass("active");
        $("#getAllContacts").addClass("active");
        $("#table-allContacts").find("tbody>tr").remove();
        $.get("/contacts", {}, function (data){
            $.each(data, function(index, value) {
                $("#table-allContacts > tbody:last").append("<tr class='tr-allContacts'><td>"
                +index+"</td><td>"+value.firstName+"</td><td>"+
                value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
            });
            $(".div-info").addClass("invisible");
            $("#div-table-allContacts").removeClass("invisible");

        });
    });

    //why this doesn't work????
    $(".tr-allContacts").on("click", function(){
       alert("yes");
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
                if(data === -1){
                    alert("contact is already exists");
                }else {
                    alert("new contact added, id: " + data);
                }
            })
        }
    });

    $("#btn-enter").on("click", function(){
        $(".btn").removeClass("active");
        $("#btn-enter").addClass("active");
        $(".div-info").addClass("invisible");
        $("#userFirstName").val("");
        $("#userLastName").val("");
        $("#userBirthDate").val("");
        $("#div-form-enter").removeClass("invisible");
    });

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
            $.get("/userEnter", {firstName: firstName, lastName: lastName,
                    birthDate: date, birthMonth: month, birthYear: year},
                function(data){
                    if(data !== "" && data != undefined){
                        $.cookie("userFirstName", data.firstName);
                        $.cookie("userLastName", data.lastName);
                        $.cookie("userId", data.id);
                        location.reload();
                    }else {
                        alert("unknown");
                    }
                });
        }
    });

});
