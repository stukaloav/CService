$(document).ready(function(){

    $("#sidebar").css("height", $("#content").css("height"));

    $("#birthDate").datepicker();

    //get all contacts
    $("#getAllContacts").click(function () {
        $(".btn-method").removeClass("active");
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
        $(".btn-method").removeClass("active");
        $("#addContact").addClass("active");
        $(".div-info").addClass("invisible");
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
            var dateBirthDate = new Date(birthDate);
            var month = "" + dateBirthDate.getMonth();
            var date = "" + dateBirthDate.getDate();
            var year = "" + dateBirthDate.getFullYear();
            alert("he");

        }
    });

});
