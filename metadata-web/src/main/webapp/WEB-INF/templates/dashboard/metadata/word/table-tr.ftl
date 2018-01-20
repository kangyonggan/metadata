<#assign ctx="${(rca.contextPath)!''}">

<tr id="word-${word.id}">
    <td>${word.name}</td>
    <td>${word.value}</td>
    <td><#include "delete.ftl"></td>
    <td><@c.relative_date datetime=word.createdTime/></td>
    <td>
        <div class="btn-group">
            <a class="btn btn-xs btn-inverse" href="${ctx}/dashboard/metadata/word/${word.id}/edit" data-toggle="modal"
               data-target="#myModal"
               data-backdrop="static">编辑</a>
        </div>
    </td>
</tr>