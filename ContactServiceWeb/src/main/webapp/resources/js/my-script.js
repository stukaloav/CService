$(document).ready(function(){

    $("#sidebar").css("height", $("#content").css("height"));

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
        $("#getAllContacts").addClass("active");
        $("#addContact").removeClass("active");
        $("#table-allContacts").find("tr:gt(0)").remove();
        $.get("/contacts", {}, function (data){
            $.each(data, function(index, value) {
                $("#table-allContacts > tbody:last").append("<tr><td>"+index+"</td><td>"+
                value.firstName+"</td><td>"+ value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
            });
            $("#div-form-addContact").addClass("invisible");
            $("#div-form-addContact").addClass("collapsed");
            $("#div-table-allContacts").removeClass("collapsed");
            $("#div-table-allContacts").removeClass("invisible");

        });
    });

    $("#addContact").click(function(){
        $("#getAllContacts").removeClass("active");
        $("#addContact").addClass("active");
        $("#div-table-allContacts").addClass("collapsed");
        $("#div-table-allContacts").addClass("invisible");
        $("#div-form-addContact").removeClass("collapsed");
        $("#div-form-addContact").removeClass("invisible");
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

