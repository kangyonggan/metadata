<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="${word.name???string('编辑字根', '添加新字根')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/dashboard/metadata/word/${word.name???string('update', 'save')}">
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>字根名称<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "word.name" 'class="form-control" placeholder="字根名称：长度不得大于10"'/>
                <input type="hidden" id="old-name" value="${word.name!''}"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>字根的值<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "word.value" 'class="form-control" placeholder="字根的值：长度不得大于32"'/>
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

<button class="btn btn-sm btn-success" id="submit" data-loading-text="正在<@s.message "app.button.save"/>..."
        data-toggle="form-submit" data-target="#modal-form">
    <i class="ace-icon fa fa-check"></i>
    <@s.message "app.button.save"/>
</button>

<script src="${ctx}/static/app/js/dashboard/metadata/word/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>