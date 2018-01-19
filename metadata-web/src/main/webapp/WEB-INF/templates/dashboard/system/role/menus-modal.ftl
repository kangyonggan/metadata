<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="设置角色权限" />

<link rel="stylesheet" href="${ctx}/static/libs/ztree/css/zTreeStyle.css"/>

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/dashboard/system/role/${roleId}/menus">
    <input type="hidden" name="menus"/>
    <div class="control-group">
        <div>
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
</form>
</@override>

<@override name="modal-footer">
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-times"></i>
    <@s.message "app.button.cancel"/>
</button>

<button class="btn btn-sm btn-success" id="submit"
        data-loading-text="正在<@s.message "app.button.save"/>..." data-toggle="form-submit" data-target="#modal-form">
    <i class="ace-icon fa fa-check"></i>
    <@s.message "app.button.save"/>
</button>

<script>
    var zNodes = [
        <#list all_menus as menu>
            {
                id:${menu.id},
                pId:${menu.pid},
                name: "${menu.name}",
                code: "${menu.code}",
                open: true
            ${(role_menus?? && role_menus?seq_contains(menu.code))?string(", checked:true", "")}
            },
        </#list>];
</script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${ctx}/static/app/js/dashboard/system/role/menus-modal.js"></script>
</@override>
<@extends name="../../../modal-layout.ftl"/>