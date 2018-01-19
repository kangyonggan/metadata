<#assign ctx="${(rca.contextPath)!''}">
<link rel="stylesheet" href="${ctx}/static/libs/ztree/css/zTreeStyle.css"/>

<div class="space-10"></div>
<div class="col-sm-8 col-sm-offset-2 col-xs-12">
    <div class="widget-box widget-color-green2">
        <div class="widget-header">
            <h4 class="widget-title lighter smaller">全部菜单</h4>
        </div>

        <div class="widget-body">
            <div class="widget-main padding-8">
                <div id="menu_tree" class="ztree"></div>
            </div>
        </div>
    </div>
</div>

<form id="menus-form-delete" method="post" action="${ctx}/dashboard/system/menu/delete">
    <input type="hidden" name="id"/>
</form>

<script>
    var zNodes = [
        <#list all_menus as menu>
            {id:${menu.id}, pId:${menu.pid}, code:"${menu.code}", name: "${menu.name}", open: true},
        </#list>];
</script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
<script src="${ctx}/static/app/js/dashboard/system/menu/index.js"></script>

