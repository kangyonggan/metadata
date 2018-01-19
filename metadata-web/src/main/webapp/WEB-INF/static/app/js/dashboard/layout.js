$(function () {
    // 提示框
    $.messager.model = {
        cancel: {text: "取消", classed: 'btn-default'},
        ok: {text: "确定", classed: 'btn-success'}
    };

    // 关闭时清除模态框的数据
    $(document).on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });

    // form modal提交
    $('.modal').on('click', '[data-toggle=form-submit]', function (e) {
        e.preventDefault();
        $($(this).data('target')).submit();
    });

    // 搜索
    $(document).on("click", "[data-toggle='search-submit']", function (e) {
        e.preventDefault();
        var $this = $(this);
        var $form = $this.parent("form");

        var params = '?';
        var arr = $form.serializeArray();
        for (var i = 0; i < arr.length; i++) {
            if (i != 0) {
                params += '&';
            }
            params += arr[i].name + "=";
            params += arr[i].value;
        }

        var hash = window.location.hash;
        var index = hash.indexOf("?");
        if (index > -1) {
            hash = hash.substring(0, index);
        }

        window.location.href = window.location.origin + window.location.pathname + hash + params;
        return false;
    });
});
