console.log("app.js loaded");

$(document).ready(function () {
    $("#insertToSearchField").click(function () {
        $("#fname").val("<script>alert(1)</script>");
    });
});

$(document).ready(function () {
    $("#insertToFormField").click(function () {
        $("#fname").val("<script>alert(1)</script>");
    });
});

document.getElementById("result").innerHTML = document.location.href.substring(document.location.href.indexOf("#") + 1);
