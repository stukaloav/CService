$(document).ready(function(){

    //css dependencies
    var height = $("#content").css("height");
    $("#sidebar").css("height",height);
    var width = $("#content").css("width");
    $("#header").css("width",width);
    $("#footer").css("width",width);
    var textMargin = $("#sidebar").css("width");
    $("#contentText").css("margin-left",textMargin);

    //var userId;
    //
    //if(userId === "" || userId == undefined){
    //    $("#start").trigger("click");
    //}
    //
    //$("#registration").click(function(){
    //    var firstName = $("#firstName").val();
    //    var lastName = $("#lastName").val();
    //    var birthDate = $("#birthDate").val();
    //    if(firstName === "" || firstName == undefined){
    //        alert("First Name is undefined");
    //    }else if(lastName === "" || firstName == undefined){
    //        alert("Last Name is undefined");
    //    }else if(birthDate === "" || birthDate == undefined){
    //        alert("Birth Date is undefined");
    //    }else {
    //        $.post("/addContact", {firstName: firstName,
    //                lastName: lastName, brithDate: birthDate},
    //            function(data){
    //                userId = data;
    //            });
    //        $("#table_contactInfo > tbody:last").append("<tr><td>"+firstName+
    //        "</td><td>"+lastName +"</td><td>"+ birthDate + "</td></tr>");
    //        $("#btn_closeStartDialog").trigger("click");
    //        $("#container").removeClass("invisible");
    //        $("#container").removeClass("collapsed");
    //    }
    //});


    //get all contacts
    $("#getAll").click(function () {
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

