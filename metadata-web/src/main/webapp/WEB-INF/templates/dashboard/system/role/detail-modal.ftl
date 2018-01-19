<#assign modal_title="角色详情" />

<@override name="modal-body">
<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
    <tbody>
    <tr>
        <th width="25%">ID</th>
        <td width="75%">${role.id}</td>
    </tr>
    <tr>
        <th>角色编码</th>
        <td>${role.code}</td>
    </tr>
    <tr>
        <th>角色名称</th>
        <td>${role.name}</td>
    </tr>
    <tr>
        <th>逻辑删除</th>
        <td>${(role.isDeleted==1)?string('是', '否')}</td>
    </tr>
    <tr>
        <th>创建时间</th>
        <td><#if role.createdTime??>${role.createdTime?datetime}</#if></td>
    </tr>
    <tr>
        <th>更新时间</th>
        <td><#if role.updatedTime??>${role.updatedTime?datetime}</#if></td>
    </tr>
    </tbody>
</table>
</@override>

<@override name="modal-footer">
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-times"></i>
    <@s.message "app.button.close"/>
</button>
</@override>

<@extends name="../../../modal-layout.ftl"/>