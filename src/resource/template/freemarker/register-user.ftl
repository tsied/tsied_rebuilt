<#setting locale="zh_CN">
<#setting number_format="#">

{
	
		"series":[
		
						<#assign flag=false/>
						{
								"name":"结束日期注册用户总数",
								"data":[
										 <#list aggregations['2'].buckets as b>
												<#if flag>,<#else>
												<#assign flag=true/></#if>
												<#if b['1'].value??>${b['1'].value}<#else>0</#if>
										</#list>
									]
						},
						<#assign flag1=false/>
						{
								"name":"新增注册用户数",
								"data":[
										 <#list aggregations['2'].buckets as b>
												<#if flag1>,<#else>
												<#assign flag1=true/></#if>
												<#if b['3'].value??>${b['3'].value}<#else>0</#if>
										</#list>
									]
						}
						
	
				]
}
