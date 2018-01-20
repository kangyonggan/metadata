$(function () {

    var $form = $('#modal-form');
    var $btn = $("#submit");

    $form.validate({
        rules: {
            name: {
                required: true,
                maxlength: 10,
                remote: {
                    url: ctx + "/validate/dictionary",
                    type: 'post',
                    data: {
                        'name': function () {
                            return $('#name').val()
                        },
                        'oldName': function () {
                            return $('#old-name').val();
                        }
                    }
                }
            },
            value: {
                required: true,
                maxlength: 32
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            $btn.button('loading');
            $(form).ajaxSubmit({
                dataType: 'json',
                success: function (response) {
                    if (response.errCode == 'success') {
                        window.location.reload();
                    } else {
                        Message.error(response.errMsg);
                        $btn.button('reset');
                    }
                },
                error: function () {
                    Message.error("服务器内部错误，请稍后再试。");
                    $btn.button('reset');
                }
            });
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        errorElement: "div",
        errorClass: "error"
    });

    $("#name").keypress(function (e) {

        if (e.keyCode == 13) {
            $.get(ctx + "/dashboard/metadata/word/search?name=" + $(this).val(), function (data, status) {
                if (status == "success") {
                    data = eval('(' + data + ")");
                    var words = data.words;
                    var value = "";
                    if (words.length > 0) {
                        for (var i = 0; i < words.length; i++) {
                            var word = words[i];
                            if (word) {
                                value += words[i].value;
                            } else {
                                $("#value").val("");
                                Message.warning("字根不存在，请先添加字根！");
                                return;
                            }
                            if (i < words.length - 1) {
                                value += "_";
                            }
                        }
                    }
                    $("#value").val(value);
                } else {
                    Message.error("服务器内部错误，请联系管理员")
                }
            });
        }
    });



});