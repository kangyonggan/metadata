<#assign modal_title="修改密码" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post" action="${ctx}/dashboard/system/user/password">
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>手机号</label>
            </div>
            <div class="col-md-7 controls">
                <input type="text" name="username" class="form-control readonly" value="${user.username}" readonly/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>真实姓名</label>
            </div>
            <div class="col-md-7 controls">
                <input type="text" class="form-control readonly" value="${user.realname}" readonly/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>新密码<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <input type="password" id="password" name="password" class="form-control" placeholder="密码:8至20位的字母数字组合" autocomplete="off"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>确认密码<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <input type="password" name="rePassword" class="form-control" placeholder="密码:8至20位的字母数字组合" autocomplete="off"/>
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
<script src="${ctx}/static/app/js/dashboard/system/user/password-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>