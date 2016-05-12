<#setting locale="zh_CN">
<#setting number_format="#">

{
	"date":
		[
			<#list aggregations['8'].buckets as b>
				"${b.key_as_string?date("yyyy-MM-dd")}"
				<#if b?has_next>,</#if>
			</#list>
			
		],	
		"series":[
		
						<#assign flag=false/>
						{
								"name":"结束日期注册用户总数",
								"data":[
										 <#list aggregations['8'].buckets as b>
												<#if flag>,<#else>
												<#assign flag=true/></#if>
												<#if b['1'].value??>${b['1'].value}<#else>0</#if>
										</#list>
									]
						}
						
	
				]
}
