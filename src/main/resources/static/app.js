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
