<#if category.isDeleted == 1>
<a href="javascript:" data-role="category-delete" title="恢复类型"
   data-url="${ctx}/dashboard/metadata/category/${category.id}/undelete">
    <span class="label label-danger arrowed-in">已删除</span>
</a>
<#else>
<a href="javascript:" data-role="category-delete" title="删除类型"
   data-url="${ctx}/dashboard/metadata/category/${category.id}/delete">
    <span class="label label-success arrowed-in">未删除</span>
</a>
</#if>