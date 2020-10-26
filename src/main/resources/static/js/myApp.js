$(document).ready(function() {
	

	$alert = $('.alert');
	if($alert.length) {
		setTimeout(function() {
	    	$alert.fadeOut('slow');
		   }, 1000
		);		
	}
				
				$('.switch input[type=checkbox]').on('change',function(){
					var checked = this.checked;
					var checkbox = $(this);
					var value = checkbox.prop('value');
					
					bootbox.confirm({
											title:'<span class="text-primary">'+"Activate/De-Activate user"+'</span>',
											message:  (this.checked)? 'Do you really want to <b class="text-info"> Activate </b> the user?': 'Do you really want to <b class="text-info"> De-Activate </b> the user ?',
											buttons: {
														cancel: {
																	label: '<i class="fa fa-times"></i> Cancel'
																},
														confirm: {
																	
																	label: '<i class="fa fa-check"></i> Ok'
																 }
													},
											callback: function (result) {
											if (result)
											{
												console.log(value);
												$.ajax({							            	
														type: 'GET',
														url: window.contextRoot +'/status/'+value+'/activation',
														timeout : 100000,
														
														success : function(data)
														{
					            		
															bootbox.alert({
																		title:'<span class="text-primary">'+'Information'+'</span>',
																		message:'<b class="text-info text-capitalize">'+ data +'</b>'
																	});							        									        			
														},
														error : function(e)
														{
															
																bootbox.alert('ERROR: '+ e);
														}						            	
												});
											}
							else 
					        {							        	
					        	checkbox.prop('checked', !checked);
					        }
				    	}
					 });
				});
				
				
				$("#checkAll").click(function(){
						if($(this).is(":checked"))
							{
								$('.checkId').prop("checked",true);
							}
						else{
							$('.checkId').prop("checked",false);
						}
				});
				
});
			







			
		
			
		