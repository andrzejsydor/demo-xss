console.log("app.js loaded");

$(document).ready(function () {
    $("#insertToSearchField").click(function () {
        console.log("ddd");
        $("#fname").val("<script>alert(1)</script>");
    });
});