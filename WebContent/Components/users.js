$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});



///CLIENT-MODEL================================================================
function validateUserForm() {
	
    
    if ($("#UFirstName").val().trim() == "") {
        return "Insert User First Name";
    }
	if ($("#ULastName").val().trim() == "") {
        return "Insert User Last Name";
    }
   //phone
    if ($("#Uphone").val().trim() == "") {
        return "Insert phone";
    }
	var contactReg = /^\d{10}$/;
	var tmpPhone =  $("#Uphone").val().trim();
	if(!tmpPhone.match(contactReg)){
		return "Insert a valid Phone Number...!";
	}

	
	//Email
	function isUMail(UMail) {
	var regex =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; 
		return regex.test(UMail);
	}
	
	if ($("#UMail").val().trim() == "")
	{
	return "Insert Email.";
	}
	else if(!isUMail($("#UMail").val())){
		return "Insert valid Email";
	}
	
	//userRole
	if ($("#UserRole").val().trim() == "") {
        return "Insert UserRole";
    }
   
    if ($("#UPassword").val().trim() == "") {
        return "Insert Password.";
    }
		
    return true;
}



///SAVE-BUTTON================================================================
$(document).on("click", "#btnSave", function (event) 
{
    // Clear alerts
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    
    // Form validation
    var status = validateUserForm();
    if (status != true) 
    {
        $("#alertError").text(status);
        $("#alertError").show();
        
        return;
    }
    
    // If valid
    var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "usersAPI",
            type: type,
            data: $("#formUser").serialize(),
            dataType: "text",
            complete: function (response, status) {
            	onUserSaveComplete(response.responseText, status);
            }
        });
});


function onUserSaveComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully saved.");
	    				$("#alertSuccess").show();
	    				
	    				$("#divPaymentsGrid").html(resultSet.data);
    			} 
    			else if (resultSet.status.trim() == "error") 
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
    	}
    	
    	else if (status == "error") 
    	{
    			$("#alertError").text("Error while saving.");
    			$("#alertError").show();
    	}	 
    	
    	else 
    	{
    			$("#alertError").text("Unknown error while saving..");
    			$("#alertError").show();
    	}
    	
    	$("#hidUserIDSave").val("");
    	$("#formUser")[0].reset();
}


///UPDATE-BUTTON================================================================
$(document).on("click", ".btnUpdate", function (event) 
{
    	$("#hidUserIDSave").val($(this).data("UserId"));
    	$("#UFirstName").val($(this).closest("tr").find('td:eq(0)').text());
		$("#ULastName").val($(this).closest("tr").find('td:eq(1)').text());
		$("#Uphone").val($(this).closest("tr").find('td:eq(2)').text());
    	$("#UMail").val($(this).closest("tr").find('td:eq(3)').text());
		$("#UserRole").val($(this).closest("tr").find('td:eq(4)').text());
		$("#UPassword").val($(this).closest("tr").find('td:eq(5)').text());
		
		
});


///DELETE-BUTTON================================================================
$(document).on("click", ".btnRemove", function (event) 
{
    $.ajax(
        {
            url: "usersAPI",
            type: "DELETE",
            data: "UserId=" + $(this).data("UserId"),
            dataType: "text",
            complete: function (response, status) 
            {
            	onUserDeleteComplete(response.responseText, status);
            }
        });
});


function onDeleteComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully deleted.");
    					$("#alertSuccess").show();
    					
    					$("#divUsersGrid").html(resultSet.data);
    			}
    			
    			else if (resultSet.status.trim() == "error")
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
    	} 
    	
    	else if (status == "error") 
    	{
    			$("#alertError").text("Error while deleting.");
    			$("#alertError").show();
    	} 
    	
    	else 
    	{
    			$("#alertError").text("Unknown error while deleting..");
    			$("#alertError").show();
    	}
}