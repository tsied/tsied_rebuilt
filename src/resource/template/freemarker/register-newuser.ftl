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
		
						<#assign flag1=false/>
						{
								"name":"新增注册用户数",
								"data":[
										 <#list aggregations['8'].buckets as b>
												<#if flag1>,<#else>
												<#assign flag1=true/></#if>
												<#if b['1'].value??>${b['1'].value}<#else>0</#if>
										</#list>
									]
						}
						
	
				]
}
