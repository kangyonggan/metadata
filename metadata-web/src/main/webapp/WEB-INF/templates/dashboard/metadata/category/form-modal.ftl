<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="${category.name???string('编辑类型', '添加新类型')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/dashboard/metadata/category/${category.name???string('update', 'save')}">
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>类型代码<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "category.code" 'class="form-control" placeholder="类型代码：长度不得大于16"'/>
                <input type="hidden" id="old-code" value="${category.code!''}"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>类型名称<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "category.name" 'class="form-control" placeholder="类型名称：长度不得大于32"'/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>字段名<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "category.field" 'class="form-control" placeholder="字段名：长度不得大于32"'/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>所属数据库<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <select class="form-control" name="db">
                    <option value="MySQL">MySQL</option>
                </select>
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

<script src="${ctx}/static/app/js/dashboard/metadata/category/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>