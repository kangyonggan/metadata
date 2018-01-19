<#assign ctx="${(rca.contextPath)!''}">

<div class="row">
    <div class="col-sm-10 col-sm-offset-1">
        <div class="widget-box transparent">
            <div class="widget-header widget-header-large">
                <h3 class="widget-title grey lighter">
                    <i class="ace-icon fa fa-leaf dark"></i>
                    用户详情
                </h3>
            </div>

            <div class="widget-body">
                <div class="widget-main padding-24">
                    <div class="row">

                        <div class="space-10"></div>

                        <div class="col-sm-4">
                            <div>
                                <img class="nav-user-photo" src="${ctx}${user.largeAvatar}"
                                     alt="${user.realname}">
                            </div>
                        </div>

                        <div class="space-10"></div>

                        <div class="col-sm-8">
                            <div class="row">
                                <div class="col-xs-11 label label-lg label-default arrowed-in arrowed-right">
                                    <b>基础信息</b>
                                </div>
                            </div>

                            <div>
                                <ul class="list-unstyled  spaced">
                                    <div class="space-4"></div>

                                    <li>
                                        <i class="ace-icon fa fa-caret-right green"></i>手机号：${user.username}
                                    </li>

                                    <div class="space-4"></div>

                                    <li>
                                        <i class="ace-icon fa fa-caret-right green"></i>真实姓名：${user.realname}
                                    </li>

                                    <div class="space-4"></div>

                                    <li>
                                        <i class="ace-icon fa fa-caret-right green"></i>电子邮箱：${user.email}
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/static/app/js/dashboard/system/user/detail.js"></script>
