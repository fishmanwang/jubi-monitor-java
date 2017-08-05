$(function() {


    $("#okBtn").off("click").on("click", function() {
        var username = $("#username").val();
        var password = $("#password").val();
        var data = {username: username, password: password};
        var ctx = $("#ctx").val()
        $.post(ctx + "/login", data, function(json) {
            if (json.status == 200) {
                window.location.href= ctx + "/page/index.html"
            }
        })
    })
})