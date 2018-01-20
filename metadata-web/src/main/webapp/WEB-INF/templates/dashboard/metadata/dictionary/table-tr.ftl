<#assign ctx="${(rca.contextPath)!''}">

<tr id="dictionary-${dictionary.id}">
    <td>${dictionary.name}</td>
    <td>${dictionary.value}</td>
    <td>
        <a href="${ctx}/dashboard/metadata/category/${dictionary.categoryCode}/detail" data-toggle="modal" data-target="#myModal"
           data-backdrop="static">
        ${dictionary.categoryName}
        </a>
    </td>
    <td>${dictionary.length}</td>
    <td>${(dictionary.canEmpty==0)?string('否', '是')}</td>
    <td>${dictionary.defaultValue}</td>
    <td><#include "delete.ftl"></td>
    <td><@c.relative_date datetime=dictionary.createdTime/></td>
    <td>
        <div class="btn-group">
            <a class="btn btn-xs btn-inverse" href="${ctx}/dashboard/metadata/dictionary/${dictionary.id}/edit"
               data-toggle="modal"
               data-target="#myModal"
               data-backdrop="static">编辑</a>

            <button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
                <li>
                    <a href="javascript:" data-role="dictionary-remove" title="删除字典"
                       data-url="${ctx}dashboard/metadata/dictionary/${dictionary.id}/remove">
                        物理删除
                    </a>
                </li>
            </ul>
        </div>
    </td>
</tr>