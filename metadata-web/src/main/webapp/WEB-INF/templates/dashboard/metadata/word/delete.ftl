<#if word.isDeleted == 1>
<a href="javascript:" data-role="word-delete" title="恢复字根"
   data-url="${ctx}/dashboard/metadata/word/${word.id}/undelete">
    <span class="label label-danger arrowed-in">已删除</span>
</a>
<#else>
<a href="javascript:" data-role="word-delete" title="删除字根"
   data-url="${ctx}/dashboard/metadata/word/${word.id}/delete">
    <span class="label label-success arrowed-in">未删除</span>
</a>
</#if>