$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 

	// Form validation-------------------
	var status = validateUnitForm();
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 } 
	// If valid------------------------
	var type = ($("#hididSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
 	{ 
		 url : "UnitAPI", 
		 type : type, 
		 data : $("#formUnit").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
 		{ 
 			onUnitSaveComplete(response.responseText, status); 
 		} 
 	}); 
});

function onUnitSaveComplete(response, status)
{ 
	if (status == "success") 
 	{ 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divUnitGrid").html(resultSet.data); 
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
	 $("#hididSave").val(""); 
	 $("#formUnit")[0].reset(); 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
	{ 
		 $("#hididSave").val($(this).data("id")); 
		 $("#minunitValue").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#maxunitValue").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#unitprice").val($(this).closest("tr").find('td:eq(2)').text());  
		 $("#insertdate").val($(this).closest("tr").find('td:eq(3)').text());
         $("#modifieddate").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function(event)
	{ 
		 $.ajax( 
		 { 
		 	url : "UnitAPI", 
			type : "DELETE", 
		 	data : "id=" + $(this).data("id"),
		 	dataType : "text", 
		 	complete : function(response, status) 
		 	{ 
		 		onUnitDeleteComplete(response.responseText, status); 
		 	} 
	 }); 
});
		
function onUnitDeleteComplete(response, status)
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divUnitGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 } else if (status == "error") 
		 { 
			 $("#alertError").text("Error while deleting."); 
			 $("#alertError").show(); 
		 } else
		 { 
			 $("#alertError").text("Unknown error while deleting.."); 
			 $("#alertError").show(); 
	 } 
}


// CLIENT-MODEL================================================================
function validateUnitForm()
{
	//MINUNIT
	if ($("#minunitValue").val().trim() == ""){
		return "Insert minUnitValue.";
	}
	//MAXUNIT
	if ($("#maxunitValue").val().trim() == ""){
		return "Insert maxUnitValue.";
	}
	// Amount-------------------------------
	if ($("#unitprice").val().trim() == ""){
		return "Insert unitPrice.";
	}
	// is numerical value
	var tmpPrice = $("#unitprice").val().trim();
	if (!$.isNumeric(tmpPrice))
	{
		return "Insert a numerical value for unitPrice.";
	}
		
	// convert to decimal price
	$("#unitprice").val(parseFloat(tmpPrice).toFixed(2));

	//INSERTDATE
	if ($("#insertdate").val().trim() == ""){
		return "Insert insertDate.";
	}
	//MODIFIEDDATE
	if ($("#modifieddate").val().trim() == ""){
		return "Insert modifiedDate.";
	}
	return true;
}