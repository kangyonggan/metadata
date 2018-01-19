<#if role.isDeleted == 1>
<a href="javascript:" data-role="role-delete" title="恢复角色"
   data-url="${ctx}/dashboard/system/role/${role.id}/undelete">
    <span class="label label-danger arrowed-in">已删除</span>
</a>
<#else>
<a href="javascript:" data-role="role-delete" title="删除角色"
   data-url="${ctx}/dashboard/system/role/${role.id}/delete">
    <span class="label label-success arrowed-in">未删除</span>
</a>
</#if>