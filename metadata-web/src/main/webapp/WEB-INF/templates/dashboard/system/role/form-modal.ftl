<#assign modal_title="${role.id???string('编辑角色', '添加新角色')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/dashboard/system/role/${role.id???string('update', 'save')}">
    <#if role.id??>
        <input type="hidden" name="id" value="${role.id}"/>
    </#if>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>角色编码<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "role.code" 'class="form-control" placeholder="格式参考:ROLE_ADMIN"'/>
                <input type="hidden" id="old-code" value="${role.code!''}"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>角色名称<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "role.name" 'class="form-control" placeholder="角色名称:2至12个汉字"'/>
            </div>
        </div>
    </div>
</@override>

<@override name="modal-footer">
    <button class="btn btn-sm" data-dismiss="modal">
        <i class="ace-icon fa fa-times"></i>
        <@s.message "app.button.cancel"/>
    </button>

<button class="btn btn-sm btn-success" id="submit" data-loading-text="正在<@s.message "app.button.save"/>..." data-toggle="form-submit"
        data-target="#modal-form">
    <i class="ace-icon fa fa-check"></i>
    <@s.message "app.button.save"/>

    <script src="${ctx}/static/app/js/dashboard/system/role/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>