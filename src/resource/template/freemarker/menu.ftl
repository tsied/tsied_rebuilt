<#macro tree dataList parentId>
	<#local newDataList=[]/>
	<#list dataList as data>
		<#if parentId=data.parentId>
			<#local newDataList+=[data]/>
		</#if>
	</#list>
	<#if newDataList?has_content>
		<#if 0 != parentId>
		,"childMenu":
	</#if>
	[
		<#list newDataList as data>
			{
			"id": ${data.id},
			"menuName": "${data.menuName}",
			"parentId": ${data.parentId},
			"menuLink": "${data.menuLink}"
			<@tree dataList data.id/>
			}<#sep>,
		</#list>
	 ]
	</#if>
</#macro>
<@tree data 0/>