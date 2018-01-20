$(function () {
    updateState("metadata/category");

    var $table = $('#category-table');

    $table.on('click', 'a[data-role=category-delete]', function () {
        var $trigger = $(this);
        var url = $trigger.data('url');

        var title = $trigger.attr("title");

        $.messager.confirm("提示", "确定" + title + "吗?", function () {
            $.get(url).success(function (html) {
                var $tr = $(html);
                $('#' + $tr.attr('id')).replaceWith($tr);
                Message.success("操作成功");
            }).error(function () {
                Message.error("网络错误，请稍后重试");
            })
        });
    });
});