<#assign ctx="${(rca.contextPath)!''}">
<#assign code = RequestParameters.code!'' />
<#assign name = RequestParameters.name!'' />
<#assign field = RequestParameters.field!'' />
<#assign db = RequestParameters.db!'' />

<div class="page-header">
    <h1>
        类型列表
        <small class="pull-right">
            <a href="${ctx}/dashboard/metadata/category/create" class="btn btn-sm btn-primary" data-toggle="modal"
               data-target="#myModal"
               data-backdrop="static">添加</a>
        </small>
    </h1>
</div>

<div class="space-10"></div>

<form class="form-inline" method="get">
    <div class="form-group">
        <input type="text" class="form-control" name="code" value="${code}" placeholder="类型代码" autocomplete="off"/>
    </div>
    <div class="form-group">
        <input type="text" class="form-control" name="name" value="${name}" placeholder="类型名称" autocomplete="off"/>
    </div>
    <div class="form-group">
        <input type="text" class="form-control" name="field" value="${field}" placeholder="字段名" autocomplete="off"/>
    </div>
    <div class="form-group">
        <select name="db" class="form-control">
            <option value="MySQL">MySQL</option>
        </select>
    </div>

    <button class="btn btn-sm btn-purple" data-toggle="search-submit">
        搜索
        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
    </button>
</form>

<div class="space-10"></div>

<table id="category-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>类型代码</th>
        <th>类型名称</th>
        <th>字段名</th>
        <th>所属数据库</th>
        <th>逻辑删除</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#if page.list?size gt 0>
        <#list page.list as category>
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
<@c.pagination url="#metadata/category" param="code=${code}&name=${name}&field=${field}&db=${db}"/>


<script src="${ctx}/static/app/js/dashboard/metadata/category/list.js"></script>
