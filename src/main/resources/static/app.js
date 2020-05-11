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


// let indexOf = document.location.href.indexOf("#");
// if (indexOf > 0) {
//     document.getElementById("result").innerHTML = document.location.href.substring(indexOf + 1);
// } else {
//     document.getElementById("result").innerHTML = 'XSS - Demo';
// }
