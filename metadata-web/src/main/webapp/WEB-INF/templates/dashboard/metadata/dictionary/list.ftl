<#assign ctx="${(rca.contextPath)!''}">
<#assign name = RequestParameters.name!'' />
<#assign value = RequestParameters.value!'' />

<div class="page-header">
    <h1>
        字典列表
        <small class="pull-right">
            <a href="${ctx}/dashboard/metadata/dictionary/create" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#myModal"
               data-backdrop="static">添加</a>
        </small>
    </h1>
</div>

<div class="space-10"></div>

<form class="form-inline" method="get">
    <div class="form-group">
        <input type="text" class="form-control" name="name" value="${name}" placeholder="字典名称" autocomplete="off"/>
    </div>
<form class="form-inline" method="get">
    <div class="form-group">
        <input type="text" class="form-control" name="value" value="${value}" placeholder="字典的值" autocomplete="off"/>
    </div>

    <button class="btn btn-sm btn-purple" data-toggle="search-submit">
        搜索
        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
    </button>
</form>

<div class="space-10"></div>

<table id="dictionary-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>字典名称</th>
        <th>字典的值</th>
        <th>字段类型</th>
        <th>类型长度</th>
        <th>是否允许为空</th>
        <th>默认值</th>
        <th>逻辑删除</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#if page.list?size gt 0>
        <#list page.list as dictionary>
            <#include "table-tr.ftl"/>
        </#list>
    <#else>
    <tr>
        <td colspan="20">
            <div class="empty">暂无查询记录</div>
        </td>
    </tr>
    </#if>
    </tbody>
</table>
<@c.pagination url="#metadata/dictionary" param="name=${name}&value=${value}"/>


<script src="${ctx}/static/app/js/dashboard/metadata/dictionary/list.js"></script>
