window.notify = function(message) {
    $.notify(message, {position: "bottom right"})
};


ajax = function (dataInfo, handler) {
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data: dataInfo,
        success: function(response) {
            if (response["redirect"]) {
                location.href = response["redirect"];
            }
            handler(response);
        }
    });
};