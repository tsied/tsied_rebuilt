<#setting locale="zh_CN">
<#setting number_format="#">

{
	
		"series":[
		
						<#assign flag1=false/>
						{
								"name":"新增注册用户数",
								"data":[
										 <#list aggregations['1'].buckets as b>
												<#if flag1>,<#else>
												<#assign flag1=true/></#if>
												<#if b['1'].value??>${b['1'].value}<#else>0</#if>
										</#list>
									]
						}
						
	
				]
}
