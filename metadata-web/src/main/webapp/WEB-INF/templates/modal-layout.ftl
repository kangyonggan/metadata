<#assign ctx="${(rca.contextPath)!''}">

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3 class="smaller lighter no-margin">
    ${modal_title}
        <@block name="modal-header"/>
    </h3>
</div>

<div class="modal-body">
<@block name="modal-body"/>
</div>

<div class="modal-footer">
<@block name="modal-footer"/>
</div>