<#setting locale="zh_CN">
<#setting number_format="#"> 

{
	
		"series":[
				<#assign flag0=false/>
				{
					"name":"addomain",
					"data":[
						 <#list aggregations['8'].buckets as b>
							<#if flag0>,<#else>
							<#assign flag0=true/></#if>
							<#if b['key']??>"${b['key']}"<#else>""</#if>
						</#list>
					]
				},
		
				<#assign flag=false/>
				{
					"name":"pv",
					"data":[
						 <#list aggregations['8'].buckets as b>
							<#if flag>,<#else>
							<#assign flag=true/></#if>
							<#if b['1'].value??>${b['1'].value}<#else>0</#if>
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
							<#if b['3'].value??>${b['3'].value}<#else>0</#if>
						</#list>
					 ]
				},
				<#assign flag2=false/>
				{
						"name":"ipStats",
						"data":[
								 <#list aggregations['8'].buckets as b>
									<#if flag2>,<#else>
									<#assign flag2=true/></#if>
									<#if b['4'].value??>${b['4'].value}<#else>0</#if>
								</#list>
							]
				},
				<#assign flag3=false/>
				{
						"name":"sessionStat",
						"data":[
								 <#list aggregations['8'].buckets as b>
									<#if flag3>,<#else>
									<#assign flag3=true/></#if>
									<#if b['5'].value??>${b['5'].value}<#else>0</#if>
								 </#list>
							]
				},
				<#assign flag4=false/>
				{
					"name":"exitSessionCount",
					"data":[
							<#list aggregations['8'].buckets as b>
								<#if flag4>,<#else>
								<#assign flag4=true/></#if>
								<#if b['6'].value==0.0 ||  b['5'].value ==0.0>0<#else>${(b['6'].value/b['5'].value)?string["0.####"]}</#if>
							</#list>
						]
				},
				<#assign flag5=false/>
				{
					"name":"bounceSessionCount",
					"data":[
							 <#list aggregations['8'].buckets as b>
								<#if flag5>,<#else>
								<#assign flag5=true/></#if>
								<#if b['7'].value==0.0 ||  b['5'].value ==0.0>0<#else>${(b['7'].value/b['5'].value)?string["0.####"]}</#if>
							</#list>
						]
				},
				<#assign flag6=false/>
				{
					"name":"sessionTime",
					"data":[
							 <#list aggregations['8'].buckets as b>
								<#if flag6>,<#else>
								<#assign flag6=true/></#if>
								<#if b['9'].value??>${b['9'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag7=false/>
				{
					"name":"reqPages",
					"data":[
							 <#list aggregations['8'].buckets as b>
								<#if flag7>,<#else>
								<#assign flag7=true/></#if>
								<#if b['10'].value??>${b['10'].value}<#else>0</#if>
							</#list>
						]
				}

		]
}
