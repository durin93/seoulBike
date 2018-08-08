$(".pay").on("click", function (e) {
    e.preventDefault();
    $.ajax({
       type:'post',
       url : "/api/pay",
        data : "text",
       success : function (data) {
           console.log("data"+data);
            window.open(data);
       }
    });

});