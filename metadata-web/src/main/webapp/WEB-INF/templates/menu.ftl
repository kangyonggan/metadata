<li>
    <a <#if menu.leaf?? && menu.leaf?size gt 0>class="dropdown-toggle" href="javascript:"
       <#else>href="#${menu.url}"</#if> data-url="${menu.url}">
        <i class="${menu.icon}"></i>
        <span class="menu-text">
        ${menu.name}
                </span>

    <#if menu.leaf?? && menu.leaf?size gt 0>
        <b class="arrow fa fa-angle-down"></b>
    </#if>
    </a>

    <b class="arrow"></b>

<#if menu.leaf?? && menu.leaf?size gt 0>
    <ul class="submenu">
        <#list menu.leaf as menu>
            <#--递归，可以实现无限级菜单-->
            <#include "menu.ftl"/>
        </#list>
    </ul>
</#if>
</li>