<#assign modal_title="类型详情" />

<@override name="modal-body">
<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
    <tbody>
    <tr>
        <th width="25%">ID</th>
        <td width="75%">${category.id}</td>
    </tr>
    <tr>
        <th>类型代码</th>
        <td>${category.code}</td>
    </tr>
    <tr>
        <th>类型名称</th>
        <td>${category.name}</td>
    </tr>
    <tr>
        <th>字段名</th>
        <td>${category.field}</td>
    </tr>
    <tr>
        <th>所属数据库</th>
        <td>${category.db}</td>
    </tr>
    <tr>
        <th>逻辑删除</th>
        <td>${(category.isDeleted==1)?string('是', '否')}</td>
    </tr>
    <tr>
        <th>创建时间</th>
        <td><#if category.createdTime??>${category.createdTime?datetime}</#if></td>
    </tr>
    <tr>
        <th>更新时间</th>
        <td><#if category.updatedTime??>${category.updatedTime?datetime}</#if></td>
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