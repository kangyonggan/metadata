<div id="navbar" class="navbar navbar-default">
    <div class="navbar-container" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="${ctx}/" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                <@s.message "app.name"/>
                </small>
            </a>
        </div>

    <@shiro.user>
        <#if user??>
            <div class="navbar-buttons navbar-header pull-right" role="navigation">
                <ul class="nav navbar-nav hidden-xs">
                    <li>
                        <a href="${ctx}/logout">退出</a>
                    </li>
                </ul>
            </div>
            <div class="pull-right" style="height: 45px; line-height: 45px; margin-right: 20px; color: #eee">
                欢迎，${(user.realname!='')?string('${user.realname}', '${user.username}')}
            </div>
        <#else>
            <div class="navbar-buttons navbar-header pull-right" role="navigation">
                <ul class="nav navbar-nav hidden-xs">
                    <li>
                        <a href="${ctx}/dashboard">工作台</a>
                    </li>
                </ul>
            </div>
        </#if>
    </@shiro.user>

    <@shiro.guest>
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav navbar-nav hidden-xs">
                <li>
                    <a href="${ctx}/#index">登录</a>
                </li>
            </ul>
        </div>
    </@shiro.guest>
    </div>
</div>