<#setting locale="zh_CN">
<#setting number_format="#">
<#assign names=[]/>

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
								"name":"pv",
								"data":[
										 <#list aggregations['8'].buckets as b>
												<#if flag>,<#else>
												<#assign flag=true/></#if>
												<#if b['pv_stats'].value??>${b['pv_stats'].value}<#else>0</#if>
										</#list>
									]
						},
						<#assign flag1=false/>
						{
								"name":"uv",
								"data":[
										 <#list aggregations['8'].buckets as b>
												<#if flag1>,<#else>
												<#assign flag1=true/></#if>
												<#if b['uv_stats'].value??>${b['uv_stats'].value}<#else>0</#if>
										</#list>
									]
						},
						<#assign flag2=false/>
						{
								"name":"独立IP访问量",
								"data":[
										 <#list aggregations['8'].buckets as b>
												<#if flag2>,<#else>
												<#assign flag2=true/></#if>
												<#if b['ip_stats'].value??>${b['ip_stats'].value}<#else>0</#if>
										</#list>
									]
						},
						<#assign flag3=false/>
						{
								"name":"会话数",
								"data":[
										 <#list aggregations['8'].buckets as b>
												<#if flag3>,<#else>
												<#assign flag3=true/></#if>
												<#if b['session_count'].value??>${b['session_count'].value}<#else>0</#if>
										</#list>
									]
						},
						<#assign flag4=false/>
						{
								"name":"跳出率",
								"data":[
										 <#list aggregations['8'].buckets as b>
												<#if flag4>,<#else>
												<#assign flag4=true/></#if>
												<#if b['bounce_session_count'].value==0.0 ||  b['session_count'].value ==0.0>0<#else>${b['bounce_session_count'].value/b['session_count'].value}</#if>
										</#list>
									]
						},
						<#assign flag5=false/>
						{
								"name":"平均会话时长",
								"data":[
										 <#list aggregations['8'].buckets as b>
												<#if flag5>,<#else>
												<#assign flag5=true/></#if>
												<#if b['avg_session_time'].value??>${b['avg_session_time'].value/1000}<#else>0</#if>
										</#list>
									]
						},
						<#assign flag6=false/>
						{
								"name":"平均每次会话浏览页数",
								"data":[
										 <#list aggregations['8'].buckets as b>
												<#if flag6>,<#else>
												<#assign flag6=true/></#if>
												<#if b['avg_req_page'].value??>${b['avg_req_page'].value}<#else>0</#if>
										</#list>
									]
						}
	
				]
}
