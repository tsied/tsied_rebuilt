<#setting locale="zh_CN">
<#setting number_format="#"> 

{
	
		"series":[
				<#assign flag=false/>
				{
					"name":"loginusercnt",
					"data":[
						 <#list aggregations['source_url'].buckets as b>
							<#if flag>,<#else>
							<#assign flag=true/></#if>
							<#if b['loginusercnt'].value??>${b['loginusercnt'].value}<#else>0</#if>
						</#list>
					]
				},
				
				<#assign flag1=false/>
				{
					"name":"loginuserpaycnt",
					"data":[
						<#list aggregations['source_url'].buckets as b>
							<#if flag1>,<#else>
							<#assign flag1=true/></#if>
							<#if b['loginuserpaycnt'].value??>${b['loginuserpaycnt'].value}<#else>0</#if>
						</#list>
					 ]
				},
				<#assign flag2=false/>
				{
						"name":"paycnt",
						"data":[
								 <#list aggregations['source_url'].buckets as b>
									<#if flag2>,<#else>
									<#assign flag2=true/></#if>
									<#if b['paycnt'].value??>${b['paycnt'].value}<#else>0</#if>
								</#list>
							]
				},
				<#assign flag3=false/>
				{
						"name":"payusercnt",
						"data":[
								 <#list aggregations['source_url'].buckets as b>
									<#if flag3>,<#else>
									<#assign flag3=true/></#if>
									<#if b['payusercnt'].value??>${b['payusercnt'].value}<#else>0</#if>
								 </#list>
							]
				},
				<#assign flag4=false/>
				{
					"name":"regusercnt",
					"data":[
							<#list aggregations['source_url'].buckets as b>
								<#if flag4>,<#else>
								<#assign flag4=true/></#if>
								<#if b['regusercnt'].value??>${b['regusercnt'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag5=false/>
				{
					"name":"reguserpaycnt",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag5>,<#else>
								<#assign flag5=true/></#if>
								<#if b['reguserpaycnt'].value??>${b['reguserpaycnt'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag6=false/>
				{
					"name":"sdrate",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag6>,<#else>
								<#assign flag6=true/></#if>
								<#if b['sdrate'].value??>${b['sdrate'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag7=false/>
				{
					"name":"tdrate",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag7>,<#else>
								<#assign flag7=true/></#if>
								<#if b['tdrate'].value??>${b['tdrate'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag8=false/>
				{
					"name":"ydrate",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag8>,<#else>
								<#assign flag8=true/></#if>
								<#if b['ydrate'].value??>${b['ydrate'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag9=false/>
				{
					"name":"reguserpayargvcnt",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag9>,<#else>
								<#assign flag9=true/></#if>
								<#if b['reguserpayargvcnt'].value??>${b['reguserpayargvcnt'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag10=false/>
				{
					"name":"loginuserpayargvcnt",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag10>,<#else>
								<#assign flag10=true/></#if>
								<#if b['loginuserpayargvcnt'].value??>${b['loginuserpayargvcnt'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag11=false/>
				{
					"name":"usepayrarpu",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag11>,<#else>
								<#assign flag11=true/></#if>
								<#if b['usepayrarpu'].value == 'NaN'>0<#else>${b['usepayrarpu'].value}</#if>
							</#list>
						]
				},
				<#assign flag12=false/>
				{
					"name":"userpayargvcnt",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag12>,<#else>
								<#assign flag12=true/></#if>
								<#if b['userpayargvcnt'].value??>${b['userpayargvcnt'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag13=false/>
				{
					"name":"userarpu",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag13>,<#else>
								<#assign flag13=true/></#if>
								<#if b['userarpu'].value == 'NaN'>0<#else>${b['userarpu'].value}</#if>
							</#list>
						]
				},
				<#assign flag14=false/>
				{
					"name":"userpaycnt",
					"data":[
							 <#list aggregations['source_url'].buckets as b>
								<#if flag14>,<#else>
								<#assign flag14=true/></#if>
								<#if b['userpaycnt'].value??>${b['userpaycnt'].value}<#else>0</#if>
							</#list>
						]
				}
		]
}
