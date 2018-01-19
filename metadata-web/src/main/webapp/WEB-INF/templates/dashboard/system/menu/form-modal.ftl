<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="${menu.id???string('修改菜单', '添加菜单')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/dashboard/system/menu/${menu.id???string('update', 'save')}">
    <#if menu.id??>
        <input type="hidden" name="id" value="${menu.id}"/>
    </#if>
    <div class="row">
        <#if parent_menu??>
            <input type="hidden" name="pcode" value="${(parent_menu.code)!''}">
            <div class="row form-group">
                <div class="col-md-3 control-label">
                    <label for="id">上级菜单</label>
                </div>
                <div class="col-md-7 controls">
                    <input value="${parent_menu.name}" class="form-control readonly" readonly />
                </div>
            </div>
        </#if>

        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label for="description">菜单名称</label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "menu.name" 'class="form-control" placeholder="菜单名称:2至12个汉字"'/>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label for="name">菜单代码</label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "menu.code" 'class="form-control" placeholder="格式参考:SYSTEM_USER"'/>
                <input type="hidden" id="old-code" value="${menu.code!''}">
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label for="url">菜单地址</label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "menu.url" 'class="form-control" placeholder="格式参考:system/user"'/>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label for="sort">排序</label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "menu.sort" 'class="form-control" placeholder="0排在最上面"'/>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label for="icon">图标</label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "menu.icon" 'class="form-control" placeholder="格式参考:menu-icon fa fa-dashboard"'/>
            </div>
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

<script src="${ctx}/static/app/js/dashboard/system/menu/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>