<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="baseURL" value="/app/" />



<style>
    
    .text-error
    {
        color: red;
        font-weight: 35px;
    }
</style>


		
		<script type="text/javascript">
		$(document).ready(function() {
			$("#form").submit(function() {  
			
				$.post($(this).attr("action"), $(this).serialize());
			
			});
		
		});
		
		function submitForm()
		{
			var formVar = $('#form');
			var info = formVar.serialize();
			alert(formVar.attr("action"));
			alert(info);
			$.post(formVar.attr("action"), formVar.serialize(),function(html) {$("#infoMark").replaceWith(html);  });
		
		}
		
		
		
		</script>
		



		 
		<form:form id="form" method="post" cssClass="form well columnLeft" modelAttribute="formBean" action="${baseURL}complex/forms/addForm">
			<div class="row well">
				
		  		<c:if test="${not empty message}">
					<div id="message" class="${message.type}">${message.text}</div>	
		  		</c:if>
		  		<s:bind path="*">
		  			<c:if test="${status.error}">
				  		<div id="message" class="text-error">Form has errors</div>
		  			</c:if>
		  		</s:bind>
		  		
		  		
			</div>
		  	 
		  		<h2>Personal Info</h2>
		  		
		  		<table class="table">
		  		
		  		<tr>
		  		<td>
		  		<form:label path="name">Name 
		  		<form:errors path="name" cssClass="text-error" />
		  		</form:label>
		  		</td>
		  		<td><form:input path="name" /></td>
		  		</tr>
		  		
	  		
		  		<tr>
		  		<td>
		  		<form:label path="age">
		  			Age <form:errors path="age" cssClass="text-error" />
		 		</form:label>
		  		</td>
		  		<td><form:input path="age" /></td>
		  		</tr>
		  		
		  		<tr>
		  		<td>
				<form:label path="birthDate">
		  			Birth Date (in form yyyy-mm-dd) <form:errors path="birthDate" cssClass="text-error" />
		 		</form:label>		  		
		 		</td>
		  		<td><form:input path="birthDate" /></td>
		  		</tr>
		  		
		  		<tr>
		  		<td>
				<form:label path="currency">
		  			Currency (in form $#.##) <form:errors path="currency" cssClass="text-error" />
		  		</form:label>
		  		</td>
		  		<td><form:input path="currency" /></td>
		  		</tr>

		  		<tr>
		  		<td>
				<form:label path="percent">
		  			Percentage (in form ##%) <form:errors path="percent" cssClass="text-error" />
		  		</form:label>
		  		</td>
		  		<td><form:input path="percent" /></td>
		  		</tr>
		  		
		  		</table>
			  		
		  	
			 
				<h2>Inquiry</h2>
                                <table class="table">
                                    <tr><td><form:label path="inquiry">
					Type (select one)
				</form:label></td><td><form:select path="inquiry">
					<form:option value="comment">Comment</form:option>
					<form:option value="feedback">Feedback</form:option>
					<form:option value="suggestion">Suggestion</form:option>
				</form:select></td></tr>
                             
                                    
                                
                         
                                    <tr>
                                        <td>
		  		<form:label path="inquiryDetails">
		  			Details
		  		</form:label>
                                        </td><td>
		  		<form:textarea path="inquiryDetails" />
                                        </td></tr>
                                
                                
                               
		  	 
	 
                                    <tr><td>
                                            <form:label path="additionalInfo">Request Additional Info</form:label></td><td>
                                    <label><form:checkbox path="additionalInfo[mvc]" value="true" />on Spring MVC</label> 
                                    <label><form:checkbox path="additionalInfo[java]" value="true" />on Java (4-ever)</label></td></tr>				
			 
		  		  	
                                <tr><td><form:label path="subscribeNewsletter">
                                 Subscribe to Newsletter? </form:label></td><td>
                                    <label><form:radiobutton path="subscribeNewsletter" value="true" />Yes</label> 
                                    <label><form:radiobutton path="subscribeNewsletter" value="false" /> No</label></td></tr>
			 
	
                                <tr><td colspan="2"><button class="btn btn-large btn-primary" type="submit">Submit</button></td></tr>
                        
                        </table>
		</form:form>
		
	
