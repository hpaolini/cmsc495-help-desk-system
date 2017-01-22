/**
 * 
 */

var oUserTable;
        
$(document).ready(function() {
	oUserTable = $('#UserTable').dataTable({
    	'bJQueryUI': true,
    	'bAutoWidth': true,
    	"aoColumnDefs": [
                        	{ 'bSearchable': false, 'bVisible': false, "aTargets": [ 0 ] },
                        	{ 'bSearchable': false, 'bVisible': true, "aTargets": [ 3 ] },
                        	{ 'bSearchable': false, 'bVisible': false, "aTargets": [ 5 ] },
                        	{ 'bSearchable': false, 'bVisible': true, "aTargets": [ 6 ] }
                     	]
    });

	$('#DataTable').css('opacity',1);
	
	/* Add a click handler to the rows - this could be used as a callback */
    $("#UserTable tbody").click(function(event) {
    	fnClearSelected(oUserTable);
        $(event.target.parentNode).addClass('row_selected');
        
        fnPopulateActionFormFromSelectedEntry(oUserTable, event);
    });
    
    $('#ActionDialog').dialog({ 
    	autoOpen: false,
    	modal: true,
    	draggable: false,
    	width: 400
    });
    
    $('#CommitButton').button();
    $('#CancelButton').button();
    $('#CancelButton').click(function() {
    	$('#ActionDialog').dialog('close');
    });
    
    $('#InsertButton').button();
    $('#InsertButton').click(function() {
    	fnClearSelected(oUserTable);
    	fnClearActionForm();
    	
    	$('#CommitButton').unbind('click');
        $('#CommitButton').click(function() {
        	$('#ActionDialog').dialog('close');
        	$.post('/admin/user?action=insert', $("#UserAction").serialize(), function(data) { 
        		fnInsertSelectedTableEntry(oUserTable, data);
        	});
        });
    });
    
    $('#UpdateButton').button();
    $('#UpdateButton').click(function() {
    	var selectedRow = fnGetSelected(oUserTable);
    	if (selectedRow != null) {
        	$('#ActionDialog').dialog('open'); 
        	
        	$('#CommitButton').unbind('click');
        	$('#CommitButton').click(function() {
            	$('#ActionDialog').dialog('close');
            	$.post('/admin/user?action=update', $("#UserAction").serialize(), function(data) { 
            		fnUpdateSelectedTableEntry(oUserTable, selectedRow);
            	});
        	});
    	}
    	else
    		alert ('Select a row to update.');
    });
    
    $('#DeleteButton').button();
    $('#DeleteButton').click(function() {
    	var selectedRow = fnGetSelected(oUserTable);
    	
    	if (selectedRow != null) {
        	$.post('/admin/user?action=delete', $("#CurrentUserKey").serialize(), function (data) {
            	oUserTable.fnDeleteRow(selectedRow);
            });
    	}
    	else
    		alert ('Select a row to delete.');
    });
});

/* Get the rows which are currently selected */
function fnGetSelected(oTableLocal)
{
    var aTrs = oTableLocal.fnGetNodes();
     
    for ( var i=0 ; i<aTrs.length ; i++ )
    {
        if ( $(aTrs[i]).hasClass('row_selected') )
        {
            return aTrs[i];
        }
    }
    return null;
}

function fnClearSelected(oTableLocal)
{
	$(oTableLocal.fnSettings().aoData).each(function (){
        $(this.nTr).removeClass('row_selected');
    });
}

function fnClearActionForm() {
	$('#CurrentUserKey').val('');
	$('#CurrentUserFirstName').val('');
	$('#CurrentUserLastName').val('');
	$('#CurrentUserHasPrivileges:checked').val('');
	$('#CurrentUserUsername').val('');
	$('#CurrentUserPassword').val('');
	$('#CurrentUserExpirationDate').val('');
	$('#ActionDialog').dialog('open'); 	
}

function fnPopulateActionFormFromSelectedEntry(oTableLocal, event) {
    var index = 0;
    $('#CurrentUserKey').val(oTableLocal.fnGetData(event.target.parentNode, index++));
	$('#CurrentUserFirstName').val(oTableLocal.fnGetData(event.target.parentNode, index++));
	$('#CurrentUserLastName').val(oTableLocal.fnGetData(event.target.parentNode, index++));
	$('#CurrentUserHasPrivileges').attr('checked', oTableLocal.fnGetData(event.target.parentNode, index++) == 'true');
	$('#CurrentUserUsername').val(oTableLocal.fnGetData(event.target.parentNode, index++));
	$('#CurrentUserPassword').val(oTableLocal.fnGetData(event.target.parentNode, index++));
	$('#CurrentUserExpirationDate').val(oTableLocal.fnGetData(event.target.parentNode, index++));
}

function fnInsertSelectedTableEntry(oTableLocal, key) {
	var firstName = $("#CurrentUserFirstName").val();
	var lastName = $("#CurrentUserLastName").val();
	var hasPrivileges = $('#CurrentUserHasPrivileges').is(':checked');
	var username = $("#CurrentUserUsername").val();
	var password = $("#CurrentUserPassword").val();
	var expirationDate = $("#CurrentUserExpirationDate").val();
	oTableLocal.fnAddData([ key, firstName, lastName, hasPrivileges, username, password, expirationDate ]);	        
}

function fnUpdateSelectedTableEntry(oTableLocal, selectedRow) {
	var key = $("#CurrentUserKey").val();
	var firstName = $("#CurrentUserFirstName").val();
	var lastName = $("#CurrentUserLastName").val();
	var hasPrivileges = $('#CurrentUserHasPrivileges').is(':checked');
	var username = $("#CurrentUserUsername").val();
	var password = $("#CurrentUserPassword").val();
	var expirationDate = $("#CurrentUserExpirationDate").val();
	oTableLocal.fnUpdate([ key, firstName, lastName, hasPrivileges, username, password, expirationDate ], selectedRow);
}