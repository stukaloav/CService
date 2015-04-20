$(document).ready(function(){

    $("#sidebar").css("height", $("#content").css("height"));

    $("#birthDate").datepicker();


        //var firstName = $("#firstName").val();
        //var lastName = $("#lastName").val();
        //var birthDate = $("#birthDate").val();
        //if(firstName === "" || firstName == undefined){
        //    alert("First Name is undefined");
        //}else if(lastName === "" || firstName == undefined){
        //    alert("Last Name is undefined");
        //}else if(birthDate === "" || birthDate == undefined){
        //    alert("Birth Date is undefined");
        //}else {
        //    $.post("/addContact", {firstName: firstName,
        //            lastName: lastName, brithDate: birthDate},
        //        function(data){
        //
        //        });
        //
    //get all contacts
    $("#getAllContacts").click(function () {
        $(".btn-method").removeClass("active");
        $("#getAllContacts").addClass("active");
        $("#table-allContacts").find("tr:gt(0)").remove();
        $.get("/contacts", {}, function (data){
            $.each(data, function(index, value) {
                $("#table-allContacts > tbody:last").append("<tr><td>"+index+"</td><td>"+
                value.firstName+"</td><td>"+ value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
            });
            $(".div-info").addClass("invisible");
            $(".div-info").addClass("collapsed");
            $("#div-table-allContacts").removeClass("collapsed");
            $("#div-table-allContacts").removeClass("invisible");

        });
    });


    $("#addContact").click(function(){
        $(".btn-method").removeClass("active");
        $("#addContact").addClass("active");
        $(".div-info").addClass("collapsed");
        $(".div-info").addClass("invisible");
        $("#div-form-addContact").removeClass("collapsed");
        $("#div-form-addContact").removeClass("invisible");
    });

    $("#btn-addContact-submit").click(function(){
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
            alert("in");
            $.get("/addContact", {
                    firstName: firstName,
                    lastName: lastName, birthDate: birthDate
                },
                function (data) {
                    alert("" + data);
                });
        }
    });


    //
    //$.post("/addContact", {firstName: firstName,
    //    lastName: lastName, brithDate: birthDate}, function(data){
    //    alert("catch");
    //});

});
    /*   $("#firstFormRef").click(function(){
        $("#form1").removeClass("hidden");
        $("#form2").addClass("hidden");
    });

    $("#secondFormRef").click(function(){
        $("#form2").removeClass("hidden");
        $("#form1").addClass("hidden");
    });

    $("#findBtn").click(function () {
        var firstName = $("#findInput").val();
        if (firstName === "" || firstName == undefined) {
            alert("No input parameters!");
        } else {
            $.post("/getPersons", {firstName: firstName}, function (data, status) {
                console.log(status);
                var answer = "";
                $(data).each(function(pos, person) {
                        answer += "Name: " + person.firstName + " " + person.lastName + ", phone - " + person.phoneNumber;
                        answer += "</br>";
                })
                $("#qwerty").html(answer);
            })
        }

    }) */

//});

