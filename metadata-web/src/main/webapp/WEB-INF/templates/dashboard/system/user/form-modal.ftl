<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="${user.username???string('编辑用户', '添加新用户')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/dashboard/system/user/${user.username???string('update', 'save')}">
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>手机号<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "user.username" 'class="form-control" placeholder="用户名：5至20位的字母数字组合"'/>
                <input type="hidden" id="old-username" value="${user.username!''}"/>
            </div>
        </div>
    </div>
    <#if !user.username??>
        <div class="row">
            <div class="row form-group">
                <div class="col-md-3 control-label">
                    <label>密码<span class="red">*</span></label>
                </div>
                <div class="col-md-7 controls">
                    <input type="password" id="password" name="password" class="form-control" placeholder="密码：8至20位的字母数字组合"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="row form-group">
                <div class="col-md-3 control-label">
                    <label>确认密码<span class="red">*</span></label>
                </div>
                <div class="col-md-7 controls">
                    <input type="password" name="rePassword" class="form-control" placeholder="确认密码：8至20位的字母数字组合"/>
                </div>
            </div>
        </div>
    </#if>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>真实姓名<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "user.realname" 'class="form-control" placeholder="真实姓名：最多12个汉字"'/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>电子邮箱</label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "user.email" 'class="form-control" placeholder="电子邮箱：请输入您常用的邮箱"'/>
            </div>
        </div>
    </div>
</form>
</@override>

<@override name="modal-footer">
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-times"></i>
    <@s.message "app.button.cancel"/>
</button>

<button class="btn btn-sm btn-success" id="submit" data-loading-text="正在<@s.message "app.button.save"/>..." data-toggle="form-submit" data-target="#modal-form">
    <i class="ace-icon fa fa-check"></i>
    <@s.message "app.button.save"/>
</button>

<script src="${ctx}/static/app/js/dashboard/system/user/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>