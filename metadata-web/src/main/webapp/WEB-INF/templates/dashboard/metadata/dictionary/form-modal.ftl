<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="${dictionary.id???string('编辑字典', '添加新字典')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/dashboard/metadata/dictionary/${dictionary.id???string('update', 'save')}">
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>字典名称<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "dictionary.name" 'class="form-control" placeholder="字典名称：长度不得大于10"'/>
                <input type="hidden" id="old-name" value="${dictionary.name!''}"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>字典的值<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "dictionary.value" 'class="form-control readonly" readonly placeholder="字典的值：长度不得大于32"'/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>字段类型<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <select class="form-control" name="categoryCode">
                    <#list categories as category>
                        <option value="${category.code}" <#if dictionary.id?? && dictionary.categoryCode==category.code>selected</#if>>${category.name}（${category.field}/${category.db}）</option>
                    </#list>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>类型长度</label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "dictionary.length" 'class="form-control" placeholder="类型长度：必须是正整数"'/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>是否允许为空<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <label>
                    <input type="radio" name="canEmpty" value="1" <#if dictionary.id?? && dictionary.canEmpty==1>checked</#if> class="ace"/>
                    <span class="lbl">&nbsp;是</span>
                </label>
                <label style="margin-left: 30px;">
                    <input type="radio" name="canEmpty" value="0" <#if !dictionary.id?? || dictionary.canEmpty==0>checked</#if> class="ace"/>
                    <span class="lbl">&nbsp;否</span>
                </label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>默认值</label>
            </div>
            <div class="col-md-7 controls">
                <@s.formInput "dictionary.defaultValue" 'class="form-control" placeholder="默认值：长度不得大于64"'/>
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

<script src="${ctx}/static/app/js/dashboard/metadata/dictionary/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>