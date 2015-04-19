$(document).ready(function(){

    //css dependencies
    var height = $("#content").css("height");
    $("#sidebar").css("height",height);
    var width = $("#content").css("width");
    $("#header").css("width",width);
    $("#footer").css("width",width);
    var textMargin = $("#sidebar").css("width");
    $("#contentText").css("margin-left",textMargin);

    $("#addContact").click(function(){
        $("#mainTable").addClass("invisible");
        $("#tableAllContacts").addClass("invisible");



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
        //}
    });

    //get all contacts
    $("#getAll").click(function () {
        $("#table_allContacts").find("tr:gt(0)").remove();
        $.get("/contacts", {}, function (data){
            var answer = "";
            $.each(data, function(index, value) {
                $("#table_allContacts > tbody:last").append("<tr><td>"+index+"</td><td>"+
                value.firstName+"</td><td>"+ value.lastName +"</td><td>"+ value.birthDate +"</td></tr>");
            });
            //$("#allContacts").html(answer);
            $("#mainTable").addClass("invisible");
            $("#tableAllContacts").removeClass("invisible");
        });
    });
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

