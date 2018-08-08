
$(".user-join-form button[type=submit]").on("click", function(e){

    e.preventDefault();
    console.log("회원가입 클릭");

    var url = $(".user-join-form").attr("action");
    console.log("url :"+url);

    var queryString = $('.user-join-form').serialize();
    console.log("queryString :"+queryString);

    $.ajax({
        type:'post',
        url : url,
        data : queryString,
        dataType : 'json',
        error : function () {
            console.log("회원가입 error!!");
        },
        success : function (data) {

            if(data.status == 'fail'){
                console.log("성공 fail");
                $(".errorMessage").text(data.message);
            }
            else{
                console.log("성공 success");
                location.href = data.url;
            }

        }
    });



});
