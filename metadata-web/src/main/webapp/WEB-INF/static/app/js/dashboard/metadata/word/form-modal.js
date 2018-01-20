$(function () {

    var $form = $('#modal-form');
    var $btn = $("#submit");

    $form.validate({
        rules: {
            name: {
                required: true,
                maxlength: 10,
                remote: {
                    url: ctx + "/validate/word",
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
});