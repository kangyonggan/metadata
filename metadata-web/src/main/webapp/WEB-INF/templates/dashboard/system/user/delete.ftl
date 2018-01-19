<#if user.isDeleted == 1>
<a href="javascript:" data-role="user-delete" title="恢复用户"
   data-url="${ctx}/dashboard/system/user/${user.id}/undelete">
    <span class="label label-danger arrowed-in">已删除</span>
</a>
<#else>
<a href="javascript:" data-role="user-delete" title="删除用户"
   data-url="${ctx}/dashboard/system/user/${user.username}/delete">
    <span class="label label-success arrowed-in">未删除</span>
</a>
</#if>