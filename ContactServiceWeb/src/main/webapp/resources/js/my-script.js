$(document).ready(function(){
    var height = $("#content").css("height");
    $("#sidebar").css("height",height);
    var width = $("#content").css("width");
    $("#header").css("width",width);
    $("#footer").css("width",width);
    var textMargin = $("#sidebar").css("width");
    $("#contentText").css("margin-left",textMargin);

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

