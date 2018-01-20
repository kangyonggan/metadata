<#assign ctx="${(rca.contextPath)!''}">

<tr id="category-${category.id}">
    <td>${category.code}</td>
    <td>${category.name}</td>
    <td>${category.field}</td>
    <td>${category.db}</td>
    <td><#include "delete.ftl"></td>
    <td><@c.relative_date datetime=category.createdTime/></td>
    <td>
        <div class="btn-group">
            <a class="btn btn-xs btn-inverse" href="${ctx}/dashboard/metadata/category/${category.id}/edit" data-toggle="modal"
               data-target="#myModal"
               data-backdrop="static">编辑</a>
        </div>
    </td>
</tr>