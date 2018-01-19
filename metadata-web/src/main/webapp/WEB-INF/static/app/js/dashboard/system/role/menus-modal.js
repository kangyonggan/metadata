$(function () {
    var $form = $('#modal-form');
    var $modal = $form.parents('.modal');
    var $btn = $("#submit");

    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    $.fn.zTree.init($("#tree"), setting, zNodes);

    var str = "";
    $form.submit(function () {
        $btn.button('loading');
        $form.ajaxSubmit({
            beforeSubmit: function (arr) {
                var zTree = $.fn.zTree.getZTreeObj("tree");
                var nodes = zTree.getCheckedNodes(true);
                for (var i = 0; i < nodes.length; i++) {
                    if (str != "") {
                        str += ",";
                    }
                    str += nodes[i].code;
                }
                arr[0].value = str;
            },
            dataType: 'json',
            success: function (response) {
                if (response.errCode == 'success') {
                    $modal.modal('hide');
                    Message.success("菜单设置成功。");
                } else {
                    Message.error("菜单设置失败。");
                }
                $btn.button('reset');
            },
            error: function () {
                Message.error("服务器内部错误，请稍后再试。");
                $btn.button('reset');
            }
        });
        return false;
    });
});