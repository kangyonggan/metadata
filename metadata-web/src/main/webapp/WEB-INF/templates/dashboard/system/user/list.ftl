<#assign ctx="${(rca.contextPath)!''}">
<#assign username = RequestParameters.username!'' />
<#assign realname = RequestParameters.realname!'' />
<#assign email = RequestParameters.email!'' />

<div class="page-header">
    <h1>
        用户列表
        <small class="pull-right">
            <a href="${ctx}/dashboard/system/user/create" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#myModal"
               data-backdrop="static">添加</a>
        </small>
    </h1>
</div>

<div class="space-10"></div>

<form class="form-inline" method="get">
    <div class="form-group">
        <input type="text" class="form-control" name="username" value="${username}" placeholder="用户名"
               autocomplete="off"/>
    </div>
    <div class="form-group">
        <input type="text" class="form-control" name="realname" value="${realname}" placeholder="真实姓名"
               autocomplete="off"/>
    </div>
    <div class="form-group">
        <input type="text" class="form-control" name="email" value="${email}" placeholder="电子邮箱"
               autocomplete="off"/>
    </div>

    <button class="btn btn-sm btn-purple" data-toggle="search-submit">
        搜索
        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
    </button>
</form>

<div class="space-10"></div>

<table id="user-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>手机号</th>
        <th>真实姓名</th>
        <th>电子邮箱</th>
        <th>逻辑删除</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#if page.list?? && page.list?size gt 0>
        <#list page.list as user>
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
<@c.pagination url="#system/user" param="username=${username}&realname=${realname}&email=${email}"/>

<script src="${ctx}/static/app/js/dashboard/system/user/list.js"></script>
